package annmj.chapter9.futurecity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static FastReader scan = new FastReader();

    static int N, M, X, K;
    static int[][] arr;

    static void input() {
        N = scan.nextInt();
        M = scan.nextInt();
        arr = new int[N + 1][N + 1]; // 연결된 간선은 1, 연결되지 않았다면 101, 자기 자신은 0
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= N; j++) {
                arr[i][j] = 101; // 최대가 100 이므로 101로 설정
                if (i == j) arr[i][j] = 0;
            }
        }

        for (int i = 0; i < M; i++) {
            int from = scan.nextInt();
            int to = scan.nextInt();
            arr[from][to] = 1;
            arr[to][from] = 1;
        }

        X = scan.nextInt();
        K = scan.nextInt();
    }

    static void process() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                for (int k = 1; k <= N; k++) {
                    arr[j][k] = Math.min(arr[j][k], arr[j][i] + arr[i][k]);
                }
            }
        }

        int distance = arr[1][K] + arr[K][X];
        System.out.println(distance > 100 ? -1 : distance);
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

