package annmj.thisiscodingtest.part03.Q15_findcity;

import java.util.*;

public class Main {

    static int N, M, K, X;
    static ArrayList<Integer>[] graph;
    static int[] distance;
    static PriorityQueue<Node> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(o -> o.distance));
    static int MAX_VALUE = 1000000;

    static void process() {
        // 다익스트라 알고리즘
        distance[X] = 0;
        priorityQueue.add(new Node(X, 0));

        while (!priorityQueue.isEmpty()) {
            Node node = priorityQueue.poll();

            // 연결된 간선 정보 찾기
            for (Integer edge : graph[node.to]) {
                if (node.distance + 1 < distance[edge]) {
                    priorityQueue.add(new Node(edge, node.distance + 1));
                    distance[edge] = node.distance + 1;
                }
            }
        }
        int count = 0;
        for (int i = 0; i < distance.length; i++) {
            if (K == distance[i]) {
                count++;
                System.out.println(i);
            }
        }
        if (count == 0) System.out.println(-1);
    }

    public static void main(String[] args) {
        input();
        process();
    }

    static void input() {
        Scanner scanner = new Scanner(System.in);
        N = scanner.nextInt(); // 도시의 개수
        M = scanner.nextInt(); // 도로의 개수
        K = scanner.nextInt(); // 거리정보 ( 거리가 K 인 것의 개수 출력 )
        X = scanner.nextInt(); // 출발 도시의 번호
        graph = new ArrayList[N + 1]; // 1 ~ N 까지 노드 존재
        distance = new int[N + 1];
        Arrays.fill(distance, MAX_VALUE);

        for (int i = 0; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            int from = scanner.nextInt();
            int to = scanner.nextInt();
            graph[from].add(to);
        }
    }

    static class Node {
        int to; // 자기 자신
        int distance; // 시작 점 에서부터의 거리

        public Node(int to, int distance) {
            this.to = to;
            this.distance = distance;
        }
    }
}
