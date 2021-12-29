package seunguk.thisiscodingtest.part03.Q35_uglynumber;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] uglyNumber = new int[1001];
        uglyNumber[0] = 1;
        int number = 2;
        int index = 1;
        while (true) {
            if (index == 1001) {
                break;
            }
            if (number % 7 != 0 && (number % 2 == 0 || number % 3 == 0 || number % 5 == 0)) {
                uglyNumber[index] = number;
                index++;
            }
            number++;
        }

        for (int i = 0; i < uglyNumber.length; i++) {
            System.out.println(uglyNumber[i]);
        }
        System.out.println(uglyNumber[n-1]);

    }
}
