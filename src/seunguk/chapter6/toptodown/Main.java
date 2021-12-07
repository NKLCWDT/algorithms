package seunguk.chapter6.toptodown;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        Integer[] arr = new Integer[N];

        // 입력
        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }

        // 내림차순 정렬
        Arrays.sort(arr, Collections.reverseOrder());

        // 출력
        for (int i = 0; i < N; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
