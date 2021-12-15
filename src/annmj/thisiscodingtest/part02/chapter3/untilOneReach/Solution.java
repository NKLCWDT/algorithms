package annmj.thisiscodingtest.part02.chapter3.untilOneReach;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    static FastReader scan = new FastReader();

    static int N, K;

    static void input() {
        N = scan.nextInt();
        K = scan.nextInt();
    }

    static void pro() {
        int count = 0;
        while (N != 1) {
            if (N % K == 0) {
                N /= K;
            } else {
                N--;
            }
            count++;
        }
        System.out.println(count);
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
