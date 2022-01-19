package annmj.thisiscodingtest.part03.Q36_editdistance.solution2;

import java.util.Scanner;

public class Main {
    static String A, B;
    public static void main(String[] args) {
        input();
        process();
    }

    private static void process() {
        int[][] dp = new int[A.length()+1][B.length()+1];

        for (int i = 0; i <= A.length(); i++) {
            dp[i][0] = i;
        }

        for (int i = 0; i <= B.length(); i++) {
            dp[0][i] = i;
        }

        for (int i = 1; i <= A.length(); i++) {
            for (int j = 1; j <= B.length(); j++) {
                if (A.charAt(i-1) == B.charAt(j-1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 1 + Math.min(Math.min(dp[i - 1][j - 1], dp[i][j - 1]), dp[i - 1][j]);
                }
            }
        }

        System.out.println(dp[A.length()][B.length()]);
    }

    private static void input() {
        Scanner scanner = new Scanner(System.in);
        A = scanner.next();
        B = scanner.next();
    }

}
