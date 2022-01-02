package seunguk.thisiscodingtest.part03.Q21_populationmovement;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// 예제5 통과못함
// 인구이동 구역이 두개 이상 나올 수 있고,
// border 배열처럼 체크하게 되면 옆에 벽이 존재해도 서로 이어져있다고 판단할 수 있다.
// 모든 정점에 대해서 bfs를 돌고, 한번 bfs가 끝나면 같은 구역이라 판단해야한다.

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
            border = new boolean[N][N]; // 인구 이동 가능한지 true, false
            int count = 0;
            int result = 0;
            boolean bool = false;
            bfs();

            // bfs 돌고 난 후 border 배열을 보고 true일 경우 result에 더해주고 count 증가
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (border[i][j]) {
                        bool = true;
                        result += arr[i][j];
                        count++;
                    }
                }
            }

            // 한번이라도 인구 이동 했다면 true이다
            if (!bool) {
                break;
            }

            int answer = result / count;


            // 값 갱신
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
                            // 현재값과 이동한값 border배열 true로 체크
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
