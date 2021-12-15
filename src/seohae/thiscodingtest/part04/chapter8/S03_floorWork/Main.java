package seohae.thiscodingtest.part04.chapter8.S03_floorWork;

import java.util.Scanner;

/*
    가로 N, 세로 2

    덮개 1 X 2, 2 X 1, 2 X 2

    N 을 입력받음

    가로길이 i - 1 --->  2 X 1 (1개)
    가로길이 i - 2 --->  1 X 2, 2 X 2 (2개)

    d[i - 1] + d[i - 2] * 2
 */
public class Main {
    static int X; /* 정수 X */
    static int[] DP; /* i의 최소 연산 횟수를 저장하는 배열 */

    public static void main(String[] args) {
        input();

        DP[0] = 0;
        DP[1] = 1; // 2 X 1 : 1가지
        DP[2] = 3; // (2 X 1 3개  + 2 X 2 + 2 X 1 + 2 X 1 + 2 X 2 ) : 3가지

        for (int i = 3; i < DP.length; i++) {
            DP[i] = (DP[i - 1] + DP[i - 2] * 2) % 796796;
        }

        System.out.println(DP[X]);
    }

    private static void input() {
        Scanner sc = new Scanner(System.in);
        X = sc.nextInt();

        DP = new int[X + 1];
    }
}
