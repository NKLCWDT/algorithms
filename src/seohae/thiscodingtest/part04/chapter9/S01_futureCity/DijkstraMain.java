package seohae.thiscodingtest.part04.chapter9.S01_futureCity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

/*
   공중 미래 도시 : 1번 ~ N번까지의 회사 존재
   특정 회사끼리는 서로 도로를 통해 연결되어있음

   방문 판매원 A의 위치 : 1번 노드
   방문 해야하는 노드 : X번 노드

   공중 미래도시에서 특정 회사에 도착하기 위한 방법은 회사끼리 연결되어 있는 도로를 이용하는 방법이 유일하다.
   특정 회사와 다른 회사가 도로로 연결되어있다면 소요 시간 : 1

   판매원 A의 필요 경로
   1) K번 회사에서 소개팅
   2) X번 회사에서 물건 판매

   판매원 A는 가장 빠른 경로로 1번 회사 -> K번 회사 -> X번 회사를 방문해야한다.
   최소 시간을 계산해야한다.

   N = 5 (=회사 개수)
   X = 4 (=물건 판매할 회사 번호)
   K = 5 (=소개팅 회사 번호)

   (1번, 2번)
   (1번, 3번)
   (1번, 4번)
   (2번, 4번)
   (3번, 4번)
   (3번, 5번)
   (4번, 5번)

   1번 -> 3번 -> 5번 -> 4번 : 시간 3

   => 플로이드 워셜 알고리즘
   => 다익스트라 알고리즘 구현 시도 -> 특정 노드를 거쳐서 가는 비용? 못구함
 */
public class DijkstraMain {
    /* 무한을 의미하는 값으로 10억을 설정 */
    public static final int INF = (int) 1e9;

    public static int N; // 회사 개수
    public static int M; // 경로 개수

    public static int K; // 소개팅 회사
    public static int X; // 판매 회사

    /* 최단 거리 테이블 만들기 */
    public static int[] d = new int[100001];

    /* 각 노드에 연결되어 있는 노드에 대한 정보를 담는 배열 */
    public static ArrayList<ArrayList<QueueNode>> graph = new ArrayList<ArrayList<QueueNode>>();

    public static void main(String[] args) {
        input();

        /* 다익스트라 알고리즘을 수행 */
        dijkstra(1);

        /* 수행된 결과를 출력 */
        int distance = -1;

        /**
         * 1 -> 3 -> 5 -> 4 경로의 최단거리 추출 불가
         */
        try {
            int firstDt = 0;
            int secondDt = 0;
            for (ArrayList<QueueNode> queueNodes : graph) {
                for (int j = 0; j < queueNodes.size(); j++) {
                    if (queueNodes.get(j).getIndex() == K) {
                        firstDt = queueNodes.get(j).getDistance();
                    }

                    if (j == K && queueNodes.get(K).getIndex() == X) {
                        secondDt = queueNodes.get(K).getDistance();
                    }
                }
            }

            distance = firstDt + secondDt;
        } catch (IndexOutOfBoundsException ignore) {}

        System.out.println(distance);
    }

    /**
     5 7
     1 2
     1 3
     1 4
     2 4
     3 4
     3 5
     4 5
     4 5
     */
    private static void input() {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt(); // 노드의 개수(N)
        M = sc.nextInt(); // 간선의 개수(M)

        /* 그래프 초기화 */
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<QueueNode>());
        }

        /* 모든 간선 정보를 입력받기 */
        for (int i = 0; i <= M; i++) {
            /* 마지막 줄은 각 순서대로 K, X */
            if (i == M) {
                X = sc.nextInt();
                K = sc.nextInt();
            } else {
                int a = sc.nextInt();
                int b = sc.nextInt();

                /* a번 노드에서 b번 노드로 가는 비용이 c라는 의미 */
                graph.get(a).add(new QueueNode(b, 1));
                graph.get(b).add(new QueueNode(a, 1));
            }
        }



        /* 최단 거리 테이블을 모두 무한으로 초기화 */
        Arrays.fill(d, INF);
    }

    private static void dijkstra(int start) {
        /* 우선순위 큐 선언 */
        PriorityQueue<QueueNode> pq = new PriorityQueue<>();

        /* 시작 노드로 가기 위한 최단 경로는 0으로 설정하여, 큐에 삽입 */
        pq.offer(new QueueNode(start, 0));
        d[start] = 0;

        /* 큐가 비어있지않을 때까지 반복 */
        while(!pq.isEmpty()) {
            /* 가장 최단 거리가 짧은 노드에 대한 정보 꺼내기 */
            QueueNode node = pq.poll();

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
                    pq.offer(new QueueNode(graph.get(now).get(i).getIndex(), cost));
                }
            }
        }
    }
}

class QueueNode implements Comparable<QueueNode> {

    private int index;
    private int distance;

    public QueueNode(int index, int distance) {
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
    public int compareTo(QueueNode other) {
        if (this.distance < other.distance) {
            return -1;
        }
        return 1;
    }
}
