package seunguk.thisiscodingtest.part03.Q13_chickendelivery;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// 재귀 + bfs로 못풀음

public class Main {
    static int N;
    static int M;
    static int[][] arr;
    static int[][] distance;
    static boolean[][] visited;
    static ArrayList<Node> store = new ArrayList<>();
    static boolean[] chickenCheck;
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
        M = sc.nextInt();
        arr = new int[N][N];
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                arr[i][j] = sc.nextInt();
            }
        }

        int result = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (arr[i][j] == 2) {
                    store.add(new Node(i,j));
                }
//                if (arr[i][j] == 1) {
//                    result += bfs(i,j);
//                }
            }
        }
        chickenCheck = new boolean[store.size()];
    }

    private static void recursive(int count, int index) {
        if (count == M) {
            return;
        }

        for (int i = index; i < store.size(); i++) {
            chickenCheck[i] = true;
            recursive(count+1, index + 1);
            chickenCheck[i] = false;
        }


    }

    private static int bfs(int a, int b) {
        distance = new int[N][N];
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(a,b));

        while (!queue.isEmpty()) {
            Node node = queue.remove();
            int x = node.x;
            int y = node.y;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (0 <= nx && 0 <= ny && N > nx && N > ny) {
                    if (!visited[nx][ny]) {
                        distance[nx][ny] = distance[x][y] + 1;
                        queue.add(new Node(nx,ny));
                        if (arr[nx][ny] == 2) {
//                            if (chickenCheck[])
                            return distance[nx][ny];
                        }
                    }
                }
            }
        }
        return 0;
    }
}
