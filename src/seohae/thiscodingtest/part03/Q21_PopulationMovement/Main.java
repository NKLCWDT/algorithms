package seohae.thiscodingtest.part03.Q21_PopulationMovement;

import java.util.*;

/*
https://www.acmicpc.net/problem/16234
 */
public class Main {
    static int[][] graph;
    static boolean[][] visited;
    static int N = 0;
    static int L = 0;
    static int R = 0;
    static int sum = 0; // 총 이동한 인구수
    // static int result = 0; // 인구 이동 발생 일수
    static boolean isMoved = false;

    static List<Node> list = new ArrayList<>();

    static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) {
        input();

        int result = 0; // 인구 이동 발생 일수

        /** while 문 안에 하루안의 과정 */
        while(true) {
            /* 하루가 지났음 - reset */
            boolean isResult = false;
            visited = new boolean[N + 1][N + 1];

            for (int i = 1; i < N + 1; i++) {
                for (int j = 1; j < N + 1; j++) {
                    if (!visited[i][j]) {
                        // list.clear();
                        bfs(i, j);

                        /* 인구 이동 시작 */
                        if (isMoved) {
                            // (연합의 인구수) / (연합을 이루고 있는 칸의 개수)
                            int newValue = sum / list.size();
                            for (Node node : list) {
                                // avg 로 change
                                graph[node.x][node.y] = newValue;
                            }

                            isMoved = false;
                            isResult = true; // 인구 이동이 한번이라도 발생
                        }

                        list.clear();
                    }
                }
            }

            if (!isResult) { // 더이상의 인구 이동이 발생하지 않았으므로 종료
                break;
            } else {
                result++;
            }
        }

        System.out.println(result);
    }

    /*
        2 20 50
        50 30
        20 40

        3 5 10
        10 15 20
        20 30 25
        40 22 10

        (X)
        4 10 50
        10 100 20 90
        80 100 60 70
        70 20 30 40
        50 20 100 10
     */
    private static void input() {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        L = sc.nextInt();
        R = sc.nextInt();

        graph = new int[N + 1][N + 1];
        visited = new boolean[N + 1][N + 1];

        for (int i = 1; i < N + 1; i++) {
            for (int j = 1; j < N + 1; j++) {
                graph[i][j] = sc.nextInt();
            }
        }

    }

    /**
     * sum 을 구한다.
     * @param x
     * @param y
     */
    static void bfs(int x, int y) {
        /* 방문 */
        visited[x][y] = true;

        Queue<Node> queue = new LinkedList<Node>();

        queue.offer(new Node(x, y));
        list.add(new Node(x, y));

        sum = graph[x][y]; // 초기화
        while (!queue.isEmpty()) {
            Node cur = queue.poll();

            // 우, 하, 좌, 상
            int[] dx = new int[]{0, 1, 0, -1};
            int[] dy = new int[]{1, 0, -1, 0};

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (nx > 0 && ny > 0 && nx < N + 1 && ny < N + 1) {
                    if (!visited[nx][ny]) {
                        // 두 나라의 인구 차이가 L명 이상 R 명 이하일때 국경선을 연다.
                        if (Math.abs(graph[cur.x][cur.y] - graph[nx][ny]) >= L
                                && Math.abs(graph[cur.x][cur.y] - graph[nx][ny]) <= R) {
                            // 인구 이동 시작
                            queue.offer(new Node(nx, ny));

                            sum += graph[nx][ny]; // 이동한 총 인구수 계산
                            list.add(new Node(nx, ny)); // 이동한 노드 add

                            isMoved = true; // 인구 이동 발생 여부
                            visited[nx][ny] = true; // 방문 처리
                        }
                    }
                }
            }
        }
    }
}
