package seohae.chapter6.S01_topToDown;

import java.util.Scanner;
import java.util.stream.IntStream;

/*
   큰 수부터 작은 순서로 정렬
 */
public class Main {
    static int[] arr;
    static int N;

    /**
     * 입력받기
     */
    private static void input() {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();

        arr = new int[N];

        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }
    }

    public static void main(String[] args) {
        input();

        selectionSort(); /* 선택정렬 */
        insertionSort(); /* 삽입정렬 */
    }

    /**
     * 선택정렬
     */
    static void selectionSort() {
        for (int i = 0; i < N; i++) {
            int min_index = i;  // 가장 작은 원소의 인덱스

            /* i 다음 인덱스부터 탐색 */
            for (int j = i + 1; j < N; j++) {
                if (arr[min_index] > arr[j]) {
                    min_index = j; /* 작은 원소 발견시 min_index 갱신 */
                }
            }

            /* swap */
            int temp = arr[i];
            arr[i] = arr[min_index];
            arr[min_index] = temp;
        }

        // N = 3) 2, 1, 0 순으로 출력
        IntStream.range(0, N).mapToObj(i -> arr[N - (i + 1)] + " ").forEach(System.out::println);
    }

    /**
     * 삽입정렬
     */
    static void insertionSort() {
        for (int i = 0; i < N; i++) {
            /* 인덱스 i 부터 감소시키며 반복 */
            for (int j = i; j > 0; j--) {
                /* 현재의 arr[j] 원소보다 더 큰 원소를 발견하면 swap */
                if (arr[j] < arr[j - 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = temp;
                } else {
                    /* 자기보다 작은 데이터를 만나면 그 위치에서 멈춤 */
                    break;
                }
            }
        }

        // N = 3) 2, 1, 0 순으로 출력
        IntStream.range(0, N).mapToObj(i -> arr[N - (i + 1)] + " ").forEach(System.out::println);
    }
}
