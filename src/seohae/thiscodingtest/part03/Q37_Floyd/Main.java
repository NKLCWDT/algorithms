package seohae.thiscodingtest.part03.Q37_Floyd;

import java.util.Arrays;
import java.util.Scanner;

/*
   도시의 개수 n
   한 도시 -> 다른 도시로의 경로로 이동되는 m 개의 버스
   각 버스는 한번 사용할때 필요한 비용 존재
   모든 도시의 쌍(A, B) : 도시 A -> B 로 가는데 필요한 비용의 최솟값

 */
public class Main {
    /* 무한을 의미 */
    public static final int INF = (int) 1e9;

    /* 노드의 개수 (도시) */
    public static int n;

    /* 간선의 개수 (버스) */
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
        for (int a = 1; a <= n; a++) {
            for (int b = 1; b <= n; b++) {
                System.out.print(graph[a][b] + " ");
            }

            System.out.println();
        }
    }

    /*
        5
        14
        1 2 2
        1 3 3
        1 4 1
        1 5 10
        2 4 2
        3 4 1
        3 5 1
        4 5 3
        3 5 10
        3 1 8
        1 4 2
        5 1 7
        3 4 2
        5 2 4
     */
    private static void input() {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt(); // 도시
        m = sc.nextInt(); // 버스

        graph = new int[n + 1][n + 1];

       /* 최단 거리 테이블을 모두 무한으로 초기화 */
        for (int i = 0; i <= n; i++) {
            Arrays.fill(graph[i], INF);
        }

        /* 자기 자신에서 자기 자신으로 가는 비용은 0으로 초기화 */
        for (int a = 1; a <= n; a++) {
            for (int b = 1; b <= n; b++) {
                if (a == b) {
                    graph[a][b] = 0;
                }
            }
        }

        /* 각 간선에 대한 정보를 입력 받아, 그 값으로 초기화 */
        for (int i = 0; i < m; i++) {
            // A에서 B로 가는 비용은 C라고 설정
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();

            if (graph[a][b] > c) { // 최소값 저장
                graph[a][b] = c;
            }
        }
    }
}
