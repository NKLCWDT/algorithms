package seunguk.chapter7.findparts;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static int[] arr1;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        arr1 = new int[N];
        for (int i = 0; i < N; i++) {
            arr1[i] = sc.nextInt();
        }

        int M = sc.nextInt();
        int[] arr2 = new int[M];
        for (int i = 0; i < M; i++) {
            arr2[i] = sc.nextInt();
        }

        Arrays.sort(arr1);

        for (int i = 0; i < M; i++) {
            int result = binarySearch(arr2[i], 0, N-1);
            if (result == -1) {
                System.out.print("no" + " ");
            } else {
                System.out.print("yes" + " ");
            }
        }

    }

    private static int binarySearch(int target, int start, int end) {
        while (start <= end) {
            int mid = (start + end) / 2;

            if (target == arr1[mid]) { // 찾는값이 있을경우
                return mid;
            } else if (target < arr1[mid]) { // 찾는 값이 더 작을경우 mid 값 보다 작은 범위 탐색
                end = mid - 1;
            } else { // 찾는 값이 더 클 경우 mid 값 보다 더 큰 범위 탐색
                start = mid + 1;
            }
        }
        return -1; // 탐색실패
    }
}
