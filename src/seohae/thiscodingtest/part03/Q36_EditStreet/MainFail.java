package seohae.thiscodingtest.part03.Q36_EditStreet;

import java.util.Scanner;

/**
 * 두개의 문자열 A, B
 *
 * 문자열 A를 편집하여 문자열 B 를 만든다.
 *
 * 세 연산 중 한번에 하나씩 선택 가능
 * 삽입, 삭제, 교체
 *
 * 편집 거리: 문자열 A를 편집하여 문자열 B로 만들기 위해 사용한 연산의 수
 * 문자열 A를 문자열 B로 만드는 최소 편집 거리를 계산
 *
 * sunday , saturday
 * 1) insert a -> saunday
 * 2) insert t -> satunday
 * 3) replace -> saturday
 */
public class MainFail {
    static String A;
    static String B;
    static int shortStartIndex; // A, B 중에 짧은 것의 start 만큼
    static boolean isShortedA = false; // A 가 B 보다 짧은지 여부
    static int replaceCnt;
    static int lengthDiff;

    /*
     디버깅 해보기

     두 길이 차이 : 2
     더 짧은것 : A
       sunday (A)
     saturday (B)

     1) y : true
     2) a : true
     3) d : true
     4) n != r  : n replace r (cnt : 1)
     5) u : true
     6) s != t : s replace t  (cnt : 1)
     7) B 배열의 남은것 : sa
     : 결국 결과는 : 4

     replace 하는것이 짧은 쪽의 길이까지 수행하고, 나머지를 insert 하면 될것같다고 생각했는데
     6)번 이후부터 반례

     6) A의 s, u 사이에 a, t  를 insert 해주면 최소횟수 3이 나옴
     */
    public static void main(String[] args) {
        input();

        // A :   sunday
        // B : saturday
        if (A.length() > B.length()) {
            shortStartIndex = B.length() - 1;

            // 길이의 차이 저장
            lengthDiff = A.length() - B.length();
        } else {
            shortStartIndex = A.length() - 1;

            // A 가 더 짧음 여부를 저장
            isShortedA = true;
            lengthDiff = B.length() - A.length();
        }

        // arrA 의 길이가 5일때
        // arrB 의 길이가 9일때
        for (int i = shortStartIndex; i >= 0; i--) { // i 가 5부터 시작
            char targetA = A.charAt(i);
            char targetB = B.charAt(i + lengthDiff);

            if (targetA == targetB) { // 같을때 패스
                continue;
            }

            // 같지 않을때
            replaceCnt = replaceCnt + 1; // replace
        }

        // 긴 문자열의 길이 중 짧은 문자열보다 많은 문자열 개수
        // replace 후 delete 될 개수를 더한다.
        int remainder = isShortedA ? B.length() - A.length() : A.length() - B.length();

        System.out.println(replaceCnt + remainder);
    }

    private static void input() {
        Scanner scanner = new Scanner(System.in);
        A = scanner.next();
        B = scanner.next();
    }
}
