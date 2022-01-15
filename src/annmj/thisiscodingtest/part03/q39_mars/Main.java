package annmj.thisiscodingtest.part03.q39_mars;

import java.util.*;

public class Main {
    static int N;    /* 공간의 크기 */
    static int[] a;
    static List<Edge>[] graph;
    static int[] dir = {0, 0, -1, 1};
    static boolean[] visit;
    static int[] dist;
    static Scanner scanner = new Scanner(System.in);

    static class Node implements Comparable<Node> {
        private int index;
        private int cost;

        public Node(int index, int cost) {
            this.index = index;
            this.cost = cost;
        }

        public int getIndex() {
            return index;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }

    static class Edge {
        private int end; // 목적지
        private int cost; // 비용

        public Edge(int end, int cost) {
            this.end = end;
            this.cost = cost;
        }

        public int getEnd() {
            return end;
        }

        public int getCost() {
            return cost;
        }
    }


    public static void main(String[] args) {
        int T = scanner.nextInt();
        for (int i = 0; i < T; i++) {
            input();
            findMinCost(graph, 0);
        }
    }

    private static void input() {
        N = scanner.nextInt();
        a = new int[N * N];
        graph = new ArrayList[N * N];    /* 3 이라면 3 * 3 = 9 개의 노드를 만들어 준다. */
        visit = new boolean[N * N];
        dist = new int[N * N];
        dir[0] = -N;
        dir[1] = N;
        Arrays.fill(dist, Integer.MAX_VALUE);

        for (int i = 0; i < N * N; i++) {
            a[i] = scanner.nextInt();
            graph[i] = new ArrayList<>();
        }

        /* graph 로 변환하자 */
        for (int i = 1; i < a.length; i++) {
            for (int j = 0; j < 4; j++) {
                int nx = i + dir[j];
                if (notIncludePoint(nx, i)) {
                    continue;
                }
                graph[nx].add(new Edge(i, a[i]));
            }
        }
    }

    private static boolean notIncludePoint(int nx, int i) {
        return nx < 0 || (nx >= N * N)
                || ((i % N == N - 1) && (nx % N == 0))   /* 행의 우측 끝과 다음 행의 좌측 끝은 연결하지 않는다. */
                || ((i % N == 0) && (nx % N == N - 1));  /* 행의 좌측 끝과 이전 행의 우측 끝은 연결하지 않는다. */
    }

    private static void findMinCost(List<Edge>[] graph, int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, a[0]));
        dist[0] = a[0];

        while (!pq.isEmpty()) {
            Node node = pq.poll();
            for (Edge to : graph[node.getIndex()]) {
                if (dist[to.getEnd()] > dist[node.getIndex()] + to.getCost()) {
                    dist[to.getEnd()] = dist[node.getIndex()] + to.getCost();
                    pq.add(new Node(to.getEnd(), dist[to.getEnd()]));
                }
            }
        }

        System.out.println(dist[N * N - 1]);
    }
}

/**
 * 3
 * 3
 * 5 5 4
 * 3 9 1
 * 3 2 7
 * 5
 * 3 7 2 0 1
 * 2 8 0 9 1
 * 1 2 1 8 1
 * 9 8 9 2 0
 * 3 6 5 1 5
 * 7
 * 9 0 5 1 1 5 3
 * 4 1 2 1 6 5 3
 * 0 7 6 1 6 8 5
 * 1 1 7 8 3 2 3
 * 9 4 0 7 6 4 1
 * 5 8 3 2 4 8 3
 * 7 4 8 4 8 3 4
 */