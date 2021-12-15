package seohae.thiscodingtest.part04.chapter7.S01_findPart;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/*
   문의한 부품이 존재하는지를 확인하여 차례대로 yes, no 출력

   arr 8, 3, 7, 9, 2

   find arr 5, 7, 9 (asc sort)

   find arr
 */
public class Main {
    static int N; // 부품 개수
    static int[] arr; // 부품 존재 배열

    static int M; // 찾을 부품 개수
    static int[] findArr; // 찾을 부품 배열

    public static void main(String[] args) {
        input();

        findList();

        System.out.println();

        binarySearch();
    }

    /**
     5
     8 3 7 9 2
     3
     5 7 9
     */
    private static void input() {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        arr = new int[N];

        /* 부품 존재 배열 */
        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }

        M = sc.nextInt();
        findArr = new int[M];

        /* 찾을 부품 배열 */
        for (int i = 0; i < M; i++) {
            findArr[i] = sc.nextInt();
        }
    }

    /**
     * 리스트를 사용한 방법
     */
    private static void findList() {
        List<Integer> list = Arrays.stream(arr).boxed().collect(Collectors.toList());

        Arrays.stream(findArr).forEach(n -> {
            if (list.contains(n)) {
                System.out.print("yes ");
            } else {
                System.out.print("no ");
            }
        });
    }

    /**
     * 이진탐색을 사용한 방법
     */
    private static void binarySearch() {
        /* asc sort */
        Arrays.sort(arr);

        for (int target : findArr) {
            /* start, end set */
            int start = 0;
            int end = arr.length - 1;

            boolean isFind = false; // find flag

            while (start <= end) {
                /* 중간점 찾기 */
                int mid = (start + end) / 2;

                if (arr[mid] == target) { /* 찾은 경우 flag true */
                    isFind = true;
                    break;
                } else if (arr[mid] > target) { /* 중간점의 값보다 찾고자 하는 값이 작은 경우 왼쪽 확인 */
                    end = mid - 1;
                } else {
                    start = mid + 1; /* 중간점의 값보다 찾고자 하는 값이 큰 경우 오른쪽 확인 */
                }
            }

            System.out.print(isFind ? "yes " : "no ");
        }
    }
}
