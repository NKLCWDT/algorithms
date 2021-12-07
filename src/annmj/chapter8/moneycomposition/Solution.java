package annmj.chapter8.moneycomposition;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static FastReader scan = new FastReader();

    static int N, M;
    static int[] arr , money;
    static void input() {
        N = scan.nextInt();
        M = scan.nextInt();
        arr = new int[N];
        money = new int[M+1];
        for (int i = 0; i < N; i++) {
            arr[i] = scan.nextInt();
        }
        for (int i = 1; i <= M; i++) {
            money[i] = 10001;
        }
    }

    static void process() {
        for (int i = 1; i <= M; i++) {
            for (int j = 0; j < N; j++) {
                if(i - arr[j] < 0) continue; // M * N 의 최대값은 100만 이므로 2중 포문 돌아도 괜찮다.
                money[i] = Math.min(money[i], money[i - arr[j]] + 1);
            }
        }

        System.out.println(money[M] == 10001 ? -1 : money[M]);
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

