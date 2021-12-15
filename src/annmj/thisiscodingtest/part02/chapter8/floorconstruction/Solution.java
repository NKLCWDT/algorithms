package annmj.thisiscodingtest.part02.chapter8.floorconstruction;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static FastReader scan = new FastReader();

    static int N;
    static long[] floors; // 경우의 수
    static void input() {
        N = scan.nextInt();
        floors = new long[N + 1];
    }

    static void process() {
        floors[1] = 1;
        floors[2] = 3;
        for (int i = 3; i <= N; i++) {
            floors[i] = (2 * floors[i - 2] + floors[i - 1]) % 796796;
        }
        System.out.println(floors[N]);
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

