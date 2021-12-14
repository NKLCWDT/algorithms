package seunguk.chapter5.freezedrinks;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    static int N;
    static int M;
    static int[][] arr;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        sc.nextLine();
        arr = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            String s = sc.nextLine();
            for (int j = 0; j < M; j++) {
                arr[i][j] = s.charAt(j) - '0';
            }
        }

        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visited[i][j] && arr[i][j] == 0) { // 처음 방문하고 구멍이 뚫려 있는 곳만 방문
                    bfs(i, j);
                    count++; // bfs가 끝나면 인접한 부분 모두 탐색으로 1증가
                }
            }
        }
        System.out.println(count);
    }

    private static void bfs(int a, int b) {
        Queue<Freeze> queue = new LinkedList<>();
        queue.add(new Freeze(a, b)); // 큐에 처음 시작값 넣어준다
        visited[a][b] = true;

        while (!queue.isEmpty()) {
            Freeze f = queue.remove();

            int x = f.x;
            int y = f.y;

            for (int i = 0; i < 4; i++) { // 상하좌우 탐색
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx >= 0 && ny >= 0 && nx < N && ny < M) {
                    if (arr[nx][ny] == 0 && !visited[nx][ny]) { // 처음 방문하고 구멍이 뚫려 있는 곳만 방문
                        visited[nx][ny] = true;
                        queue.add(new Freeze(nx, ny));
                    }

                }
            }

        }
    }
}

class Freeze {
    int x;
    int y;

    Freeze(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

/*
15 14
00000111100000
11111101111110
11011101101110
11011101100000
11011111111111
11011111111100
11000000011111
01111111111111
00000000011111
01111111111000
00011111111000
00000001111000
11111111110011
11100011111111
11100011111111
*/
