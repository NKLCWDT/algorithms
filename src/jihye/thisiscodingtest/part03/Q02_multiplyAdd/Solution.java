package jihye.thisiscodingtest.part03.Q02_multiplyAdd;

import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String S = sc.nextLine();
        int answer = Character.getNumericValue(S.charAt(0));
        for (int i = 1; i < S.length(); i++) {
            int currNumber = Character.getNumericValue(S.charAt(i));
            if (answer == 0 || currNumber == 0) {
                answer += currNumber;
            } else {
                answer *= currNumber;
            }
        }
        System.out.println(answer);
    }
}
