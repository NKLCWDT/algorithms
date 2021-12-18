package seohae.thiscodingtest.part02.chapter8.S04_moneyConstruct;

import java.util.Arrays;
import java.util.Scanner;

/*
   N가지 종류의 화폐
   합이 M원

   가장 최소의 화폐 개수
 */
public class Main {
    static int N; /* 화폐 개수 */
    static int M; /* 총 합 */
    static int[] coins; /* 화폐 배열 */
    static int[] DP; /* i의 최소 연산 횟수를 저장하는 배열 */

    public static void main(String[] args) {
        input();

        Arrays.fill(DP, 10001);
        DP[0] = 0;

        /* 코인의 개수 만큼 */
        for (int i = 0; i < N; i++) { // 0(=2), 1(=3)
            /* 코인 값의 시작부터, DP 배열만큼 */
            for (int j = coins[i]; j < DP.length; j++) { // 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15
                // DP[2] = Math.min(DP[2], DP[2 - 2] + 1)
                // DP[3] = Math.min(DP[3], DP[3 - 2] + 1)
                // DP[4] = Math.min(DP[4], DP[4 - 2] + 1)
                // DP[5] = Math.min(DP[5], DP[5 - 2] + 1)

                // DP[3] = Math.min(DP[3], DP[3 - 3] + 1)
                // DP[4] = Math.min(DP[4], DP[4 - 3] + 1)
                // DP[5] = Math.min(DP[5], DP[5 - 3] + 1)
                DP[j] = Math.min(DP[j], DP[j - coins[i]] + 1);
            }
        }

        System.out.println(DP[M] == 10001 ? -1 : DP[M]);
    }

    private static void input() {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();

        /* 배열 */
        coins = new int[N];
        for (int i = 0; i < N; i++) {
            coins[i] = sc.nextInt();
        }

        DP = new int[M + 1];
    }
}
