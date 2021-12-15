package seohae.chapter9.S01_futureCity;

import java.util.Arrays;
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

   => 다익스트라 알고리즘
   => 플로이드 워셜 알고리즘
 */
public class Main {
    public static int K; // 소개팅 회사
    public static int X; // 판매 회사

    /* 무한을 의미 */
    public static final int INF = (int) 1e9;

    /* 노드의 개수 */
    public static int n;

    /* 간선의 개수 */
    public static int m;

    /* 2차원 배열(그래프 표현)를 만들기 */
    public static int[][] graph;

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
        int distance = graph[1][K] + graph[K][X];

        System.out.println(distance >= INF ? -1 : distance);
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

        n = sc.nextInt();
        m = sc.nextInt();

        graph = new int[n + 1][n + 1];

        /* 최단 거리 테이블을 모두 무한으로 초기화 */
        for (int i = 0; i <= n; i++) {
            Arrays.fill(graph[i], INF);
        }

        /* 자기 자신에서 자기 자신으로 가는 비용은 0으로 초기화 */
        for (int a = 1; a <= n; a++) {
            for (int b = 1; b <= n; b++) {
                if (a == b) graph[a][b] = 0;
            }
        }

        /* 각 간선에 대한 정보를 입력 받아, 그 값으로 초기화 */
        for (int i = 0; i <= m; i++) {
            /* 마지막 줄은 각 순서대로 K, X */
            if (i == m) {
                X = sc.nextInt();
                K = sc.nextInt();
            } else {
                // A에서 B로 가는 비용은 1 라고 설정
                int a = sc.nextInt();
                int b = sc.nextInt();

                graph[a][b] = 1;
                graph[b][a] = 1;
            }
        }
    }
}
