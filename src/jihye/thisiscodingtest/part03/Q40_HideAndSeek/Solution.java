package jihye.thisiscodingtest.part03.Q40_HideAndSeek;

import java.util.*;

class Node {
    int end, weight;

    public Node(int end, int weight) {
        this.end = end;
        this.weight = weight;
    }
}

public class Solution {

    public static ArrayList<Node>[] graph;
    public static boolean[] visited;
    public static int INF = Integer.MAX_VALUE;
    //    public static int[] distance;
    public static int N;
    public static int M;
    static int[] dist;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        graph = new ArrayList[N + 1];
        dist = new int[N + 1];

        Arrays.fill(dist, INF);

        for (int i = 1; i < N + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        //리스트에 그래프 정보 초기화
        for (int i = 0; i < M; i++) {
            int start = sc.nextInt();
            int end = sc.nextInt();
            graph[start].add(new Node(end, 1));
            graph[end].add(new Node(start, 1));
        }

        dijkstra(1);

        int maxDistance = 0;
        int maxNode = 0;
        int sameCount = 0;

        for (int i = 1; i < N + 1; i++) {
            if (maxDistance < dist[i]) {
                maxNode = i;
                maxDistance = dist[i];
                sameCount = 0;
            }
            if (maxDistance == dist[i]) {
                sameCount++;
            }
        }
        System.out.println(maxNode + " " + maxDistance + " " + sameCount);
    }

    public static void dijkstra(int start) {
        Queue<Node> queue = new LinkedList<>();
        boolean[] check = new boolean[N + 1];
        queue.add(new Node(start, 0));
        dist[start] = 0;

        while (!queue.isEmpty()) {//큐가 빌때까지
            Node curNode = queue.poll();//현재노드
            int cur = curNode.end;

            if (check[cur] == true) {//이미 방문했을시에는 패스한다.
                continue;
            }
            check[cur] = true;

            for (Node node : graph[cur]) {//list에서 node를 하나씩 꺼내서
                if (dist[node.end] > dist[cur] + node.weight) {//만일 현재 노드를 거쳐서 가는 경우가 더 짧은경우
                    dist[node.end] = dist[cur] + node.weight;//거리를 더 짧은 걸로 업데이트후
                    queue.add(new Node(node.end, dist[node.end]));//큐에 더해준다
                }
            }
        }
    }
}

