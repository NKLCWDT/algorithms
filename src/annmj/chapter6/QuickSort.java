package annmj.chapter6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class QuickSort {

    static FastReader scan = new FastReader();

    static int size;
    static int[] arr;
    static void input() {
        size = scan.nextInt();
        arr = new int[size];

        for (int i = 0; i < size; i++) {
            arr[i] = scan.nextInt();
        }
    }

    static void quickSort(int start, int end) {
        if(start >= end) {
            return;
        }
        int pivot = arr[start];
        int left = start;
        int right = end;
        while (left <= right) {
            while (left <= end && arr[left] <= pivot) {
                left++;
            }

            while (right > start && arr[right] >= pivot) {
                right--;
            }

            if (left > right) {
                arr[start] = arr[right];
                arr[right] = pivot;
            } else {
                int temp = arr[right];
                arr[right] = arr[left];
                arr[left] = temp;
            }
        }

        quickSort(start, right-1);
        quickSort(right+1, end);
    }

    static void pro() {
        StringBuilder sb = new StringBuilder();
        quickSort(0, size - 1);
        for (int i = 0; i < size; i++) {
            sb.append(arr[i]).append(' ');
        }
        System.out.println(sb);
    }

    public static void main(String[] args) {
        input();
        pro();
    }

    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        Integer nextInt() {
            return Integer.parseInt(next());
        }
    }
}
