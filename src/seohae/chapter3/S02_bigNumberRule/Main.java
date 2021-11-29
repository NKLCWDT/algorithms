package seohae.chapter3.S02_bigNumberRule;

import java.util.Arrays;

public class Main {
    /*
        M = 8, K = 3
        arr = {2, 4, 5, 4, 6}

        -> 가장 큰 수 : 6
        -> 두번째로 큰 수 : 5
        6 + 6 + 6 + 5 + 6 + 6 + 6 = 46
     */
    public static void main(String[] args) {
        int[] arr = new int[]{2, 4, 5, 4, 6};

        int m = 8; // 개수 제한
        int k = 3; // 반복 개수 제한

        /* 정렬 */
        Arrays.sort(arr);

        /* 정렬된 배열 기준 가장 큰수와 두번째로 큰 수 추출 */
        int max = arr[arr.length - 1];
        int secondMax = arr[arr.length - 2];

        int result = 0;

        int copyK = k;
        /* m 번 만큼 반복 */
        for (int i = 0; i < m; i++) {
            if (copyK == 0) {
                /* copyK 가 0 일 경우 두번째로 큰 수를 더한다 */
                result += secondMax;
                copyK = k; /* 다시 가장 큰 수를 곱할 횟수 k 로 셋팅 */
            } else {
                /* copyK 가 0 이 아닐 경우 가장 큰 수를 더한다 */
                result += max;
                copyK = copyK - 1;
            }
        }

        System.out.println(result);
    }
}
