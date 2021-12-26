package jihye.thisiscodingtest.part03.Q37_Floid;

import java.util.Arrays;
import java.util.Scanner;

public class Solution {
    static int n;
    static int m;
    static int graph[][];
    static int INF = 100000;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        graph = new int[n + 1][n + 1];

        for(int i=0; i < n+1; i++){
            Arrays.fill(graph[i], INF);
        }

        for(int i=1; i< n+1; i++){
            graph[i][i] = 0;
        }

        for (int i = 0; i < m; i++) {
            int start = sc.nextInt();
            int end = sc.nextInt();
            int weight = sc.nextInt();
            graph[start][end] = Math.min(graph[start][end], weight);
        }

        floid();

        //출력한다.
        for (int start = 1; start < n + 1; start++) {
            for (int end = 1; end < n + 1; end++) {
                if (graph[start][end] == INF) {
                    System.out.print("0" + " ");
                } else {
                    System.out.print(graph[start][end] + " ");
                }
            }
            System.out.println();
        }


    }

    //플로이드 워셜 알고리즘 수행
    public static void floid() {
        for (int k = 1; k < n + 1; k++) {//중간에 경우해서 갈때 가중치의 합이 더 작은 경우 변경
            for (int start = 1; start < n + 1; start++) {
                for (int end = 1; end < n + 1; end++) {
                    graph[start][end] = Math.min(graph[start][end], graph[start][k] + graph[k][end]);
                }
            }
        }
    }
}
