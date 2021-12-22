package seohae.thiscodingtest.part03.Q15_FindCityOnStreet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

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
public class Main2 {
    private static int graph[][];
    static boolean[] visited;

    private static int n; /* 도시의 개수 */
    private static int m; /* 도로의 개수 */
    private static int k; /* 거리 정보 */
    private static int x; /* 출발 도시의 번호 */

    private static int[] resultArr;

    public static void main(String[] args) throws IOException {
        input();

        // dfs
        dfs(x);

        // bfs
        // bfs(x);

        boolean isExist = false;
        for (int i = 1; i < resultArr.length; i++) {
            if (resultArr[i] == k) {
                isExist = true;
                System.out.println(i);
            }
        }

        if (!isExist) {
            System.out.println("-1");
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

        graph = new int[n + 1][n + 1];
        visited = new boolean[n + 1];
        resultArr = new int[n + 1];

        for (int i = 0; i < m; i++) {
            String edge = br.readLine();
            StringTokenizer st1 = new StringTokenizer(edge," ");

            int a = Integer.parseInt(st1.nextToken());
            int b = Integer.parseInt(st1.nextToken());

            graph[a][b] = 1;
        }

        // [
        //   [0, 0, 0, 0, 0],
        //   [0, 0, 1, 1, 0], // 1 -> 2, 3
        //   [0, 0, 0, 1, 1], // 2 -> 3, 4
        //   [0, 0, 0, 0, 0], // 3 -> 0
        //   [0, 0, 0, 0, 0]  // 4 -> 0
        // ]
        System.out.println(Arrays.deepToString(graph));
    }

    static void dfs(int start) {
        /* 방문 */
        //visited[start] = true;

        for (int i = 1; i < graph[start].length; i++) {
            /* 인정행렬일 경우 */
            if (graph[start][i] == 1) { // 간선으로 연결되어있는 경우
                /**
                 * DFS 는 방문처리하면 안됨
                 * 1 -> 2-> 3 에서 방문처리가 되서 2가 됬을때
                 * 3번을 이미 방문되서 1 -> 3 이 계산되지않음
                 *
                 * i : i 노드까지의 최단거리를 저장한다.
                 * start : start 노드까지의 거리 + 1
                 *
                 * 1) start = 1, i = 2) resultArr[2] = resultArr[1] + 1
                 * 2) start = 2, i = 3) resultArr[3] = resultArr[2] + 1
                 * 3) start = 3, x
                 * 4) start = 2, i = 4) resultArr[4] = resultArr[2] + 1
                 * 5) start = 4 x
                 * 6) start = 1, i = 3) resultArr[3] = resultArr[1] + 1
                 *
                 * 호출 순서가 노드 -> 그 노드에 연결되어있는 노드 므로
                 * 마지막에 호출될수록 최단거리이기 때문에 min 체크 없이 바로 설정해주면 된다.
                 */
                //if (!visited[i]) {
                    resultArr[i] = resultArr[start] + 1;
                    dfs(i);
               // }
            }
        }
    }

    static void bfs(int start) {
        /* 방문 */
        visited[start] = true;

        Queue<Integer> queue = new LinkedList<Integer>();
        queue.offer(start);

        while (!queue.isEmpty()) {
            int target = queue.poll();

            for (int i = 1; i < graph[target].length; i++) {
                if (!visited[i] && graph[target][i] == 1) {
                    queue.offer(i);
                    visited[i] = true;
                    /* resultArr[target] (target 까지의 거리) + 1 */
                    resultArr[i] = resultArr[target] + 1;
                }
            }
        }
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
