package seunguk.chapter9.telegram;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
    static ArrayList<Node>[] graph;
    static int[] distance;
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
        int N = sc.nextInt();
        int M = sc.nextInt();
        int C = sc.nextInt();
        graph = new ArrayList[N + 1];
        distance = new int[N + 1];
        for (int i = 0; i < N+1; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < N + 1; i++) {
            distance[i] = Integer.MAX_VALUE;
        }

        for (int i = 0; i < M; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            int c = sc.nextInt();

            graph[x].add(new Node(y, c));
        }
        dijkstra(C);

        int count = 0;
        int time = 0;

        for (int i = 1; i < N + 1; i++) {
            if (Integer.MAX_VALUE != distance[i]) {
                count++;
                time = Math.max(time, distance[i]);
            }
        }

        System.out.println(count-1 + " " + time);
    }
    private static void dijkstra(int start) {
        distance[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        
        pq.add(new Node(start, 0));
        
        while (!pq.isEmpty()) {
            Node curNode = pq.remove();
            
            int cur = curNode.end;
            int cur_weight = curNode.weight;
            
            // 목적지 가중치가 cur_weight 보다 작다면 갱신 할 필요없다.
            if (distance[cur] < cur_weight) {
                continue;
            }
            // distance[cur]에 값은 갱신 안해줘도 되나?

            for (Node next_node : graph[cur]) {
                // distance[next_node.end] -> 다음 탐색할 정점 가중치
                // next_node.weight -> 현재 정점에서 다음 정점까지 가중치

                // 다음 정점 까지의 가중치(distance[next_node.end])가
                // 현재 정점에서 다음 정점까지 가중치 (next_node.weight)의 가중치 + 현재 정점 까지 가중치(cur_weight) 보다 크다면 갱신

                // cur_weight를 써야할지 distance[cur]을 써야할지 감이 잘안잡힌다.

                if (distance[next_node.end] > next_node.weight + cur_weight) {
                    distance[next_node.end] = next_node.weight + cur_weight;
                    pq.add(new Node(next_node.end, distance[next_node.end]));
                }
            }
        }
    }
}

/*
3 2 1
1 2 4
1 3 2
*/
