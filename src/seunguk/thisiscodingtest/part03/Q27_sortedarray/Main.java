package seunguk.thisiscodingtest.part03.Q27_sortedarray;

import java.util.Scanner;

public class Main {
    static int N;
    static int x;
    static int[] arr;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        x = sc.nextInt();
        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }
        int result = binary();
        if (result == 0) {
            System.out.println(-1);
        } else {
            System.out.println(result);
        }

    }

    private static int binary() {
        int count = 0;
        int start = 0;
        int end = N-1;

        while (start <= end) {
            int mid = (start + end) / 2;
            // 값을 구한경우 왼쪽 오른쪽으로 같은 값을 탐색한다.
            if (arr[mid] == x) {
                for (int i = 1; i < mid; i++) {
                    if (arr[mid-i] == arr[mid]) {
                        count++;
                    } else {
                        break;
                    }
                }
                for (int i = 0; i < mid; i++) {
                    if (arr[mid+i] == arr[mid]) {
                        count++;
                    } else {
                        break;
                    }
                }
                return count;
            } else if (arr[mid] < x) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }

        }
        return count;
    }
}

/*
7 2
1 1 2 2 2 2 3

7 4
1 1 2 2 2 2 3
*/
