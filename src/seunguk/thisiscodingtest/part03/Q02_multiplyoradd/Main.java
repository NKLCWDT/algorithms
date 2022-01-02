package seunguk.thisiscodingtest.part03.Q02_multiplyoradd;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();

        int temp1 = s.charAt(0) - '0';

        for (int i = 1; i < s.length(); i++) {
            int temp2 = s.charAt(i) - '0';

            if (temp1 <= 1 || temp2 <= 1) {
                temp1 = temp1 + temp2;
            } else {
                temp1 = temp1 * temp2;
            }
        }
        System.out.println(temp1);
    }
}
