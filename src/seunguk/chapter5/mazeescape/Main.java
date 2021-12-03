package seunguk.chapter5.mazeescape;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    static int N;
    static int M;
    static int[][] arr;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        sc.nextLine();

        arr = new int[N][M];

        for (int i = 0; i < N; i++) {
            String s = sc.nextLine();
            for (int j = 0; j < M; j++) {
                arr[i][j] = s.charAt(j) - '0';
            }
        }
        bfs();
    }
    private static void bfs() {
        Queue<Maze> queue = new LinkedList<>();
        queue.add(new Maze(0, 0));

        while (!queue.isEmpty()) {
            Maze m = queue.remove();

            int x = m.x;
            int y = m.y;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx >= 0 && ny >= 0 && nx < N && ny < M) {
                    if (arr[nx][ny] == 1) {
                        arr[nx][ny] = arr[x][y] + 1;
                        queue.add(new Maze(nx, ny));
                    }
                }
            }



        }
    }
}

class Maze {
    int x;
    int y;

    Maze(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

/*
5 6
101010
111111
000001
111111
111111
*/
