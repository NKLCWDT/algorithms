package seunguk.thisiscodingtest.part03.Q39_marsexploration;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// 간선에 가중치가 아니라 도착노드가 가지고 있는값이 가장 작으면 되기때문에 bfs로 풀수있다고 생각

public class Main {
    static int T;
    static int N;
    static int[][] arr;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static int[][] distance;
    static boolean[][] visited;
    static final int INF = Integer.MAX_VALUE;
    static class Node {
        int x;
        int y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        T = sc.nextInt();
        for (int i = 0; i < T; i++) {
            N = sc.nextInt();
            arr = new int[N][N];
            distance = new int[N][N]; // 값 누적할 배열
            visited = new boolean[N][N];

            for (int j = 0; j < N; j++) {
                Arrays.fill(distance[j], INF); // 누적배열 가장 큰 값으로 초기화 -> 경로 이동중 가장 작은값 저장하기 위해
            }

            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    arr[j][k] = sc.nextInt();
                }
            }
            bfs(0,0);
            System.out.println(distance[N-1][N-1]);
        }

    }
    private static void bfs(int a, int b) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(a,b));
        visited[a][b] = true;
        distance[a][b] = arr[a][b]; // 시작점은 arr값으로 초기화

        while (!queue.isEmpty()) {
            Node node = queue.remove();
            int x = node.x;
            int y = node.y;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx >= 0 && ny >= 0 && nx < N && ny < N) {
                    if (distance[x][y] < distance[nx][ny]) { // 값 누적배열이 더 작을 경우만 탐색
                        distance[nx][ny] = Math.min(distance[nx][ny], distance[x][y] + arr[nx][ny]); // 더 작은값으로 갱신
                        queue.add(new Node(nx,ny));
                    }
                }
            }
        }
    }
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