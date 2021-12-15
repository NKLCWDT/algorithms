package seunguk.thisiscodingtest.part02.chapter5.mazeescape;

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
        // bfs는 시작 지점에서 가까운 노드붜 차례대로 모든 노드를 탐색 하기 때문에
        // 한점에서 한점 까지에 최단 거리를 구할 수 있다.
        System.out.println(arr[N-1][M-1]); // 도착지점 출력
    }
    private static void bfs() {
        Queue<Maze> queue = new LinkedList<>();
        queue.add(new Maze(0, 0)); // 처음 시작 값

        while (!queue.isEmpty()) {
            Maze m = queue.remove();

            int x = m.x;
            int y = m.y;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx >= 0 && ny >= 0 && nx < N && ny < M) {
                    if (arr[nx][ny] == 1) { // 괴물이없고 처음 방문하는곳만 탐색한다.
                        arr[nx][ny] = arr[x][y] + 1; // 처음 방문한 곳에 이전 값에 +1을 한다.
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
