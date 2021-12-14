package seunguk.chapter10.citydivisionplan;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static int N;
    static int M;
    static Edge[] graph;
    static int[] parent;
    static class Edge implements Comparable<Edge>{
        int A;
        int B;
        int C;

        Edge(int A, int B, int C) {
            this.A = A;
            this.B = B;
            this.C = C;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.C, o.C);
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        graph = new Edge[M];
        parent = new int[N+1];

        // 부모 테이블상에서, 부모를 자기 자신으로 초기화
        for (int i = 0; i < N + 1; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < M; i++) {
            int A = sc.nextInt();
            int B = sc.nextInt();
            int C = sc.nextInt();
            graph[i] = new Edge(A, B, C);
        }

        // 가중치로 정렬
        Arrays.sort(graph);
        System.out.println(kruskal());
    }
    private static int kruskal() {
        int result = 0;
        int last = 0;
        for (Edge edge : graph) {
            int A = edge.A;
            int B = edge.B;
            int C = edge.C;

            // 서로 루트 노드 값이 같지 않아야지만 간선을 선택한다.
            if (find(A) != find(B)) {
                result += C;
                union(A, B);
                // 가장 마지막이 가장 큰 값이므로 가장 마지막 값을 저장해준다.
                last = C;
            }
        }
        return result - last;
    }

    private static void union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x != y) {
            parent[Math.max(x,y)] = Math.min(x,y);
        }
    }

    private static int find(int x) {
        if (x != parent[x]) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }
}

/*
7 12
1 2 3
1 3 2
3 2 1
2 5 2
3 4 4
7 3 6
5 1 5
1 6 2
6 4 1
6 5 3
4 5 3
6 7 4
*/
