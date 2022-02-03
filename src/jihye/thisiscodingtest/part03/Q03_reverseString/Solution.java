package jihye.thisiscodingtest.part03.Q03_reverseString;

import java.util.Scanner;

/*
연속된 하나 이상의 숫자를 잡고 모두 뒤집는다.
 */
public class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String S = sc.next();

        int countOne = 0;
        //1로 바꿀때 몇번을 뒤집어야 하는지
        for (int i = 0; i < S.length(); i++) {
            if (S.charAt(i) == '0') {
                countOne++;
                while (i < S.length() && S.charAt(i) == '0') {
                    i++;
                }
            }
        }

        int countZero = 0;
        //0으로 바꿀때 몇번을 뒤집어야하는지
        for (int i = 0; i < S.length(); i++) {
            if (S.charAt(i) == '1') {
                countZero++;
                while (i < S.length() && S.charAt(i) == '1') {
                    i++;
                }
            }
        }

        System.out.println(Math.min(countOne, countZero));
    }

}
