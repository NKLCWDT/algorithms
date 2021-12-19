package annmj.thisiscodingtest.part03.Q04_notmakemoney;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static int N;
    static int[] arr;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }

        Arrays.sort(arr);

        int target = 1;
        for (int i = 0; i < N; i++) {
            if (target < arr[i]) {
                break;
            }
            target += arr[i];
        }
        System.out.println(target);

    }
}
