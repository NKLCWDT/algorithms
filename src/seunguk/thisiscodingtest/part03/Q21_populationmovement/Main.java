package seunguk.thisiscodingtest.part03.Q21_populationmovement;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// 예제5 통과못함
// 구역이 두개 이상 나올 수 있고, 모든 곳에 대하여 bfs를 돌고
// 한번 bfs가 끝나면 같은 구역이라 판단해야한다.

public class Main {
    static int N;
    static int L;
    static int R;
    static int[][] arr;
    static boolean[][] check;
    static boolean[][] border;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    static class Node{
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
        L = sc.nextInt();
        R = sc.nextInt();
        arr = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                arr[i][j] = sc.nextInt();
            }
        }
        int time = 0;
        while (true) {
            check = new boolean[N][N];
            border = new boolean[N][N];
            int count = 0;
            int result = 0;
            boolean bool = false;
            bfs();



            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (border[i][j]) {
                        bool = true;
                        result += arr[i][j];
                        count++;
                    }
                }
            }

            if (!bool) {
                break;
            }

            int answer = result / count;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (border[i][j]) {
                        arr[i][j] = answer;
                    }
                }
            }


            time++;
        }
        System.out.println(time);

    }
    private static void bfs() {
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(0,0));

        while (!queue.isEmpty()) {
            Node node = queue.remove();
            int x = node.x;
            int y = node.y;
            check[x][y] = true;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx >= 0 && ny >= 0 && nx < N && ny < N) {
                    if (!check[nx][ny]) {
                        if (Math.abs(arr[nx][ny] - arr[x][y]) <= R && Math.abs(arr[nx][ny] - arr[x][y]) >= L) {
                            border[x][y] = true;
                            border[nx][ny] = true;
                        }

                        queue.add(new Node(nx,ny));
                    }

                }
            }
        }
    }

}
