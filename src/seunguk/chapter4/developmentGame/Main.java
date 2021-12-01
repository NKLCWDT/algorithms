package seunguk.chapter4.developmentGame;

import java.util.Scanner;

public class Main {
    //상우하좌
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    static int N;
    static int M;
    static int x;
    static int y;
    static int d;
    static int[][] arr;
    static boolean[][] visited;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        x = sc.nextInt();
        y = sc.nextInt();
        d = sc.nextInt(); // 0 : 북, 1 : 동, 2 : 남, 3 : 서

        int nx;
        int ny;
        int count = 1;
        arr = new int[N][M];
        visited = new boolean[N][M];
        visited[x][y] = true;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                arr[i][j] = sc.nextInt(); // 0 : 육지, 1 : 바다
            }
        }

        int turnCount = 0;
        while (true) {
            turn();
            nx = x + dx[d];
            ny = y + dy[d];
            if (!visited[nx][ny] && arr[nx][ny] == 0) {
                visited[nx][ny] = true;
                x = nx;
                y = ny;
                count++;
                turnCount = 0;
                continue;
            } else {
                turnCount++;
            }
            if (turnCount == 4) {
                nx = x - dx[d];
                ny = y - dy[d];
                if (arr[nx][ny] == 0) {
                    x = nx;
                    y = ny;
                } else {
                    break;
                }
                turnCount = 0;
            }
        }
        System.out.println(count);


    }
    private static void turn() {
        d -= 1;
        if (d < 0) {
            d = 3;
        }
    }
}
