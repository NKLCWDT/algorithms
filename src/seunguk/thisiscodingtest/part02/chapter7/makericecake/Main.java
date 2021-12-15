package seunguk.thisiscodingtest.part02.chapter7.makericecake;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }
        Arrays.sort(arr); // 가장 큰 값을 구하기 위해 정렬
        int start = 0;
        int end = arr[N-1];
        int check = 0;
        while (start <= end) {
            int mid = (start + end) / 2;
            int result = 0;

            for (int i = 0; i < N; i++) {
                if (arr[i] > mid) { // 높이보다 클 경우만
                    result += arr[i] - mid;
                }
            }

            if (result >= M) { // M이랑 같거나 더 클 경우 일단 mid 값은 저장해놓고 높이를 다시 올리면서 확인한다.
                check = mid;
                start = mid + 1;
            } else { // M보다 작은 경우는 높이를 낮춰 떡을 더 많이 가져가야한다.
                end = mid - 1;
            }
        }
        System.out.println(check);
    }
}
