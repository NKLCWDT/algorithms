package annmj.thisiscodingtest.part02.chapter7.makingtteokbokki;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
    static FastReader scan = new FastReader();

    static int N, M;
    static int[] array;
    static int[] sum; // sum[i] = array 의 0번째부터 i번째 까지의 합
    static int[] tteokArray;
    static void input() {
        N = scan.nextInt();
        M = scan.nextInt();
        array = new int[N];
        sum = new int[N];
        tteokArray = new int[N];
        for (int i = 0; i < N; i++) {
            array[i] = scan.nextInt();
        }
    }

    static void pro() {
        // 구하려는 길이 : M
        Arrays.sort(array);

        int start = 0;
        int end = array[N-1];
        int result = 0;
        // H 를 찾기 위한 이분 탐색!!
        while (start <= end) {
            int mid = (start + end) / 2; // 높이 H
            int total = 0;
            for (int i = 0; i < N; i++) {
                if(array[i] > mid) {
                    total += array[i] - mid;
                }
            }

            if (total < M) { // 적게 잘랐음, H는 현재보다 더 작아져야 함.
                end = mid - 1;
            } else { // 많이 잘랐음. H 는 더 커져야 함.
                result = mid; // 최소 이정도는 잘라야 하므로 result 는 mid 로 설정
                start = mid + 1;
            }
        }

        System.out.println(result);
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

