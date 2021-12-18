package seohae.thiscodingtest.part02.chapter5.S02_mazeEscape;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/*
   N x M
   (1, 1) 에서 출발
   괴물이 존재하는 경우 (0), 아닌 경우 (1)
   1일 경우 움직일 수 있다.

   칸은 시작칸과 마지막칸 포함하여 계산
   시작칸과 마지막칸은 무조건 1
   시작부터 count = 1 설정 필요

   움직이면서 +1 필요
 */
public class Main {
    static int[][] graph;
    static int N;
    static int M;

    /**
     * 입력받기
     */
    static void input() {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt(); // 행
        M = sc.nextInt(); // 열

        // index 1 부터 시작
        graph = new int[N + 1][M + 1];

        /* 인접행렬 생성 */
        for (int i = 0; i < N; i++) {
            String s = sc.next();

            for (int j = 0; j < M; j++) {
                graph[i + 1][j + 1] = s.charAt(j) - '0';
            }
        }
    }

    public static void main(String[] args) {
        input();

        bfs(1, 1);

        /* 마지막 칸에 도착했을때 셋팅된 값이 정답이다. 이동하면서 +1을 하기 때문 */
        System.out.println(graph[N][M]);
    }

    /**
     * bfs
     * @param x
     * @param y
     */
    private static void bfs(int x, int y) {
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(x, y));

        /* 이동 상, 하, 좌, 우 */
        int[] dx = new int[]{-1, 1, 0, 0};
        int[] dy = new int[]{0, 0, -1, 1};

        while (!q.isEmpty()) {
            /* 탐색 대상 : node */
            Node node = q.poll();

            for (int i = 0; i < dx.length; i++) {
                int nx = node.getX() + dx[i];
                int ny = node.getY() + dy[i];

                /* 방문 가능 조건 */
                if (nx > 0 && nx < N + 1 && ny > 0 && ny < M + 1) {
                    /* 괴물이 없을때 */
                    if (graph[nx][ny] == 1) {
                        /* 방금 지나온 노드의 값에서 + 1 */
                        graph[nx][ny] = graph[node.getX()][node.getY()] + 1;

                        /* queue push */
                        q.offer(new Node(nx, ny));
                    }
                }
            }
        }
    }
}

/**
 * x, y 좌표를 위한 노드
 */
class Node {
    private int x;
    private int y;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }
}
