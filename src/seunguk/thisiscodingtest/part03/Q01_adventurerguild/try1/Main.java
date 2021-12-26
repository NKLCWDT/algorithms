package seunguk.thisiscodingtest.part03.Q01_adventurerguild.try1;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }

        Arrays.sort(arr);
        int start = arr[N-1];
        int count = 0;

        while(true) {
            N -= start;
            if (N > 0) {
                count++;
            } else if (N == 0) {
                count++;
                break;
            } else {
                break;
            }
            start = arr[N-1];
        }
        System.out.println(count);
    }
}
