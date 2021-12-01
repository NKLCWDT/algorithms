package seohae.chapter4.S02_time;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 5 -> 00시 00분 00초 ~ 5시 59분 59초
        int h = sc.nextInt();
        int cnt = 0;

        /* 구현 */
        for (int i = 0; i <= h; i++) { /* 5시 */
            for (int j = 0; j < 60; j++) { /* 59분 */
                for (int k = 0; k < 60; k++) { /* 59초 */
                    /* 매 시각 안에 '3'이 포함되어 있다면 카운트 증가 */
                    if (i % 10 == 3 || j % 10 == 3 || k % 10 == 3 // 13, 23, 33, 43, 53
                            || j / 10 == 3 || k / 10 == 3) { // 30
                        cnt++;
                    }
                }
            }
        }

        System.out.println(cnt);
    }
}
