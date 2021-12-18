package annmj.thisiscodingtest.part02.chapter9.telegram;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
    static FastReader scan = new FastReader();

    static class Edge {
        int to; // 목적지
        int weight; // 가중치

        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    static class Info {
        // v, d 저장할 클래스
        int index;
        int distance;

        public Info(int index, int distance) {
            this.index = index;
            this.distance = distance;
        }
    }

    static int N,M,C;
    static int[] dist;
    static ArrayList<Edge>[] edges;

    static void input() {
        N = scan.nextInt();
        M = scan.nextInt();
        C = scan.nextInt();
        dist = new int[N + 1]; // 시작점 부터 N 까지 최단 거리를 가지고 있을 배열
        edges = new ArrayList[N + 1];
        for (int i = 0; i < N+1; i++) {
            edges[i] = new ArrayList<>(); // i 와 연결된 엣지들
        }

        for (int i = 0; i < M; i++) {
            edges[scan.nextInt()].add(new Edge(scan.nextInt(), scan.nextInt())); // 1 에서 2 로 가는데 4 소요.
        }
    }

    static void process() {
        // 다익스트라

        // dist 초기화
        for(int i =0; i<=N; i++) dist[i] = Integer.MAX_VALUE;

        dist[C] = 0; // 출발점은 0 으로 초기화 시킴

        PriorityQueue<Info> priorityQueue = new PriorityQueue<>(((o1, o2) -> o1.distance - o2.distance)); // 오름차순 정렬 이므로 가장 낮은 value 가 앞에 있다.

        priorityQueue.add(new Info(C, 0));

        while (!priorityQueue.isEmpty()) {
            Info info = priorityQueue.poll();

            // 가치 있는지 판단 , 원래 알고 있던 최단거리보다 크면 패스
            if(info.distance > dist[info.index]) continue;
            
            for (Edge edge : edges[info.index]) {
                if(dist[edge.to] > dist[info.index] + edge.weight) {
                    // 거리 갱신
                    dist[edge.to] = dist[info.index] + edge.weight;
                    priorityQueue.add(new Info(edge.to, dist[edge.to]));
                }
            }
        }

        /*Arrays.sort(dist, 1, dist.length);
        int count = 0;
        int totalTime = 0;

        for (int i = 2; i <= N; i++) {
            if(Integer.MAX_VALUE > dist[i]) {
                totalTime = dist[i];
                count++;
            }
        }

        System.out.print(count + " " + totalTime);
        */


        // 78 ~ 89 line refactoring
        int count = 0;
        int totalTime = 0;

        for (int i = 0; i <= N; i++) {
            if(Integer.MAX_VALUE != dist[i]) {
                count++;
                totalTime = Math.max(totalTime, dist[i]);
            }
        }

        System.out.print(count-1 + " " + totalTime);
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

