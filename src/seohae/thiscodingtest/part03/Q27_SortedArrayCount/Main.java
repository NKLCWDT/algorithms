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
public class Main {
    private static int N;
    private static int X;

    private static int[] arr;

    public static void main(String[] args) {
        input();

        binarySearch();
    }

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
     * 이진탐색을 사용한 방법
     */
    private static void binarySearch() {
        int count = -1;

        /* start, end set */
        int start = 0;
        int end = arr.length - 1;

        while (start <= end) {
            /* 중간점 찾기 */
            int mid = (start + end) / 2;

            if (arr[mid] == X) { /* 찾은 경우 flag true */
                int minus = mid;
                int plus = mid;

                /* mid 보다 작을 경우 체크 */
                while (true) {
                    if (minus - 1 >= 0) {
                        if (arr[minus - 1] != X) {
                            break;
                        } else {
                            /* 동일할 경우 계속 왼쪽으로 진행 */
                            minus = minus - 1;
                        }
                    } else {
                        break;
                    }
                }

                /* mid 보다 클 경우 체크 */
                while (true) {
                    if (plus + 1 <= arr.length - 1) {
                        if (arr[plus + 1] != X) {
                            break;
                        } else {
                            /* 동일할 경우 계속 오른쪽으로 진행 */
                            plus = plus + 1;
                        }
                    } else {
                        break;
                    }
                }

                count = plus - minus + 1;
                break;
            } else if (arr[mid] > X) { /* 중간점의 값보다 찾고자 하는 값이 작은 경우 왼쪽 확인 */
                end = mid - 1;
            } else {
                start = mid + 1; /* 중간점의 값보다 찾고자 하는 값이 큰 경우 오른쪽 확인 */
            }
        }

        System.out.println(count);
    }
}
