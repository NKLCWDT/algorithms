package seunguk.thisiscodingtest.part03.Q27_sortedarray;

import java.util.Scanner;

public class Main2 {
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
        System.out.println(right());
        System.out.println(left());
        int result = right() - left() + 1; // 인덱스 끼리 뺴주므로 +1을 더해준다.
        if (result == 0) {
            System.out.println(-1);
        } else {
            System.out.println(result);
        }
    }
    // 같은 수 중 제일 큰 값을 구한다.
    private static int right() {
        int check = 0;
        int start = 0;
        int end = N-1;
        while (start <= end) {
            int mid = (start + end) / 2;
            // x 값보다 작거나 같을때 check에 담는다.
            // 1 1 2 2 2 2 3 일때 x와 같은 값인 2중에 가장 오른쪽 위치가 담긴다.
            if (arr[mid] <= x) {
                check = mid;
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return check;
    }
    // 같은 수 중 가장 작은 값을 구한다.
    private static int left() {
        int check = 0;
        int start = 0;
        int end = N-1;
        while (start <= end) {
            int mid = (start + end) / 2;
            // x 값보다 작은 값일 때 check에 담는다.
            // 1 1 2 2 2 2 3 일때 가장 오른쪽 1일때 위치를 담는다.
            if (arr[mid] < x) {
                check = mid;
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return check + 1; // x 보다 작은 값중 가장 오른쪽 값이므로 + 1을 하면 x값중 가장 왼쪽값이 된다.
    }
}


/*
7 2
1 1 2 2 2 2 3

7 4
1 1 2 2 2 2 3
*/

