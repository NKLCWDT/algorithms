package seunguk.thisiscodingtest.part03.Q07luckystraight;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String N = sc.next();
        int count = N.length();

        int number = count / 2;

        String a = N.substring(0, number);
        String b = N.substring(number, count);

        int x = 0;
        int y = 0;

        for (int i = 0; i < a.length(); i++) {
            x += a.charAt(i) - '0';
            y += b.charAt(i) - '0';
        }

        if (x == y) {
            System.out.println("LUCKY");
        } else {
            System.out.println("READY");
        }



    }
}
