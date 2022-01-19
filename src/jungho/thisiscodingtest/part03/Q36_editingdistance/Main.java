package jungho.thisiscodingtest.part03.Q36_editingdistance;

import java.util.Scanner;

// 편집 거리
public class Main {

    static String A;
    static String B;

    public static void main(String[] args) {
        input();
        System.out.println(solution());
    }

    static void input() {
        Scanner sc = new Scanner(System.in);
        A = sc.nextLine();
        B = sc.nextLine();
    }

    static int solution() {
        int n = A.length();
        int m = B.length();

        // 다이나믹 프로그래밍을 위한 2차원 DP 테이블 초기화
        int[][] dp = new int[n + 1][m + 1];

        
        /*
            DP 테이블 초기 설정
            빈 문자열을 가지고 해당 문자를 만들 수 있는 최소 편집 거리로 초기화
            Ex. dp[2][0] 에 저장된 값은 
                빈 문자열을 가지고 su 를 만들 수 있는 최소 편집거리를 의미
         */
        for (int i = 1; i <= n; i++) {
            dp[i][0] = i;
        }
        for (int k = 1; k <= m; k++) {
            dp[0][k] = k;
        }

        // 최소 편집 거리 계산
        for (int i = 1; i <= n; i++) {
            for (int k = 1; k <= m; k++) {
                // 문자가 같다면, 왼쪽 위에 해당하는 수를 그대로 대입
                if (A.charAt(i - 1) == B.charAt(k - 1)) {
                    dp[i][k] = dp[i - 1][k - 1];
                }
                
                // 문자가 다르다면, 세 가지 경우 중에서 최솟값 찾기
                else { // 삽입(왼쪽), 삭제(위쪽), 교체(왼쪽 위) 중에서 최소 비용을 찾아 대입
                    dp[i][k] = 1 + Math.min(dp[i][k - 1], Math.min(dp[i - 1][k], dp[i - 1][k - 1]));
                }
            }
        }

        return dp[n][m];
    }
}
