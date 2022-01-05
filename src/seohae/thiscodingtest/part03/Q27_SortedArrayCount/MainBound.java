package seohae.thiscodingtest.part03.Q27_SortedArrayCount;

import java.util.Arrays;
import java.util.Scanner;

/*
   원소 N개
   오름차순 정렬

   x 가 등장하는 횟수 계산하기

   예시)
   1, 1, 2, 2, 2, 2, 3
   x = 2 일때 원소 2의 개수 : 4개

   시간복잡도 O(logN) 필수
 */
public class MainBound {
    private static int N;
    private static int X;

    private static int[] arr;

    public static void main(String[] args) {
        input();

        int result = upperBound() - lowerBound() + 1;
        System.out.println(result == 0 ? -1 : result);
    }

    /*
      7 2
      1 1 2 2 2 2 3
     */
    private static void input() {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        X = sc.nextInt();

        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }

        /* asc sort */
        Arrays.sort(arr);
    }

    /**
     * 처음으로 나오는 target 보다 작은 수
     */
    private static int lowerBound() {
        int start = 0;
        int end = arr.length; // start 가 마지막 인덱스 직전까지 체크되어야한다.

        while (start < end) {
            int mid = (start + end) / 2;

            /* target 이 중간값보다 작거나 같다면, 끝을 중간값으로 */
            if (X <= arr[mid]) {
                end = mid;
            } else { /* target 이 중간값보다 크다면 시작을 중간값 다음으로 */
                start = mid + 1;
            }
        }

        System.out.println("lowerBound low : " + start);
        return start;
    }

    /**
     * 처음으로 나오는 target 보다 큰 수
     */
    private static int upperBound() {
        int start = 0;
        int end = arr.length;
        while (start < end) {
            int mid = (start + end) / 2;

            if (X >= arr[mid]) { /* target 이 중간값보다 크거나 같다면, 시작을 중간값 다음으로 */
                start = mid + 1;
            } else { /* target 이 중간값보다 작다면 끝을 중간값으로 */
                end = mid;
            }
        }

        System.out.println("upperBound low : " + start);
        return start - 1;
    }
}
