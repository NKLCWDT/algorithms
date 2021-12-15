package seohae.thiscodingtest.part03.Q07_LuckyStraight;

import java.util.Scanner;
import java.util.stream.IntStream;

/*
   럭키 스트레이트
   럭키 스트레이트 기술이란?
   (특정 조건 만족 필요)
   현재 캐릭터의 점수를 N 이라고 할때 자릿수를 기준으로 점수 N을
   반으로 나누어 왼쪽 부분의 각 자릿수의 합과 오른쪽 부분의 각 자릿수의 합을 더한 값이 동일한 상황

   123402 일때
   왼쪽부분 : 123
   오른쪽부분 : 402

   123 = 6
   402 = 6

   이때 럭키스트레이트 기술을 사용 가능하다.

   N은 항상 짝수다.

 */
public class Main {
    private static int n;
    private static String[] arr;

    public static void main(String[] args) {
        input();

        // n = 123402
        int length = arr.length; // 6
        int mid = length / 2; // 3

        // 0, 1, 2
        int first = IntStream.range(0, mid).map(i -> Integer.parseInt(arr[i])).sum();
        // 3, 4, 5
        int second = IntStream.range(mid, length).map(i -> Integer.parseInt(arr[i])).sum();

        System.out.println(first == second ? "LUCKY" : "READY");
    }

    private static void input() {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();

        /* N은 항상 짝수임을 기억 */

        arr = String.valueOf(n).split("");
    }
}
