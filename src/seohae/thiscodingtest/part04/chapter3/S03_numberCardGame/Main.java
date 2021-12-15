package seohae.thiscodingtest.part04.chapter3.S03_numberCardGame;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt(); // 열 개수
        int m = sc.nextInt(); // 행 개수
        int result = 0;

        /* 열의 개수만큼 입력받기 */
        for (int i = 0; i < n; i++) {
            /* 최솟값 변수 */
            int min_value = Integer.MAX_VALUE;

            /* 행의 개수만큼 입력받기 */
            for (int j = 0; j < m; j++) {
                int x = sc.nextInt();

                /* 해당 행의 가장 작은값 셋팅 */
                min_value = Math.min(min_value, x);
            }

            /* 각 행의 최소값 중 최대값을 구한다 */
            result = Math.max(result, min_value);
        }

        System.out.println(result);
    }
}
