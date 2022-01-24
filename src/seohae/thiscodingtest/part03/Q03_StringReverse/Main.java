package seohae.thiscodingtest.part03.Q03_StringReverse;

import java.util.Scanner;

/**
 * https://www.acmicpc.net/problem/1439
 */

/*
 0, 1로 만 이루어진 문자열 S

 문자열 S의 모든 숫자를 같게만들자.
 S에서 연속된 하나 이상의 숫자를 잡고 모두 뒤집는것이다.
 뒤집는 것은 1 -> 0, 0 -> 1 로 바꾸는것을 의미한다.

 0001100
 1) 1110011
 2) 1111111

 총 2번
 */
public class Main {
    static String S;

    public static void main(String[] args) {
        input();

        int zeroChangeCnt = 0;
        int oneChangeCnt = 0;

        // 마지막 문자열에 대해서 +1 체크
        if (S.charAt(S.length() - 1) == '0') {
            zeroChangeCnt = 1;
        } else {
            oneChangeCnt = 1;
        }

        char before = S.charAt(0);
        for (int i = 1; i < S.length(); i++) {
            if (before != S.charAt(i)) { // change 되는 시점
                if (before == '0') { // 0 -> 1일때 개수 ++
                    zeroChangeCnt++;
                } else { // 1 -> 0일때 개수 ++
                    oneChangeCnt++;
                }
            }

            before = S.charAt(i);
        }

        if (oneChangeCnt != 0 && zeroChangeCnt != 0) {
            System.out.println(Math.min(zeroChangeCnt, oneChangeCnt));
        } else {
            System.out.println(0);
        }
    }

    public static void input() {
        Scanner sc = new Scanner(System.in);
        S = sc.next();
    }
}
