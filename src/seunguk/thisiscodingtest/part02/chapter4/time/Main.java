package seunguk.thisiscodingtest.part02.chapter4.time;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int count = 0;
        for (int i = 0; i <= N; i++) {
            String si = i + "";
            for (int j = 0; j < 60; j++) {
                String sj = j + "";
                for (int k = 0; k < 60; k++) {
                    String sk = k + "";
                    String result = si + sj + sk;
                    if (result.contains("3")) {
                        count++;
                    }
                }
            }
        }
        System.out.println(count);
    }
}
