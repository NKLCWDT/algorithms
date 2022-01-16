package jungho.thisiscodingtest.part03.Q39_explorationofmars;

/*
    가장 왼쪽 위칸 [0][0] 에서 가장 오른쪽 아래 칸인 [N-1][N-1] 위치로 이동하는
    최소 비용을 출력
    화성 탐사 기계는 상하좌우 인접한 곳으로 1칸씩 이동 가능
*/
import java.util.*;

// 화성 탐사
public class Main {

    static class Node implements Comparable<Node> {
        private int x;
        private int y;
        private int cost;

        public Node(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }

    static int T;
    static List<int[][]> graphs = new ArrayList<>();
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[][] shortestPaths;
    static int INF = 10000000;

    public static void main(String[] args) {
        input();
        solution();
    }

    static void input() {
        Scanner sc = new Scanner(System.in);
        T = sc.nextInt();

        for (int i = 0; i < T; i++) {
            int graphSize = sc.nextInt();
            int[][] graph = new int[graphSize][graphSize];

            for (int j = 0; j < graphSize; j++) {
                for (int k = 0; k < graphSize; k++) {
                    graph[j][k] = sc.nextInt();
                }
            }

            graphs.add(graph);
        }
    }

    static void solution() {
        for (int i = 0; i < graphs.size(); i++) {
            int[][] graph = graphs.get(i);

            // 최단거리 테이블 초기화
            shortestPaths = new int[graph.length][graph.length];
            for (int x = 0; x < graph.length; x++) {
                Arrays.fill(shortestPaths[x], INF);
            }

            dijkstra(graph, shortestPaths);
        }
    }

    /**
     * BFS 수행
     * Ex. (0,0) -> (0,1) or (1,0) 으로 갈때, 이동한 좌표에 최단거리를 누적
     *  즉 (0,1) 에는 (0,0) 에 대한 COST + (0,1) 에 대한 COST 저장
     * @param graph
     * @param shortestPaths
     */
    static void dijkstra(int[][] graph, int[][] shortestPaths) {
        PriorityQueue<Node> Q = new PriorityQueue<>();
        Q.offer(new Node(0, 0, graph[0][0]));
        shortestPaths[0][0] = graph[0][0];
        int graphSize = graph.length;

        while(!Q.isEmpty()) {
            Node node = Q.poll();

            // 상하좌우 탐색
            for (int i = 0; i < dx.length; i++) {
                int nx = node.x + dx[i];
                int ny = node.y + dy[i];

                if(isInGraph(nx, ny, graphSize)) {
                    int newCost = node.cost + graph[nx][ny];

                    // 탐색한 노드에 대한 최단거리 비용이 더 작은 경우(처리된 적이 있는 경우)
                    if(shortestPaths[nx][ny] < newCost) {
                        continue;
                    }

                    shortestPaths[nx][ny] = newCost;
                    Q.offer(new Node(nx, ny, newCost));
                }
            }

            // 마지막 값을 갱신 했으면 while 탈출
            // 우선순위 큐이기 때문에 큐에 데이터가 남아있어도 탈출 가능
            if(shortestPaths[graphSize - 1][graphSize - 1] != INF) {
                break;
            }
        }

        System.out.println(shortestPaths[graphSize - 1][graphSize - 1]);
    }

    static boolean isInGraph(int x, int y, int graphSize) {
        return x >= 0 && y >= 0 && x < graphSize && y < graphSize;
    }
}
