package seunguk.chapter6.swapelements;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();
        int result = 0;
        int[] arr1 = new int[N];
        Integer[] arr2 = new Integer[N];

        // 입력
        for (int i = 0; i < N; i++) {
            arr1[i] = sc.nextInt();
        }
        for (int i = 0; i < N; i++) {
            arr2[i] = sc.nextInt();
        }

        Arrays.sort(arr1); // 오름차순
        Arrays.sort(arr2, Collections.reverseOrder()); // 내림차순

        for (int i = 0; i < K; i++) {
            if (arr1[i] < arr2[i]) { // 원소가 더 작을 경우에만 스왑
                swap(arr1, arr2, i);
            }
        }

        // 출력
        for (int i : arr1) { result += i; }

        System.out.println(result);
    }

    private static void swap(int[] arr1, Integer[] arr2, int i) {
        int temp;
        temp = arr1[i];
        arr1[i] = arr2[i];
        arr2[i] = temp;
    }


}
