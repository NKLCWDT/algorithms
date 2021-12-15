package annmj.thisiscodingtest.part02.chapter3.NumberCardGame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Solution {
    static FastReader scan = new FastReader();

    static int N, M;
    static int[][] arr;
    static Integer[] mins;

    static void input() {
        N = scan.nextInt();
        M = scan.nextInt();
        arr = new int[N][M];
        mins = new Integer[N];

        for (int i = 0; i < N; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < M; j++) {
                arr[i][j] = scan.nextInt();
                min = Math.min(min, arr[i][j]);
            }
            mins[i] = min;
        }
    }

    static void pro() {
        Arrays.sort(mins, Collections.reverseOrder());
        System.out.println(mins[0]);
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
