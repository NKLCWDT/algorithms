package seunguk.thisiscodingtest.part03.Q24_antenna;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static int[] arr;
    static int N;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        arr = new int[N];

        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }
        Arrays.sort(arr);

        int div = arr.length / 2;

        if (arr.length % 2 == 0) { // 짝수
            System.out.println(arr[div-1]);
        } else { // 홀수
            System.out.println(arr[div]);
        }
    }
}
