package annmj.chapter8.makeit1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static FastReader scan = new FastReader();
    static int N;
    static int[] answer;
    static void input() {
        N = scan.nextInt();
        answer = new int[30000]; // 1부터 N 까지의 배열 생성
    }

    static void process() {
        // 초기화
        answer[1] = 1;
        answer[2] = 1;
        answer[3] = 1;
        answer[4] = 2;
        answer[5] = 1;

        for (int i = 6; i <= N; i++) {
            if (i % 5 == 0) {
                answer[i] = answer[i/5] + 1;
            } else if (i % 3 == 0) {
                answer[i] = answer[i/3] + 1;
            } else if (i % 2 == 0) {
                answer[i] = answer[i/2] + 1;
            } else {
                answer[i] = answer[i-1] + 1;
            }
        }

        System.out.println(answer[N]);

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

