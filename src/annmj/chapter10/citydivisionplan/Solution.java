package annmj.chapter10.citydivisionplan;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {
    static FastReader scan = new FastReader();

    static void input() {
        N = scan.nextInt();
        M = scan.nextInt();
        visited = new boolean[N + 1];

        roots = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            roots[i] = i;
        }

        for (int i = 0; i < M; i++) {
            int from = scan.nextInt();
            int to = scan.nextInt();
            int distance = scan.nextInt();
            completeLoads.add(new Load(from, to, distance));
        }
    }

    static int N, M; /* 집의 갯수 : N , 길의 갯수 : M */
    static boolean[] visited;
    static PriorityQueue<Load> completeLoads = new PriorityQueue<>(((o1, o2) -> o1.distance - o2.distance)); // 전체 도시 도로
    static int[] roots; // 각 노드들의 루트 노드들

    static void addNode(int a, int b) {
        a = findRootNode(a);
        b = findRootNode(b);

        if (a < b) {
            roots[b] = a;
        } else {
            roots[a] = b;
        }
    }

    static int findRootNode(int a) {
        if (roots[a] == a) {
            return a;
        } else {
            roots[a] = findRootNode(roots[a]);
        }

        return roots[a];
    }

    static boolean isSameRootNode(int a, int b) {
        return findRootNode(a) == findRootNode(b);
    }

    static void process() {
        int max = 0;  // 빼려는 최대값
        int cost = 0; // 유지비
        while (!completeLoads.isEmpty()) {
            Load load = completeLoads.poll();
            if (isSameRootNode(load.from, load.to)) {
                continue;
            }

            if (max < load.distance) {
                max = load.distance;
            }
            cost += load.distance;
            addNode(load.from, load.to);
//                for (int a : roots) {
//                    System.out.print(a + "   ");
//                }
//                System.out.println("load = " + load);
        }
        System.out.println(cost - max);
    }

    public static void main(String[] args) {
        input();
        process();
    }

    static class Load {
        int from;
        int to;
        int distance;

        public Load(int from, int to, int distance) {
            this.from = from;
            this.to = to;
            this.distance = distance;
        }

        @Override
        public String toString() {
            return "Load{" +
                    "from=" + from +
                    ", to=" + to +
                    ", distance=" + distance +
                    '}';
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

