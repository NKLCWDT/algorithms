package jungho.thisiscodingtest.part03.Q09_compressstring;


/*
   aabbaccc -> 2a2ba3c
   s 의 길이는 1 <= s <= 1000
   s 는 알파벳 소문자로만 이루어져 있음
   문자열을 잘라 압축하여 표현한 문자열 중 가장 짧은 것의 길이를 return

   min (1개단위로 잘랐을 때의 결과 vs 2개 ..)
   min (2개단위로 잘랐을 때의 결과 vs 3개 ..)
   ...

   압축 최대 효율은 -> 2 * 반복되는 문자열
*/
import java.util.Scanner;

// 문자열 압축
public class Solution {

    static int answer;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println(solution(sc.nextLine()));
    }

    public static int solution(String alphabet) {
        // 구하고자하는 답을 원본 문자열의 길이로 초기화
        answer = alphabet.length();

        // 1 ~ alphabet.length/2 만큼의 단위로 문자열 자르기
        for (int i = 1; i <= alphabet.length() / 2; i++) {
            int count = 1;

            // i 개 단위로 자른 문자열
            String current = alphabet.substring(0, i);
            StringBuilder stringBuilder = new StringBuilder();

            // i 개 단위로 잘린 현재 문자열(current) 와 비교하기 위한 next 문자열 구하는 반복문
            for (int k = i; k < alphabet.length(); k = k + i) {
                int nextLastIndex = k + i < alphabet.length() ? k + i : alphabet.length();
                String next = alphabet.substring(k, nextLastIndex);

                if (current.equals(next)) {
                    count++;
                } else {
                    concatToStringBuilder(stringBuilder, count, current);
                    count = 1;
                    current = next;
                }
            }
            // Ex. alphabet 길이가 16이고 3개 단위로 자른다고 했을때, 내부 반복문의 k 값은 15 까지만 가능함.
            // 따라서 내부 반복문 끝나면 한 번더 남은 문자열을 붙여줘야 함
            concatToStringBuilder(stringBuilder, count, current);

            answer = Math.min(answer, stringBuilder.length());
        }

        return answer;
    }

    static void concatToStringBuilder(StringBuilder stringBuilder, int count, String str) {
        if(count > 1) {
            stringBuilder.append(count);
        }
        stringBuilder.append(str);
    }
}
