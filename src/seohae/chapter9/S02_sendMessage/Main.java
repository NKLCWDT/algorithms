package seohae.chapter9.S02_sendMessage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.stream.IntStream;

/*
  도시의 개수 : N개
  각 도시는 메시지를 보내기위해, 다른도시로 전보를 보내서 다른 도시로 해당 메시지를 전송할 수 있다.

  X -> Y 통로 존재 , Y -> X 통로 없다면 Y -> X 는 발송이 불가능하다.

  특정 도시에서 메시지를 보낼때 수신이 가능한 도시의 총 개수와, 모두 받는데까지 걸리는 총 시간은?
 */
public class Main {
    /* 무한을 의미하는 값으로 10억을 설정 */
    public static final int INF = (int) 1e9;

    public static int n; // 도시의 개수
    public static int m; // 통로의 개수

    public static int C; // 메시지를 보내고자 하는 도시

    /* 각 노드에 연결되어 있는 노드에 대한 정보를 담는 배열 */
    public static ArrayList<ArrayList<QueueCityNode>> graph = new ArrayList<ArrayList<QueueCityNode>>();

    /* 최단 거리 테이블 만들기 */
    public static int[] d = new int[100001];
    
    public static void main(String[] args) {
        input();

        /* 다익스트라 알고리즘을 수행 */
        dijkstra(C);

        /* 결과 추출 */
        int count = -1;
        int maxDistance = 0;

        for (int distance : d) {
            if (distance != INF) {
                count++;
                maxDistance = Math.max(distance, maxDistance);
            }
        }

        /* 도시의 총 개수 / 총 걸리는 시간 */
        System.out.println(count + " " + maxDistance);
    }

    /**
     3 2 1
     1 2 4
     1 3 2
     */
    private static void input() {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt(); // 노드의 개수(N)
        m = sc.nextInt(); // 간선의 개수(M)
        C = sc.nextInt(); // 시작 노드 번호(Start)

        /* 그래프 초기화 */
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<QueueCityNode>());
        }

        /* 모든 간선 정보를 입력받기 */
        for (int i = 0; i < m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();

            /* a번 노드에서 b번 노드로 가는 비용이 c라는 의미 */
            graph.get(a).add(new QueueCityNode(b, c));
        }

        /* 최단 거리 테이블을 모두 무한으로 초기화 */
        Arrays.fill(d, INF);
    }

    private static void dijkstra(int start) {
        /* 우선순위 큐 선언 */
        PriorityQueue<QueueCityNode> pq = new PriorityQueue<>();

        /* 시작 노드로 가기 위한 최단 경로는 0으로 설정하여, 큐에 삽입 */
        pq.offer(new QueueCityNode(start, 0));
        d[start] = 0;

        /* 큐가 비어있지않을 때까지 반복 */
        while(!pq.isEmpty()) {
            /* 가장 최단 거리가 짧은 노드에 대한 정보 꺼내기 */
            QueueCityNode node = pq.poll();

            int dist = node.getDistance(); // 현재 노드까지의 비용
            int now = node.getIndex(); // 현재 노드 번호

            /* 현재 노드가 이미 처리된 적이 있는 노드라면 무시 */
            if (d[now] < dist) {
                continue;
            }

            /* 현재 노드와 연결된 다른 인접한 노드들을 확인 */
            for (int i = 0; i < graph.get(now).size(); i++) {
                /* 현재의 최단거리 + 현재의 연결된 노드의 비용 */
                int cost = d[now] + graph.get(now).get(i).getDistance();

                /* 현재 노드를 거쳐서 다른 노드로 이동하는 거리가 더 짧은 경우 */
                if (cost < d[graph.get(now).get(i).getIndex()]) {
                    d[graph.get(now).get(i).getIndex()] = cost;
                    pq.offer(new QueueCityNode(graph.get(now).get(i).getIndex(), cost));
                }
            }
        }
    }
}

class QueueCityNode implements Comparable<QueueCityNode> {

    private int index;
    private int distance;

    public QueueCityNode(int index, int distance) {
        this.index = index;
        this.distance = distance;
    }

    public int getIndex() {
        return this.index;
    }

    public int getDistance() {
        return this.distance;
    }

    // 거리(비용)가 짧은 것이 높은 우선순위를 가지도록 설정
    @Override
    public int compareTo(QueueCityNode other) {
        if (this.distance < other.distance) {
            return -1;
        }
        return 1;
    }
}
