package seunguk.chapter10.kruskal;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static Edge[] arr;
    static int[] parent;
    static class Edge implements Comparable<Edge> {
        int a;
        int b;
        int cost;

        Edge(int a, int b, int cost) {
            this.a = a;
            this.b = b;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.cost, o.cost);
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int V = sc.nextInt();
        int E = sc.nextInt();
        parent = new int[V+1];

        // 부모 테이블상에서, 부모를 자기 자신으로 초기화
        for (int i = 0; i < V + 1; i++) {
            parent[i] = i;
        }

        // 간선의 개수로 초기화
        arr = new Edge[E];

        for (int i = 0; i < E; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int cost = sc.nextInt();
            arr[i] = new Edge(a,b,cost);

        }
        // 오름차순으로 정렬
        Arrays.sort(arr);
        System.out.println(kruskal());
    }
    private static int kruskal() {
        int result = 0;
        for (Edge edge : arr) {
            int a = edge.a;
            int b = edge.b;
            int cost = edge.cost;

            // 서로 루트 노드 값이 같지 않아야지만 간선을 선택한다.
            if (find(a) != find(b)) {
                union(a, b);
                result += cost;
            }
        }
        return result;
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
7 9
1 2 29
1 5 75
2 3 35
2 6 34
3 4 7
4 6 23
4 7 13
5 6 53
6 7 25
*/
