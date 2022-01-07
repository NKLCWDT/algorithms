package seunguk.thisiscodingtest.part03.Q31_goldmine;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int i = 0; i < T; i++) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            int[][] arr = new int[n][m];
            int[][] dp = new int[n][m];
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < m; k++) {
                    arr[j][k] = sc.nextInt();
                }
            }

            // 열이 0인 값부터 채워야하므로 첫번째 열은 arr 값으로 초기화 시켜준다.
            for (int j = 0; j < n; j++) {
                dp[j][0] = arr[j][0];
            }

            // dp만들어줄때 열 1개 돌때 행을 모두 탐색해야한다.
            // 왜냐하면 dp 테이블 채울때 열마다 모든 행을 탐색하면서 돌기 때문이다.
            for (int k = 1; k < m; k++) {
                for (int j = 0; j < n; j++) {
                    if (j == 0) {
                        dp[j][k] = Math.max(dp[j][k-1], dp[j+1][k-1]) + arr[j][k]; // 중간 아래
                    } else if (j == n-1){
                        dp[j][k] = Math.max(dp[j][k-1], dp[j-1][k-1]) + arr[j][k]; // 중간, 위
                    } else {
                        dp[j][k] = Math.max(dp[j][k-1], Math.max(dp[j+1][k-1], dp[j-1][k-1])) + arr[j][k]; // 중간 아래 위
                    }

                }
            }
            int result = 0;
            for (int j = 0; j < n; j++) {

                result = Math.max(result, dp[j][m-1]);
            }
            System.out.println(result);
        }
    }
}

/*
2
3 4
1 3 3 2 2 1 4 1 0 6 4 7
4 4
1 3 1 5 2 2 4 1 5 0 2 3 0 6 1 2
* */