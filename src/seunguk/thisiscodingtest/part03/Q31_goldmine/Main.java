package seunguk.thisiscodingtest.part03.Q31_goldmine;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int i = 0; i < T; i++) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            int[][] dp = new int[n][m];
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < m; k++) {
                    dp[j][k] = sc.nextInt();
                }
            }

            for (int j = 0; j < n; j++) {
                for (int k = 1; k < m; k++) {
                    if (j == 0) {
                        dp[j][k] += Math.max(dp[j][k-1], dp[j+1][k-1]); // 중간 아래
                    } else if (j == n-1){
                        dp[j][k] += Math.max(dp[j][k-1], dp[j-1][k-1]); // 중간, 위
                    } else {
                        dp[j][k] += Math.max(dp[j][k-1], Math.max(dp[j+1][k-1], dp[j-1][k-1])); // 중간 아래 위
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