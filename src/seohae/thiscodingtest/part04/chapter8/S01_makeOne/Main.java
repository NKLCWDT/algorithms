package seohae.thiscodingtest.part04.chapter8.S01_makeOne;

import java.util.Scanner;

/*
    5로 나누어 떨어지면 5로 나눔
    3으로 나누어 떨어지면 3으로 나눔
    2로 나누어 떨어지면 2로 나눔
    1을 뻄

    위 4가지 연산을 최소로 사용하여 1로 만들기
 */
public class Main {
    static int X; /* 정수 X */
    /** 힌트 : 백트래킹 */
    static int[] DP; /* i의 최소 연산 횟수를 저장하는 배열 */

    public static void main(String[] args) {
        input();

        /* 0, 1 일때는 종료 */
        for (int i = 2; i < DP.length; i++) {
            /**
             * DP[]에 저장되어있는 이전에 연산된 값을 불러와서 사용하는 방법
             * DP[i] = Math.min(DP[i - 1] + 1, DP[i / 2] + 1, DP[i / 3] + 1);
             *
             * 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26
             * 0, 0, 1, 1, 2, 1, 2, 3, 3, 2, 2,  3,  3,  4,  4,  2,  3,  4,  3,  4,  3,  4,  4,  5,  4,  2,  3
             */
            /* -1 을 한 결과값 가져오기 */
            DP[i] = DP[i - 1] + 1;

            /* -1 을 한 결과값 vs 나눴을때의 결과값 중 최소값 구하기 */
            if (i % 5 == 0) {
                DP[i] = Math.min(DP[i], DP[i / 5] + 1);
            }

            if (i % 3 == 0) {
                DP[i] = Math.min(DP[i], DP[i / 3] + 1);
            }

            if (i % 2 == 0) {
                DP[i] = Math.min(DP[i], DP[i / 2] + 1);
            }
        }

        System.out.println(DP[X]);
    }

    private static void input() {
        Scanner sc = new Scanner(System.in);
        X = sc.nextInt();

        DP = new int[X + 1];
    }
}
