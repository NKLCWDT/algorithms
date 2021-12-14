package seunguk.chapter9.futurecity;

import java.util.Scanner;

public class Main {
    static int[][] arr;
    static int INF = (int)1e9;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        arr = new int[N+1][N+1];

        // INF로 초기화
        for (int i = 0; i < N+1; i++) {
            for (int j = 0; j < N+1; j++) {
                if (i == j) {
                    arr[i][j] = 0;
                } else {
                    arr[i][j] = INF;
                }
            }
        }

        // 간선을 입력받아 1로 초기화
        for (int i = 0; i < M; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            arr[from][to] = arr[to][from] = 1;
        }

        int X = sc.nextInt();
        int K = sc.nextInt();

        for (int k = 0; k < N + 1; k++) {
            for (int i = 0; i < N + 1; i++) {
                for (int j = 0; j < N + 1; j++) {
                    arr[i][j] = Math.min(arr[i][j], arr[i][k] + arr[k][j]); // k를 거쳐 가는게 더 작다면 초기화
                }
            }
        }

        int distance = arr[1][K] + arr[K][X];

        if (distance >= INF) {
            System.out.println(-1);
        } else {
            System.out.println(distance);
        }

    }
}

/*
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