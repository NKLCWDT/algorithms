package jungho.thisiscodingtest.part03.Q22_movingblocks;

/*
    로봇 크기 : 2 x 1
    좌측 상단 시작 좌표 : (1, 1)
    좌표 값 : 0(빈칸) or 1(벽)
    로봇은 벽이 있는 칸 or 지도 밖으로 이동 불가능
    로봇은 처음에 (1,1) 에서 가로로 누워있음 -> (1,1) 과 (1,2) 차지
    로봇은 앞뒤 구분 없이 이동 가능
    (1,1)(1,2) 에서 우측으로 한 칸 이동하면 -> (1,2)(1,3)
        -> tail, head 로 구분해서 생각하면 되는 건가
    로봇은 90도씩 회전 가능, 로봇의 head, tail 둘 다 회전 축이 될 수 있음
    단, 회전축의 대각선 방향에는 벽이 없어야 함
    로봇이 한칸 이동하거나 회전하는데 걸리는 시간은 1초
    로봇이 (N, N) 위치 까지 이동하는데 필요한 최소 시간을 구하시오.

    로봇이 처음 놓여있는 칸 1,1 과 1,2 는 항상 0으로 주어진다.
    로봇은 항상 목적지에 도착할 수 있는 경우만 입력으로 주어진다.

    head 기준으로 상하좌우 회전하여 이동가능
    tail 기준으로 상하좌우 회전하여 이동가능

    회전도하고, 이동도 다 해보고 시간정보를 큐에 삽입

    해당 문제의 정답률은 1.7% 라고 함
*/

import java.util.*;

// 카카오 : 블록 이동하기
public class Solution {

    static class Position {
        private int x;
        private int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Position position = (Position) o;
            return x == position.x && y == position.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    static class Robot {
        private Position head;
        private Position tail;
        private int cost;
        private Direction direction;

        public Robot(Position head, Position tail, int cost, Direction direction) {
            this.head = head;
            this.tail = tail;
            this.cost = cost;
            this.direction = direction;
        }

        enum Direction {
            HORIZONTAL, VERTICAL;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Robot robot = (Robot) o;
            return Objects.equals(head, robot.head) && Objects.equals(tail, robot.tail);
        }

        @Override
        public int hashCode() {
            return Objects.hash(head, tail);
        }
    }

    static final int BLANK = 0;
    static final int WALL = 1;
    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) {
        int[][] board = {{0, 0, 0, 1, 1},{0, 0, 0, 1, 0},{0, 1, 0, 1, 1},{1, 1, 0, 0, 1},{0, 0, 0, 0, 0}};
        System.out.println(solution(board));
    }

    public static int solution(int[][] board) {
        List<Robot> visitedRobots = new ArrayList<>();
        Queue<Robot> Q = new LinkedList<>();
        Robot startRobot = new Robot(new Position(1, 2), new Position(1, 1), 0, Robot.Direction.HORIZONTAL);
        Q.offer(startRobot);
        visitedRobots.add(startRobot);

        int boardLength = board.length;
        int[][] newBoard = new int[boardLength + 2][boardLength + 2];

        // board 외각을 1로 감싸서 새로운 보드 생성
        for (int i = 0; i < boardLength + 2; i++) {
            for (int j = 0; j < boardLength + 2; j++) {
                newBoard[i][j] = 1;
            }
        }

        // 외각을 제외한 내부 좌표들을 원래 board 값으로 갱신
        for (int i = 0; i < boardLength; i++) {
            for (int j = 0; j < boardLength; j++) {
                newBoard[i + 1][j + 1] = board[i][j];
            }
        }

        while(!Q.isEmpty()) {
            Robot robot = Q.poll();
            Position head = robot.head;
            Position tail = robot.tail;
            int cost = robot.cost;

            if(reachedEndPoint(head.x, head.y, tail.x, tail.y, boardLength)) {
                return cost;
            }


            /*
                 robots 안에 상하좌우 + 회전 결과값들이 있음

                 // 중간에 방문여부 체크하는거 통과했다고 치고

                 Q.add(상 + 하 + 위쪽으로 회전 + 왼쪽으로 회전)
                 Robot robot = Q.poll(상)
                 현재 robot 을 기준으로 또 이동가능한 애들을 쭉 뽑는다.

                 Q.add(좌 + 우 + 우측회전 + 왼쪽회전)
                 Robot robot = Q.poll(하)
                 현재 Q : 위쪽회전 왼쪽회전 좌 우 우측회전 왼쪽 회전

                 ... 생략

                 즉, BFS 를 통해서 가장 인접한 애들을 먼저 탐색해서 마지막 위치에 먼저 도달하는 값이 최솟값이 된다.
                 (BFS 그래프를 생각하면 될 듯)
             */

            List<Robot> movedRobots = findMovedRobots(newBoard, robot);
            for (Robot movedRobot : movedRobots) {
                boolean isNotVisited = true;

                // 방문된 로봇 안에 이동된 로봇이 존재하는지 확인
                for(Robot visitedRobot : visitedRobots) {
                    if(visitedRobot.equals(movedRobot)) {
                        isNotVisited = false;
                        break;
                    }
                }

                // 방문하지 않았다면, 방문 처리 + Q 에 삽입 처리
                if(isNotVisited) {
                    Q.offer(movedRobot);
                    visitedRobots.add(movedRobot);
                }
            }
        }

        return 0;
    }

    /**
     * @param board 보드
     * @param robot 현재 로봇
     * @return 현재 로봇 상태에서 이동 가능한 모든 경우의 수를 계산하여 이동된 로봇들을 반환
     */
    private static List<Robot> findMovedRobots(int[][] board, Robot robot) {
        List<Robot> robots = new ArrayList<>();

        // 파라미터로 넘어온 robot 정보
        Position head = robot.head;
        Position tail = robot.tail;
        int cost = robot.cost;
        Robot.Direction direction = robot.direction;

        // 상하좌우 이동
        for (int i = 0; i < dx.length; i++) {
            int newHeadX = head.x + dx[i];
            int newHeadY = head.y + dy[i];
            int newTailX = tail.x + dx[i];
            int newTailY = tail.y + dy[i];

            if(board[newHeadX][newHeadY] == BLANK && board[newTailX][newTailY] == BLANK) {
                robots.add(new Robot(new Position(newHeadX, newHeadY), new Position(newTailX, newTailY), cost + 1, direction));
            }
        }

        /*
               T : 꼬리, H : 머리, BLANK : 빈칸

               (1,2,BLANK), (1,3,BLANK)
               (2,2,T), (2,3,H)  -> 현재 로봇 위치 가로로 누워있는 상태

               위쪽에 벽이 없으면 회전

               (2,3,H) 를 축으로 회전하면 -> (1,3,T) / (2,3,H)
               (2,2,T) 를 축으로 회전하면 -> (1,2,T) / (2,2,H) (축을 Head 로 변경)
         */

        // 현재 로봇이 가로로 놓여 있는 경우
        int[] horizontal = {-1, 1};
        if (direction.equals(Robot.Direction.HORIZONTAL)) {
            for (int i = 0; i < 2; i++) { // 위쪽으로 회전하거나, 아래쪽으로 회전
                // 위쪽 혹은 아래쪽 두 칸이 모두 비어 있다면
                if (board[head.x + horizontal[i]][head.y] == BLANK && board[tail.x + horizontal[i]][tail.y] == BLANK) {
                    // 회전축(고정 좌표)을 head 로 설정하고 tail 을 움직인다.
                    robots.add(new Robot(new Position(head.x, head.y), new Position(head.x + horizontal[i], head.y), cost + 1, Robot.Direction.VERTICAL));
                    robots.add(new Robot(new Position(tail.x, tail.y), new Position(tail.x + horizontal[i], tail.y), cost + 1, Robot.Direction.VERTICAL));
                }
            }
        }

        /*
               T : 꼬리, H : 머리, BLANK : 빈칸

               (1,2,BLANK), (1,3,T)  -> 현재 로봇 위치
               (2,2,BLANK), (2,3,H)  -> 현재 로봇 위치 세로로 누워있는 상태

               왼쪽에 벽이 없으면 회전

               (2,3,H) 를 축으로 회전하면 -> (2,2,T) - (2,3,H)
               (1,3,T) 를 축으로 회전하면 -> (1,2,T) / (1,3,H)  (축을 Head 로 변경)
         */

        // 현재 로봇이 세로로 놓여 있는 경우
        int[] vertical = {-1, 1};
        if (direction.equals(Robot.Direction.VERTICAL)) {
            for (int i = 0; i < 2; i++) { // 왼쪽으로 회전하거나, 오른쪽으로 회전
                // 왼쪽 혹은 오른쪽 두 칸이 모두 비어 있다면
                if (board[head.x][head.y + vertical[i]] == BLANK && board[tail.x][tail.y + vertical[i]] == BLANK) {
                    // 회전축(고정 좌표)을 head 로 설정하고 tail 을 움직인다.
                    robots.add(new Robot(new Position(head.x, head.y), new Position(head.x, head.y + vertical[i]), cost + 1, Robot.Direction.HORIZONTAL));
                    robots.add(new Robot(new Position(tail.x, tail.y), new Position(tail.x, tail.y + vertical[i]), cost + 1, Robot.Direction.HORIZONTAL));
                }
            }
        }

        return robots;
    }

    /**
     * @param headX 머리 X 좌표
     * @param headY 머리 Y 좌표
     * @param tailX 꼬리 X 좌표
     * @param tailY 꼬리 Y 좌표
     * @param boardLength 보드 크기
     * @return 종료 지점에 도달했는지 여부
     */
    private static boolean reachedEndPoint(int headX, int headY, int tailX, int tailY, int boardLength) {
        return (headX == boardLength && headY == boardLength) || (tailX == boardLength && tailY == boardLength);
    }
}
