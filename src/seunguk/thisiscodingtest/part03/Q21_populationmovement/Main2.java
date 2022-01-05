package seunguk.thisiscodingtest.part03.Q21_populationmovement;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main2 {
    static int N;
    static int L;
    static int R;
    static int[][] arr;
    static boolean[][] check;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

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
        L = sc.nextInt();
        R = sc.nextInt();
        arr = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                arr[i][j] = sc.nextInt();
            }
        }
        int time = 0;


        // 인구이동
        while (true) {
            boolean bool = false;
            boolean flag = false;
            check = new boolean[N][N];

            // arr 배열 모든 정점에 대하여 미방문한곳 전부 bfs
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!check[i][j]) {
                        bool = bfs(i, j);

                        // 한번이라도 true이면 인구이동이 가능하다
                        if (bool) {
                            flag = true;
                        }
                    }
                }
            }
            // 전체 한번도 true가 없었으므로 인구이동이 없었다고 판단한다.
            if (!flag) {
                break;
            }
            time++;
        }
        System.out.println(time);

    }

    private static boolean bfs(int a, int b) {
        int amount = arr[a][b]; // 인구이동을 위한 합
        int answer = 0; // 인구이동 총 계산
        boolean bool = false; // 인구이동 가능한지 체크
        check[a][b] = true;

        Queue<Node> queue = new LinkedList<>(); // bfs를 위한 queue
        Queue<Node> result = new LinkedList<>(); // 인구이동 영역 구분을 위한 queue
        queue.add(new Node(a, b));
        result.add(new Node(a, b));

        while (!queue.isEmpty()) {
            Node node = queue.remove();
            int x = node.x;
            int y = node.y;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx >= 0 && ny >= 0 && nx < N && ny < N) {
                    if (!check[nx][ny]) { // 미방문 정점이고
                        if (Math.abs(arr[nx][ny] - arr[x][y]) <= R && Math.abs(arr[nx][ny] - arr[x][y]) >= L) {
                            amount += arr[nx][ny];
                            check[nx][ny] = true; // 방문체크
                            bool = true;
                            result.add(new Node(nx, ny));
                            queue.add(new Node(nx, ny));
                        }
                    }
                }
            }
        }
        answer = amount / result.size();

        while (!result.isEmpty()) { // queue에서 값을 꺼내어 arr값 변경, (여기서 while문 대신 for문을 쓰면 이상해진다.)
            Node node = result.remove();
            int x = node.x;
            int y = node.y;
            arr[x][y] = answer;
        }
        return bool;
    }

}
