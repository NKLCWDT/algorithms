package jihye.thisiscodingtest.part03.Q08_rearrangeStrings;

import java.util.Arrays;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String S = sc.nextLine();
        //숫자만 num에 담는다.
        String num = S.replaceAll("[^0-9]", "");
        //대문자 A-Z만 string에 담는다.
        String string = S.replaceAll("[^A-Z]", "");
        //문자열 정렬을 string을 문자열 배열로 만든다.
        char[] characters = string.toCharArray();
        //정렬
        Arrays.sort(characters);
        int sum = 0;
        for (int i = 0; i < num.length(); i++) {
            //숫자들의 합을 구한다.
            sum += Character.getNumericValue(num.charAt(i));
        }

        String answer = String.valueOf(characters) + sum;
        System.out.println(answer);
    }

}
