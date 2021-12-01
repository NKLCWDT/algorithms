package seunguk.chapter3.bigNumberRule;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int K = sc.nextInt();

        Integer[] arr = new Integer[N];

        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }

        Arrays.sort(arr, Collections.reverseOrder());

        int result = 0;
        int count = 0;

        while (M > 0){
            if (count >= K) { // K보다 같거나 클 경우 두번째로 큰 값 더한다.
                result += arr[1];
                count = 0;
            } else { // K보다 작을 경우 제일 큰 값 더한다.
                result += arr[0];
                count++;
            }
            M--;
        }

        System.out.println(result);
    }
}
