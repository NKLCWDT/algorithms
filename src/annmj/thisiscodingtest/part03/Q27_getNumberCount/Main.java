package annmj.thisiscodingtest.part03.Q27_getNumberCount;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        input();
        process();
    }
    static int N;
    static int x;
    static int[] arr;
    static void input() {
        Scanner scan = new Scanner(System.in);
        N = scan.nextInt();
        x = scan.nextInt();
        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = scan.nextInt();
        }
    }
    static void process() {
        int result = 0;
        int L = 0;
        int R = N - 1; // 배열의 크기 -1
        int mid = 0;
        while (L <= R) {
            // 1 1 2 2 2 2 3
            mid = (L + R) / 2;
            if (arr[mid] >= x) {
                result = arr[mid];
                R = mid - 1;
            } else {
                L = mid + 1;
            }
        }
        // x 의 개수 구하기 , 기존 L 과 R을 활용
        if(result == x) {
            L = findLowerBound(mid);
            R = findUpperBound(mid);
            System.out.println(R - L + 1);
        } else {
            System.out.println(-1);
        }
    }

    private static int findLowerBound(int mid) {
        int value = arr[mid];
        int lowerBound = mid;
        while (lowerBound >= 0 && arr[lowerBound] == value) {
            lowerBound--;
        }
        return lowerBound + 1;
    }

    private static int findUpperBound(int mid) {
        int value = arr[mid];
        int upperBound = mid;
        while (upperBound < N && arr[upperBound] == value) {
            upperBound++;
        }
        return upperBound - 1;
    }
}
