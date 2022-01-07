package annmj.thisiscodingtest.part03.Q40_hide;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[][] A;
    static boolean[] visit;
    static int[] dist;

    public static void main(String[] args) {
        input();
        process();
    }

    private static void process() {
        // BFS 로 풀자. edge 거리가 1이니까
        Queue<Integer> Q = new LinkedList<>();
        Q.add(1);
        visit[1] = true;
        int maxDistance = 0;

        while (!Q.isEmpty()) {
            int from = Q.poll();

            for (int i = 1; i <= N; i++) {
                if(visit[i]) continue;
                if(A[from][i] == 0) continue;
                Q.add(i);
                dist[i] = dist[from] + 1;
                maxDistance = Math.max(maxDistance, dist[i]);
                visit[i] = true;
            }
        }

        int answerNumber = 0, answerDistance = maxDistance, answerCount = 0;

        for (int i = 1; i <= N; i++) {
            if (maxDistance == dist[i]) {
                if(answerNumber == 0) answerNumber = i;
                answerCount++;
            }
        }
        System.out.println(answerNumber + " " + answerDistance + " " + answerCount);
    }

    private static void input() {
        FastReader scan = new FastReader();
        N = scan.nextInt();
        M = scan.nextInt();
        A = new int[N + 1][N + 1];
        dist = new int[N + 1];
        visit = new boolean[N + 1];

        for (int i = 0; i < M; i++) {
            int from = scan.nextInt();
            int to = scan.nextInt();
            A[from][to] = 1;
            A[to][from] = 1;
        }
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
