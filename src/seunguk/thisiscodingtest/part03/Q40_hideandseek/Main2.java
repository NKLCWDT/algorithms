package seunguk.thisiscodingtest.part03.Q40_hideandseek;

import java.util.*;

// bfs 인접행렬 방식 실패

public class Main2 {
    static int N;
    static int M;
    static int[][] arr;
    static boolean[][] visited;
    static int[][] distance;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

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
        N = sc.nextInt();
        M = sc.nextInt();
        arr = new int[N+1][N+1];
        visited = new boolean[N+1][N+1];
        distance = new int[N+1][N+1];

        for (int i = 0; i < M; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            arr[a][b] = arr[b][a] = 1;
        }
        bfs();
        int maxx = 0;
        Queue<Node> queue = new LinkedList<>();
        for (int i = 0; i < N + 1; i++) {
            for (int j = 0; j < N + 1; j++) {
                maxx = Math.max(maxx, distance[i][j]);
            }
        }

        for (int i = 0; i < N + 1; i++) {
            for (int j = 0; j < N + 1; j++) {
                if (maxx == distance[i][j]) {
                    queue.add(new Node(i,j));
                }
            }
        }
        int leng = queue.size();
        Node node = queue.remove();
        System.out.println(node.x + " " + maxx + " " + leng);

    }
    private static void bfs() {
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(1,1));
        visited[1][1] = true;

        while (!queue.isEmpty()) {
            Node node = queue.remove();
            int x = node.x;
            int y = node.y;
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (0 < nx && 0 < ny && nx <= N && ny <= N) {
                    if (arr[nx][ny] == 1 && !visited[nx][ny]) {
                        visited[nx][ny] = true;
                        distance[nx][ny] = distance[x][y] + 1;
                        queue.add(new Node(nx, ny));
                    }
                }
            }
        }

    }
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
