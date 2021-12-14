package seohae.chapter8.S02_antChapmion;

import java.util.Scanner;

/*
   서로 인접한 식량창고를 공격하지 않고, 최소한 한칸 이상 떨어지게 약탈해야한다.

   [1, 3, 1, 5]

   두번째 식량을 방문하고, 네번째 시략창고를 선택했을때 최댓값인 8개의 식량을 얻는다.

   백트래킹
   -> DP[] 에 저장
   -> i 번째일때 d[i - 1] 과 d[i - 2] + arr[i] 의 최대값을 저장
 */
public class Main {
    static int X; /* 정수 X */
    static int[] DP; /* i의 최소 연산 횟수를 저장하는 배열 */
    static int[] arr; /* 식량 창고 배열 */

    public static void main(String[] args) {
        input();

        /* 0, 1 일때는 종료 */
        for (int i = 2; i < DP.length; i++) {
            DP[i] = Math.max(DP[i - 1], DP[i - 2] + arr[i]);
        }

        System.out.println(DP[arr.length - 1]);
    }

    /**
     4
     1 3 1 5
     */
    private static void input() {
        Scanner sc = new Scanner(System.in);
        X = sc.nextInt();

        /* 배열 */
        arr = new int[X + 2];
        for (int i = 2; i < arr.length; i++) {
            // 0, 0, 1, 3, 1, 5
            arr[i] = sc.nextInt();
        }

        DP = new int[arr.length];
    }
}
