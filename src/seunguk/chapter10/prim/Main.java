package seunguk.chapter10.prim;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
    static int V;
    static int E;
    static int[] parent;
    static ArrayList<Node>[] graph;
    static boolean[] visited;

    static class Node implements Comparable<Node> {
        int end; // 도착지
        int weight; // 가중치

        Node(int end, int weight) {
            this.end = end;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.weight, o.weight);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        V = sc.nextInt();
        E = sc.nextInt();
        parent = new int[V + 1];
        visited = new boolean[V + 1];
        graph = new ArrayList[V + 1];

        for (int i = 0; i < V + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < E; i++) {
            int start = sc.nextInt();
            int end = sc.nextInt();
            int weight = sc.nextInt();

            graph[start].add(new Node(end, weight));
            graph[end].add(new Node(start, weight));
        }

        System.out.println(prim());
    }

    private static int prim() {
        int result = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(1, 0));

        int cnt = 0;
        while (!pq.isEmpty()) {
            // 모든 노드를 방문한 경우
            if (cnt == V) {
                return result;
            }
            Node node = pq.remove();
            int now = node.end;
            int now_weight = node.weight;

            if (visited[now]) {
                continue;
            }

            result += now_weight;
            visited[now] = true;

            // 연결된 노드들 중 방문하지 않은 노드 탐색
            for (Node next_node : graph[now]) {
                if (!visited[next_node.end]) {
                    pq.add(next_node);
                }
            }
        }
        return result;
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
