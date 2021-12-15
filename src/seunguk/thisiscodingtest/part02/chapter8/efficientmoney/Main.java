package seunguk.thisiscodingtest.part02.chapter8.efficientmoney;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int[] coin = new int[N];
        int[] dp = new int[M+1];
        for (int i = 0; i < N; i++) {
            coin[i] = sc.nextInt();
        }
        for (int i = 1; i < M+1; i++) { // 1부터 10001로 초기화 해줘야 dp[0] 값을 이용해 다른값을 채워나간다.
            dp[i] = 10001;
        }
        for (int i = 0; i < N; i++) {
            for (int j = coin[i]; j < M+1; j++) { // 화폐값 부터 M까지 돈다.
                dp[j] = Math.min(dp[j], dp[j - coin[i]] + 1); // 현재 들어있는 값과 현재 화폐로 만들 수 있는 경우를 비교해 작은 값을 넣는다.
            }
        }
        if (dp[M] == 10001) {
            System.out.println(-1);
        } else {
            System.out.println(dp[M]);
        }
    }
}
