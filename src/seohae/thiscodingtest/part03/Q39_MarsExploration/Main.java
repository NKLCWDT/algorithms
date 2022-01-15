package seohae.thiscodingtest.part03.Q39_MarsExploration;

import java.util.*;

/*
   에너지를 효율적으로 사용하고자 화성 탐사 기계가 출발 지점에서 목표 지점까지 이동할때
   항상 최적의 경로를 찾아야한다.

   N x N 크기의 2차원 배열

   각각의 칸을 지나기 위한 비용이 존재한다.

   가장 왼쪽칸 [0][0] 에서 가장 오른쪽 아래칸 [N - 1][N - 1] 위치로 이동하는 최소비용을 출력

   화성탐사 기계는 특정한 위치에서 상하좌우 인접한 곳으로 1칸씩 이동할 수 있다.


   - 구현 후 로직 디버깅 (distance)
    5 5 4
    3 9 1
    3 2 7 의 경우

    (1)
    [1000000000, 1000000000, 1000000000],
    [1000000000, 1000000000, 1000000000],
    [1000000000, 1000000000, 1000000000]

    (2)
    [5, 1000000000, 1000000000],
    [8, 1000000000, 1000000000],
    [1000000000, 1000000000, 1000000000]

    (3) 10은 5 + 5 (상하좌우로만 이동이 가능할때, 좌에서 오는 경우가 최단)
    [5, 10, 1000000000],
    [8, 1000000000, 1000000000],
    [1000000000, 1000000000, 1000000000]

    (4) 11은 8 + 3 (상 (8) + 3)
    [5, 10, 1000000000],
    [8, 1000000000, 1000000000],
    [11, 1000000000, 1000000000]

    (5) 17은 8 + 9
    [5, 10, 1000000000],
    [8, 17, 1000000000],
    [11, 1000000000, 1000000000]

    (6)
    [5, 10, 14],
    [8, 17, 1000000000],
    [11, 1000000000, 1000000000]

    (7)
    [5, 10, 14],
    [8, 17, 1000000000],
    [11, 13, 1000000000]

    (8)
    [5, 10, 14],
    [8, 17, 1000000000],
    [11, 13, 20]

    (9) 결국 distance[N-1][N-1] 20이 결과다.
    [5, 10, 14],
    [8, 17, 15],
    [11, 13, 20]
 */
public class Main {
    /* 무한을 의미하는 값으로 10억을 설정 */
    static final int INF = (int) 1e9;

    static int num;
    static int N;
    static int[][] graph;
    static int[][] distance;
    static List<Integer> resultList = new ArrayList<>();

    public static void main(String[] args) {
        input();
        resultList.stream().forEach(System.out::println);
    }

    /*
    3
    3
    5 5 4
    3 9 1
    3 2 7
    5
    3 7 2 0 1
    2 8 0 9 1
    1 2 1 8 1
    9 8 9 2 0
    3 6 5 1 5
    7
    9 0 5 1 1 5 3
    4 1 2 1 6 5 3
    0 7 6 1 6 8 5
    1 1 7 8 3 2 3
    9 4 0 7 6 4 1
    5 8 3 2 4 8 3
    7 4 8 4 8 3 4
     */
    private static void input() {
        Scanner sc = new Scanner(System.in);
        num = sc.nextInt();

        for (int n = 0; n < num; n++) {
            N = sc.nextInt();

            graph = new int[N][N];
            distance = new int[N][N];

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    graph[i][j] = sc.nextInt();
                    distance[i][j] = INF;
                }
            }

            /* 알고리즘 수행 */
            dijkstra(0, 0);

            resultList.add(distance[N - 1][N - 1]);
        }
    }

    /**
     * 기존에 공부했던 다익스트라를 2차배열로 구성하여 최단거리 update
     * @param x
     * @param y
     */
    private static void dijkstra(int x, int y) {
        /* 우선순위 큐 선언 */
        PriorityQueue<MarsNode> pq = new PriorityQueue<>();

        /* 시작 노드로 가기 위한 최단 경로는 0으로 설정하여, 큐에 삽입 */
        distance[x][y] = graph[x][y];
        pq.offer(new MarsNode(x, y, distance[x][y]));

        /* 큐가 비어있지않을 때까지 반복 */
        while (!pq.isEmpty()) {
            /* 가장 최단 거리가 짧은 노드에 대한 정보 꺼내기 */
            MarsNode node = pq.poll();

            /* 현재의 정보 */
            int nowDistance = node.getDistance();
            int nowX = node.getX();
            int nowY = node.getY();

            /* 이동 : 상, 하, 좌, 우 */
            int[] dx = new int[]{-1, 1, 0, 0};
            int[] dy = new int[]{0, 0, -1, 1};

            for (int i = 0; i < 4; i++) {
                /* 이동 정보 */
                int nx = nowX + dx[i];
                int ny = nowY + dy[i];

                /* 이동 가능한 지점일 경우 */
                if (nx >= 0 && ny >= 0 && nx < N && ny < N) {
                    /* nowDistance : 현재 지점의 거리
                       graph[nx][ny] : 이동될 지점의 값
                       distance[nx][ny] : 이동될 지점의 현재까지의 최단거리
                     */
                    if (nowDistance + graph[nx][ny] < distance[nx][ny]) {
                        /* 최단거리 갱신 */
                        distance[nx][ny] = nowDistance + graph[nx][ny];

                        /* 큐 삽입 */
                        pq.add(new MarsNode(nx, ny, distance[nx][ny]));
                    }
                }

                // 결과 지점이 셋팅되었다면 break;
                if (distance[N - 1][N - 1] != INF) {
                    break;
                }
            }
        }

    }
}

class MarsNode implements Comparable<MarsNode> {

    private int x;
    private int y;
    private int distance;

    public MarsNode(int x, int y, int distance) {
        this.x = x;
        this.y = y;
        this.distance = distance;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    // 거리(비용)가 짧은 것이 높은 우선순위를 가지도록 설정
    @Override
    public int compareTo(MarsNode other) {
        if (this.distance < other.distance) {
            return -1;
        }
        return 1;
    }
}
