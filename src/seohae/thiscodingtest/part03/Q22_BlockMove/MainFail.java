package seohae.thiscodingtest.part03.Q22_BlockMove;

import java.util.LinkedList;
import java.util.Queue;

/*
회전 정보

(1,1)(1,2)
왼쪽 (1,1)의 아래 (2,1)이 0이라면 -> 회전 가능 -> (1,2)(2,2) :대각선 이동
(1,1) -> (2,2) 이동인건데. 하 -> 우 로 이동
 */
public class MainFail {
    static int n;

    /* 상, 하, 좌, 우 */
    static final int[] dx = {0, 1, 0, -1};
    static final int[] dy = {1, 0, -1, 0};

    /* 회전 왼쪽으로 90, 오른쪽으로 90, 아래로 90,래위로 90*/
    static final int[] rdx = {-1, 1, 1, -1};
    static final int[] rdy = {1, 1, -1, -1};

    static boolean[][][] visit;

    public static void main(String[] args) {
        MainFail main = new MainFail();

        // 0 : 빈칸, 1: 벽
        int[][] paramArr = new int[][]{
                                        {0, 0, 0, 0, 0, 0},
                                        {0, 0, 0, 0, 1, 1},
                                        {0, 0, 0, 0, 1, 0},
                                        {0, 0, 1, 0, 1, 1},
                                        {0, 1, 1, 0, 0, 1}
                                      };
        main.solution(paramArr);
    }

    public int solution(int[][] board) {
        n = board.length;

        int answer = 0;

        // 로봇의 처음 위치는 (1,1)(1,2)
        // 목적지 (N-1, N-1)
        bfs(board);

        return answer;
    }

    static int bfs(int[][] board) {
        Queue<Robot> queue = new LinkedList<>();
        queue.add(new Robot(0, 0)); // 초기 로봇 위치

        // 방향성 4개에 대한 visited array 를 정의
        // Right, Down, Up, Left 각 2차원 배열로 존재
        visit = new boolean[n][n][4];

        visit[0][0][0] = true;
        int x = 0;
        int y = 0;

        while (!queue.isEmpty()) {
            Robot robot = queue.poll();

            x = robot.getX();
            y = robot.getY();

            int nx;
            int ny;

            for (int i = 0; i < 4; i++) { // 상하좌우 이동
                nx = x + dx[i];
                ny = y + dy[i];

                if (nx >= 0 && ny >= 0 && nx < n && ny < n) {
                    continue; // 벽 밖으로 나갔는지 체크
                }

                queue.add(new Robot(nx, ny));
            }
        }

        return -1;
    }
}

class Robot {
    private int x;
    private int y;

    Robot(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}

