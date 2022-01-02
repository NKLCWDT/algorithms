package jungho.thisiscodingtest.part03.Q02_multiplyorplus;

/*
S : 각 자리가 숫자 0~9 로만 이루어진 문자열
왼쪽부터 오른쪽으로 하나씩 모든 숫자를 확인 -> 숫자 사이에 X OR + 를 넣어 가장 큰 수 만들기
모든 연산은 왼쪽부터 차례대로 계산

피연산자가 0이나 1이 아니라면 x
0이나 1이면 +

00299 = 18 x 9 = 162
00290 = 18
00291 = 19
9090 = 81
9191 = 91
 */
import java.util.Scanner;

// 곱하기 혹은 더하기
public class Main {

    private static String S;

    public static void main(String[] args) {
        solution();
    }

    private static void solution() {
        Scanner sc = new Scanner(System.in);
        S = sc.nextLine();
        int answer = Character.getNumericValue(S.charAt(0));
        for (int i = 1; i < S.length(); i++) {
            int number = Character.getNumericValue(S.charAt(i));
            if(answer == 0 || answer == 1 || number == 0 || number == 1) {
                answer += number;
            } else {
                answer *= number;
            }
        }
        System.out.println(answer);
    }
}
