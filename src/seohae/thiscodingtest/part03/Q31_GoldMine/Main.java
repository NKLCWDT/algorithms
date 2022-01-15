package seohae.thiscodingtest.part03.Q31_GoldMine;

import java.util.*;

/*
   첫번째 열부터 금광 캐기
   첫번째 열의 어느 행에서든 출발할 수 있다.

   이동가능
   오른쪽위 -> (0, 1) -> (-1, 0)
   오른쪽  -> (0, 1)
   오른쪽아래 -> (0, 1) -> (1, 0)

   목표 : 가장 많은 금 채굴

   예시
   (2, 1) -> (3, 2) -> (3, 3) -> (3, 4)

 */
public class Main {
    static int total;
    static int N;
    static int M; // (N, M) : 금의 개수
    static int[][] graph; // 배열
    static int[][] DP;
    static Map<Integer, List<Move>> moveCase = new HashMap<>();

    static class Move {
        int x;
        int y;

        public Move(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) {
        input();
    }

    /**
     입력값
     1
     3 4
     1 3 3 2 2 1 4 1 0 6 4 7

     -> 결과 : 19

     1
     4 4
     1 3 1 5 2 2 4 1 5 0 2 3 0 6 1 2

     -> 결과 : 16
     */
    private static void input() {
        /* M의 각 row 별 움직일 수 있는 좌표 */
        moveCase.put(0, List.of(new Move(0, 1), new Move(1, 1)));
        moveCase.put(1, List.of(new Move(-1, 1), new Move(0, 1), new Move(1, 1)));
        moveCase.put(2, List.of(new Move(-1, 1), new Move(1, 1)));

        Scanner sc = new Scanner(System.in);
        total = sc.nextInt();

        for (int cur = 0; cur < total; cur++) {
            N = sc.nextInt();
            M = sc.nextInt();
            DP = new int[N][M];

            graph = new int[N][M];

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    graph[i][j] = sc.nextInt();
                }
            }

            // 호출
            int result = exec();
            System.out.println(result);
        }
    }

    private static int exec() {
        /* 마지막에서 두번째 열이 마지막 열을 계산하므로 */
        for (int j = 0; j < M - 1; j++) { /* 열 */
            for (int i = 0; i < N; i++) { /* 행 */
                List<Move> moveList = moveCase.get(i);

                if (moveList != null) { // 이동이 가능한 경우
                    int val = graph[i][j];

                    for (Move move : moveList) {
                        /* 움직인 좌표 */
                        int nx = i + move.x;
                        int ny = j + move.y;

                        if (nx >= 0 && ny > 0 && nx < N && ny < M) {
                            int newVal = val + graph[nx][ny];

                            /* 최댓값을 저장 */
                            DP[nx][ny] = Math.max(DP[nx][ny], newVal);
                        }
                    }
                }
            }

            /*
               하나의 열의 연산이 끝날때마다 해당 열에 해당하는 DP[j + 1]을 graph 배열의 row 에 업데이트해준다.
             */
            for (int q = 0; q < N; q++) {
                if (j + 1 < M) {
                    int val = DP[q][j + 1];
                    graph[q][j + 1] = val;
                }
            }

        }

        /* 결과값 추출 : 마지막 열에서 최댓값 추출 */
        int result = 0;
        for (int i = 0; i < N; i++) {
            result = Math.max(result, DP[i][M - 1]);
        }

        return result;
    }
}
