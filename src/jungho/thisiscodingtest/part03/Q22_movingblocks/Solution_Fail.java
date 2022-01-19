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
public class Solution_Fail {

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
        Robot startRobot = new Robot(new Position(0, 1), new Position(0, 0), 0, Robot.Direction.HORIZONTAL);
        Q.offer(startRobot);
        visitedRobots.add(startRobot);
        int boardLength = board.length;

        while(!Q.isEmpty()) {
            Robot robot = Q.poll();
            Position head = robot.head;
            Position tail = robot.tail;
            int cost = robot.cost;

            if(reachedEndPoint(head.x, head.y, tail.x, tail.y, boardLength)) {
                System.out.println(visitedRobots.size());
                System.out.println(Q.size());
                return cost;
            }

            // 방문처리 부분 답지 참고
            List<Robot> movedRobots = findMovedRobots(board, robot);
            for (Robot movedRobot : movedRobots) {
                boolean isNotVisited = true;

                System.out.println("==========================");
                System.out.println("headX : " + movedRobot.head.x);
                System.out.println("headY : " + movedRobot.head.y);
                System.out.println("tailX : " + movedRobot.tail.x);
                System.out.println("tailY : " + movedRobot.tail.y);
                System.out.println("==========================");

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

            if(isInGraph(newHeadX, newHeadY, newTailX, newTailY, board.length)) {
                // 벽이 아니면 이동 가능
                if(board[newHeadX][newHeadY] != WALL && board[newTailX][newTailY] != WALL) {
                    robots.add(new Robot(new Position(newHeadX, newHeadY), new Position(newTailX, newTailY), cost + 1, direction));
                }
            }
        }

        // Case1. 로봇이 가로로 누워있는 경우
        if(direction.equals(Robot.Direction.HORIZONTAL)) {
            int topHeadX = head.x - 1;
            int topHeadY = head.y;
            int topTailX = tail.x - 1;
            int topTailY = tail.y;

            // 상단에 벽이 없이 없으면 -> 위쪽으로 회전 -> 방향이 가로에서 세로로 변경됨
            if(hasNotWalls(topHeadX, topHeadY, topTailX, topTailY, board)) {
                // T-H 로 누워 있는 상태에서, head 를 축으로 회전한 경우
                // 축으로 하는 곳을 head 로 설정

                if(hasNotWalls(head.x, head.y, tail.x - 1, tail.y + 1, board)) {
                    robots.add(new Robot(new Position(head.x, head.y), new Position(tail.x - 1, tail.y + 1), cost + 1, Robot.Direction.VERTICAL));
                }

                // T-H 로 누워 있는 상태에서, tail 을 축으로 회전한 경우
                // 축으로 하는 곳을 head 로 설정
                if(hasNotWalls(tail.x, tail.y, head.x - 1, head.y - 1, board)) {
                    robots.add(new Robot(new Position(tail.x, tail.y), new Position(head.x - 1, head.y - 1), cost + 1, Robot.Direction.VERTICAL));
                }
            }

            int bottomHeadX = head.x + 1;
            int bottomHeadY = head.y;
            int bottomTailX = tail.x + 1;
            int bottomTailY = tail.y;

            // 하단에 벽이 없으면 -> 아래쪽으로 회전 -> 방향이 가로에서 세로로 변경됨
            if(hasNotWalls(bottomHeadX, bottomHeadY, bottomTailX, bottomTailY, board)) {
                // T-H 로 누워 있는 상태에서, head 를 축으로 회전한 경우
                // 축으로 하는 곳을 head 로 설정
                if(hasNotWalls(head.x, head.y, head.x + 1, head.y + 1, board)) {
                    robots.add(new Robot(new Position(head.x, head.y), new Position(tail.x + 1, tail.y + 1), cost + 1, Robot.Direction.VERTICAL));
                }

                // T-H 로 누워 있는 상태에서, tail 을 축으로 회전한 경우
                // 축으로 하는 곳을 head 로 설정
                if(hasNotWalls(tail.x, tail.y, head.x + 1, head.y - 1, board)) {
                    robots.add(new Robot(new Position(tail.x, tail.y), new Position(head.x + 1, head.y - 1), cost + 1, Robot.Direction.VERTICAL));
                }
            }
        }

        // Case2. 로봇이 세로로 누워있는 경우
        if(direction.equals(Robot.Direction.VERTICAL)) {
            int leftHeadX = head.x;
            int leftHeadY = head.y - 1;
            int leftTailX = tail.x;
            int leftTailY = tail.y - 1;

            // 왼쪽에 벽이 없는지 -> 왼쪽으로 회전 -> 방향이 세로에서 가로로 변경됨
            if(hasNotWalls(leftHeadX, leftHeadY, leftTailX, leftTailY, board)) {
                // T/H 로 세워져 있는 상태에서, head 를 축으로 회전한 경우
                // 축으로 하는 곳을 head 로 설정
                if(hasNotWalls(head.x, head.y, tail.x + 1, tail.y - 1, board)) {
                    robots.add(new Robot(new Position(head.x, head.y), new Position(tail.x + 1, tail.y - 1), cost + 1, Robot.Direction.HORIZONTAL));
                }

                // T-H 로 누워있는 상태에서, tail 을 축으로 회전한 경우
                // 축으로 하는 곳을 head 로 설정
                if(hasNotWalls(tail.x, tail.y, head.x - 1, head.y - 1, board)) {
                    robots.add(new Robot(new Position(tail.x, tail.y), new Position(head.x - 1, head.y - 1), cost + 1, Robot.Direction.HORIZONTAL));
                }
            }

            int rightHeadX = head.x;
            int rightHeadY = head.y + 1;
            int rightTailX = tail.x;
            int rightTailY = tail.y + 1;

            // 오른쪽에 벽이 없는지 -> 오른쪽으로 회전 -> 방향이 세로에서 가로로 변경됨
            if(hasNotWalls(rightHeadX, rightHeadY, rightTailX, rightTailY, board)) {
                // T/H 로 세워져 있는 상태에서, head 를 축으로 회전한 경우
                // 축으로 하는 곳을 head 로 설정
                if(hasNotWalls(head.x, head.y, tail.x + 1, tail.y + 1, board)) {
                    robots.add(new Robot(new Position(head.x, head.y), new Position(tail.x + 1, tail.y + 1), cost + 1, Robot.Direction.HORIZONTAL));
                }

                // T-H 로 누워있는 상태에서, tail 을 축으로 회전한 경우
                // 축으로 하는 곳을 head 로 설정
                if(hasNotWalls(tail.x, tail.y, head.x - 1, head.y + 1, board)) {
                    robots.add(new Robot(new Position(tail.x, tail.y), new Position(head.x - 1, head.y + 1), cost + 1, Robot.Direction.HORIZONTAL));
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
     * @param board 보드
     * @return 벽이 존재하는지 여부
     */
    private static boolean hasNotWalls(int headX, int headY, int tailX, int tailY, int[][] board) {
        return isInGraph(headX, headY, tailX, tailY, board.length) &&
                board[headX][headY] != WALL && board[tailX][tailY] != WALL;
    }

    /**
     * @param headX 머리 X 좌표
     * @param headY 머리 Y 좌표
     * @param tailX 꼬리 X 좌표
     * @param tailY 꼬리 Y 좌표
     * @param boardLength 보드 크기
     * @return 그래프 안에 존재하는지 여부
     */
    private static boolean isInGraph(int headX, int headY, int tailX, int tailY, int boardLength) {
        return headX >= 0 && headY >= 0 && tailX >= 0 && tailY >= 0 &&
               headX < boardLength && headY < boardLength && tailX < boardLength && tailY < boardLength;
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
        return (headX == boardLength - 1 && headY == boardLength - 1) || (tailX == boardLength - 1 && tailY == boardLength - 1);
    }
}
