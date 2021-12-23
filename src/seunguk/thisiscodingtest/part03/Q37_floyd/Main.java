package seunguk.thisiscodingtest.part03.Q37_floyd;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static int[][] graph;
    static int INF = (int)1e9;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        graph = new int[n+1][n+1];

        // INF로 초기화
        for (int i = 1; i < n+1; i++) {
            Arrays.fill(graph[i], INF);
        }

        // 자기 자신은 0으로 초기화
        for (int a = 1; a < n+1; a++) {
            for (int b = 1; b < n+1; b++) {
                if (a == b) graph[a][b] = 0;
            }
        }

        for (int i = 0; i < m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();
            if (graph[a][b] > c) { // 간선이 여러개 이므로 가장 작은 값만 저장
                graph[a][b] = c;
            }
        }

        // 플로이드
        for (int k = 1; k < n + 1; k++) {
            for (int i = 1; i < n + 1; i++) {
                for (int j = 1; j < n + 1; j++) {
                    graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
                }
            }
        }

        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                if (graph[i][j] == INF) { // 가는 방법이 없는 경우 0으로 초기화
                    graph[i][j] = 0;
                }
                System.out.print(graph[i][j] + " ");
            }
            System.out.println();
        }

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
