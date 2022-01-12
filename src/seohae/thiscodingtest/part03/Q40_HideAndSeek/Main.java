package seohae.thiscodingtest.part03.Q40_HideAndSeek;

import java.util.*;
import java.util.stream.IntStream;

/*
   숨바꼭질
   1 ~ N 번까지의 헛간 중에서 하나를 골라 숨을 수 있다.
   술래는 항상 1번 부터 출발한다.

   전체 맵에는 총 M개의 양방향 통로가 존재한다.
   하나의 통로는 서로 다른 두 헛간을 연결한다.
   또한 전체 맵은 항상 어떤 헛간에서 다른 어떤 헛간으로 도달이 가능한 형태로 주어진다.

   최단거리가 가장 먼 헛간을 찾자.

   - 여러 경로를 거쳐간다. -> 플로이드 워셜 알고리즘
 */
public class Main {
    /* 무한을 의미 */
    private static final int INF = (int) 1e9;

    /* 노드의 개수 */
    private static int n;

    /* 간선의 개수 */
    private static int m;

    /* 2차원 배열(그래프 표현)를 만들기 */
    private static int[][] graph = new int[501][501];

    private static int resultMaxDistance = Integer.MIN_VALUE;
    private static int resultNodeNum;
    private static int resultSameDistanceCnt;

    public static void main(String[] args) {
        input();

        /* 점화식에 따라 플로이드 워셜 알고리즘을 수행 */
        for (int k = 1; k <= n; k++) {
            for (int a = 1; a <= n; a++) {
                for (int b = 1; b <= n; b++) {
                    graph[a][b] = Math.min(graph[a][b], graph[a][k] + graph[k][b]);
                }
            }
        }

        /* 수행된 결과를 출력 */
        // 1) 숨어야하는 헛간 번호
        // 2) 그 헛간까지의 거리
        // 3) 그 헛간과 같은 거리를 갖는 헛간의 개수

        /* *************************** */

        // 1) 숨어야하는 헛간 번호
        // 2) 그 헛간까지의 거리
        int max = Integer.MAX_VALUE;
        for (int b = 1; b <= n; b++) {
            if (graph[1][b] != INF) {
                resultMaxDistance = Math.max(resultMaxDistance, graph[1][b]);

                // 갱신
                if (max != resultMaxDistance) {
                    max = resultMaxDistance;

                    resultNodeNum = b;
                }
            }
        }

        // 3) 그 헛간과 같은 거리를 갖는 헛간의 개수
        IntStream.rangeClosed(1, n)
                .filter(i -> resultSameDistanceCnt == graph[1][i])
                .forEach(i -> resultSameDistanceCnt++);

        // result
        System.out.print(resultNodeNum + " " + resultMaxDistance + " " + resultSameDistanceCnt);
    }

    /*
        6 7
        3 6
        4 3
        3 2
        1 3
        1 2
        2 4
        5 2
     */
    private static void input() {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();

        /* 최단 거리 테이블을 모두 무한으로 초기화 */
        for (int i = 0; i < 501; i++) {
            Arrays.fill(graph[i], INF);
        }

        /* 자기 자신에서 자기 자신으로 가는 비용은 0으로 초기화 */
        for (int a = 1; a <= n; a++) {
            for (int b = 1; b <= n; b++) {
                if (a == b) graph[a][b] = 0;
            }
        }

        /* 각 간선에 대한 정보를 입력 받아, 그 값으로 초기화 */
        for (int i = 0; i < m; i++) {
            // A에서 B로 가는 비용은 C라고 설정
            int a = sc.nextInt();
            int b = sc.nextInt();

            graph[a][b] = 1;
        }
    }
}