package seunguk.chapter8.floorwork;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        int[] dp = new int[N+1];
        dp[1] = 1;
        dp[2] = 3;
        for (int i = 3; i < N+1; i++) {
            // 타일 2X1, 1x2 만 있을경우 피보나치 수와 똑같은데 2x2 타일만 추가 되었고
            // 2x2 타일은 1x2 타일과 똑같으므로 *2만 해주면 된다.
            dp[i] = (dp[i-1] + dp[i-2] * 2) % 796796;
        }
        System.out.println(dp[N]);
    }
}
