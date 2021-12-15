package seohae.thiscodingtest.part04.chapter3.S04_untillOne;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // N, K를 공백을 기준으로 구분하여 입력 받기
        int n = sc.nextInt();
        int k = sc.nextInt();
        int result = 0;

        /* 1보다 클때까지 반복 */
        while (n > 1) {
            /* n 이 k로 나누어떨어질 경우 */
            if (n % k == 0) {
                /* 나눈다 */
                n = n / k;
            } else {
                /* 아닐 경우 -1 수행 */
                n = n - 1;
            }

            /* 1이 될때까지의 총 계산 횟수 */
            result++;
        }

        System.out.println(result);
    }
}
