package annmj.thisiscodingtest.part02.chapter8.antwarrior;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static FastReader scan = new FastReader();

    static int N;
    static int[] containers;

    /**
     * [N][2] 배열
     * 인덱스 0 : 마지막으로 자기 자신을 밟은 경우
     * 인덱스 1 : 마지막으로 자기 자신을 밟지 않은 경우
     */
    static int[][] parts; // 각 단계에서의 부분 부분들
    static void input() {
        N = scan.nextInt();
        containers = new int[N+1];
        parts = new int[N + 1][2];
        for (int i = 1; i <= N; i++) {
            containers[i] = scan.nextInt(); // 1부터 인덱스 지정
        }
    }

    static void process() {
        parts[1][0] = containers[1];
        parts[1][1] = 0;
        parts[2][0] = containers[2];
        parts[2][1] = parts[1][0];
        parts[3][0] = parts[2][1] + containers[3];
        parts[3][1] = parts[2][0];

        for (int i = 4; i <= N; i++) {
            parts[i][0] = parts[i - 1][1] + containers[i];
            parts[i][1] = parts[i-1][0];
        }

        System.out.println(Math.max(parts[N][0], parts[N][1]));
    }

    public static void main(String[] args) {
        input();
        process();
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

