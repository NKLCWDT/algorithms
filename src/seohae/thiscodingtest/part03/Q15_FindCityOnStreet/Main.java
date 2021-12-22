package seohae.thiscodingtest.part03.Q15_FindCityOnStreet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

/*
   1 ~ N번까지의 도시와 M개의 단방향 도로
   특정한 도시 X 로부터 출발하여 도달할 수 있는 모든 도시 중에서, 최단 거리가 정확히 K인 모든 도시의 번호 출력

   모든 노드를 방문해서 최단경로와 K 동일여부 체크
   -> DFS/BFS

   N = 4 (도시 개수)
   K = 2 (최단거리 조건)
   X = 1 (출발 노드)

   1번 도시에서 출발하여 도달할 수 있는 도시 중에서, 최단 거리가 2인 도시 : 4번
   1 -> 2 -> 4
   1 -> 2 -> 3 (X : 최단거리는 1 -> 3 으로 1이다)
 */
public class Main {
    /* 무한을 의미하는 값으로 10억을 설정 */
    public static final int INF = (int) 1e9;

    private static int n; /* 도시의 개수 */
    private static int m; /* 도로의 개수 */
    private static int k; /* 거리 정보 */
    private static int x; /* 출발 도시의 번호 */

    /* 최단 거리 테이블 만들기 */
    public static int[] d;

    /* 각 노드에 연결되어 있는 노드에 대한 정보를 담는 배열 */
    public static ArrayList<ArrayList<CityQueue>> graph = new ArrayList<ArrayList<CityQueue>>();

    public static void main(String[] args) throws IOException {
        input();

        /* 다익스트라 알고리즘을 수행 */
        dijkstra(x);

        /* 결과 출력 */
        IntStream intStream = IntStream.range(1, n + 1).filter(i -> d[i] == k);
        if (intStream.count() > 0) {
            IntStream.range(1, n + 1).filter(i -> d[i] == k).forEach(System.out::println);
        } else {
            System.out.println(-1);
        }
    }

    private static void dijkstra(int start) {
        /* 우선순위 큐 선언 */
        PriorityQueue<CityQueue> pq = new PriorityQueue<>();

        /* 시작 노드로 가기 위한 최단 경로는 0으로 설정하여, 큐에 삽입 */
        pq.offer(new CityQueue(start, 0));
        d[start] = 0;

        /* 큐가 비어있지않을 때까지 반복 */
        while(!pq.isEmpty()) {
            /* 가장 최단 거리가 짧은 노드에 대한 정보 꺼내기 */
            CityQueue node = pq.poll();

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
                    pq.offer(new CityQueue(graph.get(now).get(i).getIndex(), cost));
                }
            }
        }
    }
    
    /*
      도시의 개수 N, 도로의 개수 M, 거리 정보 K, 출발 도시의 번호 X
      4 4 2 1
      1 2
      1 3
      2 3
      2 4
     */
    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s = br.readLine();
        StringTokenizer st = new StringTokenizer(s," ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());

        d = new int[n + 1];

        /* 그래프 초기화 */
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<CityQueue>());
        }

        /* 모든 간선 정보를 입력받기 */
        for (int i = 1; i <= m; i++) {
            String edge = br.readLine();
            StringTokenizer st1 = new StringTokenizer(edge," ");

            int a = Integer.parseInt(st1.nextToken());
            int b = Integer.parseInt(st1.nextToken());

            /* a번 노드에서 b번 노드로 가는 비용이 c라는 의미 */
            graph.get(a).add(new CityQueue(b, 1));
        }

        /* 최단 거리 테이블을 모두 무한으로 초기화 */
        Arrays.fill(d, INF);
    }
}

class CityQueue implements Comparable<CityQueue> {

    private int index;
    private int distance;

    public CityQueue(int index, int distance) {
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
    public int compareTo(CityQueue other) {
        if (this.distance < other.distance) {
            return -1;
        }
        return 1;
    }
}
/*
  4 3 2 1
  1 2
  1 3
  1 4
 */

/*
  4 4 1 1
  1 2
  1 3
  2 3
  2 4
 */
