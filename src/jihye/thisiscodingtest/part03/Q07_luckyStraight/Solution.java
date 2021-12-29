package jihye.thisiscodingtest.part03.Q07_luckyStraight;

import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String N = sc.nextLine();
        int standard = N.length() / 2;
        //왼쪽부분과 오른쪽 부분으로 분리
        String left = N.substring(0, standard);
        String right = N.substring(standard, N.length());

        int leftTotal = 0;
        int rightTotal = 0;

        for (int i = 0; i < standard; i++) {
            leftTotal += Character.getNumericValue(left.charAt(i));
            rightTotal += Character.getNumericValue(right.charAt(i));
        }

        if (leftTotal == rightTotal) {
            System.out.println("LUCKY");
        } else {
            System.out.println("READY");
        }


    }
}
