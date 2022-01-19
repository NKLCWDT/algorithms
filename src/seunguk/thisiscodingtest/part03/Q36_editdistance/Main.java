package seunguk.thisiscodingtest.part03.Q36_editdistance;

import java.util.Scanner;

// 푸는법을 떠올리지 못해 답지참고..

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String A = sc.nextLine();
        String B = sc.nextLine();
        int[][] dp = new int[A.length()][B.length()];

        dp[0][0] = 0;

        for (int i = 1; i < dp.length; i++) {
            dp[i][0] = i;
        }

        for (int i = 0; i < dp[0].length; i++) {
            dp[0][i] = i;
        }

        // 최소 편집 거리 계산
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[i].length; j++) {

                // 문자가 같다면 왼쪽 위에 해당하는 수를 그대로 대입
                if (A.charAt(i) == B.charAt(j)) {
                    dp[i][j] = dp[i-1][j-1];
                }
                // 문자가 다르다면 3가지 경우 중에서 최소값 찾기
                else { // 삽입(왼쪽), 삭제(위쪽), 교체(왼쪽 위) 중에서 최소 비용을 찾아 대입
                    dp[i][j] = Math.min(dp[i][j-1], Math.min(dp[i-1][j], dp[i-1][j-1])) + 1;
                }
            }
        }
        System.out.println(dp[A.length()-1][B.length()-1]);

    }
}
