package seunguk.thisiscodingtest.part03.Q16_laboratory;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    static int N;
    static int M;
    static int[][] arr;
    static boolean[][] visited;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static int maxx = 0;

    // 0 : 빈칸
    // 1 : 벽
    // 2 : 바이러스
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
        arr = new int[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                arr[i][j] = sc.nextInt();
            }
        }
        recursive(0);
        System.out.println(maxx);
    }
    // 벽 세워주기
    private static void recursive(int index) {
        if (index == 3) { // 벽 3개 추가했으면 실행
            maxx = Math.max(maxx, process()); // 안전지역중 가장 큰 값 저장
            return;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (arr[i][j] == 0) { // 0 : 빈칸일 경우 벽 세워주고 재귀 돌고 다시 벽 지워줌
                    arr[i][j] = 1;
                    recursive(index + 1);
                    arr[i][j] = 0;
                }
            }
        }
    }

    private static int process() {
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visited[i][j]) {
                    if (arr[i][j] == 2) { // 아직 방문하지 않은 바이러스일 경우 bfs탐색
                        bfs(i,j);
                    }
                }
            }
        }

        // 모든 바이러스에 대해 탐색후 안전지역 카운트
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (arr[i][j] == 0 && !visited[i][j]) { // 빈칸이고 방문하지 않은곳은 안전지역으로 판단
                    count++;
                }
            }
        }
        return count;
    }

    // bfs 탐색
    private static void bfs(int a, int b) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(a, b));
        visited[a][b] = true;

        while (!queue.isEmpty()) {
            Node node = queue.remove();
            int x = node.x;
            int y = node.y;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx >= 0 && ny >= 0 && nx < N && ny < M) {
                    if (arr[nx][ny] == 0 && !visited[nx][ny]) { // 빈칸이고 아직 방문하지 않은곳만 방문
                        visited[nx][ny] = true; // 방문체크
                        queue.add(new Node(nx, ny));
                    }
                }
            }
        }
    }
}
