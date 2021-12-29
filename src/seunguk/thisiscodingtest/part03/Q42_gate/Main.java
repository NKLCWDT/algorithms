package seunguk.thisiscodingtest.part03.Q42_gate;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int G = sc.nextInt();
        int P = sc.nextInt();
        int result = 0;
        boolean[] check = new boolean[G+1];
        for (int i = 0; i < P; i++) {
            int a = sc.nextInt();
            int count = 0;

            for (int j = a; j > 0; j--) { // 1부터 a까지 탑승구 이용가능하다
                if (!check[j]) {
                    check[j] = true;
                    count++;
                    break;
                }
            }

            if (count == 0) {
                break;
            }
            result++;
        }
        System.out.println(result);
    }
}
