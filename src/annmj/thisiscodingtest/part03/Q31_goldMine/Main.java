package annmj.thisiscodingtest.part03.Q31_goldMine;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static int T;
    static int N, M;
    static int[][] A;
    static int[][] dp; // dp[i][j] : dp[i] 를 만족할 때의 인덱스가 j임
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        T = scanner.nextInt();
        for (int i = 0; i < T; i++) {
            input();
            process();
        }
    }

    private static void process() {
        for (int i = 1; i <= N; i++) {
            dp[i][1] = A[i][1];
        }

        for (int i = 2; i <= M; i++) {
            for (int j = 1; j <= N; j++) {
                dp[j][i] = getMaxByRight(i, j) + A[j][i];
            }
        }

        int[] last = new int[N+1];
        for (int i = 1; i <= N; i++) {
            last[i] = dp[i][M];
        }
        Arrays.sort(last);
        System.out.println(last[last.length-1]);
    }

    private static int getMaxByRight(int column, int row) {
        // 우측 인덱스 기준으로 좌측의 3개 영역을 탐색
        int leftUp = 0;
        int left = 0;
        int leftDown = 0;
        // case 1 : 좌측 상단에서 온 경우
        if (row - 1 > 0) {
            leftUp = dp[row - 1][column - 1];
        }

        // case 2 : 좌측에서 온 경우
        if (row - 1 > 0) {
            left = dp[row][column -1];
        }
        // case 3 : 좌측 하단에서 온 경우
        if (row + 1 < N + 1) {
            leftDown = dp[row + 1][column-1];
        }

        return Math.max(Math.max(leftUp, left), leftDown);
    }

    private static void input() {
        N = scanner.nextInt();
        M = scanner.nextInt();
        A = new int[N + 1][M + 1];
        dp = new int[N + 1][M + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                A[i][j] = scanner.nextInt();
            }
        }
    }
}
