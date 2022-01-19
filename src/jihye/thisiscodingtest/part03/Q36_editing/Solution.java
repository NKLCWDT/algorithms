package jihye.thisiscodingtest.part03.Q36_editing;

import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str1 = sc.nextLine();
        String str2 = sc.nextLine();

        int answer = editDistance(str1, str2);
        System.out.println(answer);
    }

    public static int editDistance(String str1, String str2) {
        int length1 = str1.length();
        int lenght2 = str2.length();

        int[][] dp = new int[length1 + 1][lenght2 + 1];

        // 아무것도 없는데서 str1까지 만드는데 드는 값
        for (int i = 1; i < length1 + 1; i++) {
            dp[i][0] = i;
        }

        // 아무것도 없는데서 str2까지 만드는데 드는 값
        for (int j = 1; j < lenght2 + 1; j++) {
            dp[0][j] = j;
        }

        //최소 편집 거리 계산
        for (int i = 1; i < length1 + 1; i++) {
            for (int j = 1; j < lenght2 + 1; j++) {

                // 만약 문자가 같다면 왼쪽위에 해당하는 수 그대로 대입
                // 왼쪽위는 문자 더하기 str1에 있는 character 더하기 전 & str2에 있는 character 더하기 전
                // su에서 sa로 바뀔 때 왼쪽 위는 s만 있는 상태에서 바꾸는 것 고려
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {  // 문자가 다를경우
                    //dp[i][j-1]은 삽입하는 케이스
                    //dp[i-1][j]는 삭제하는 케이스
                    //dp[i-1][j-1]은 교체하는 케이스
                    dp[i][j] = 1 + Math.min(Math.min(dp[i][j - 1], dp[i - 1][j]), dp[i - 1][j - 1]);
                }
            }
        }
        return dp[length1][lenght2];
    }
}
