package annmj.chapter5.freezedrinks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
    static FastReader scan = new FastReader();

    static int N, M;
    static int[][] arr;
    static boolean[][] visit;
    static int[][] dir = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    static Queue<Node> queue = new LinkedList<>();

    static void input() {
        N = scan.nextInt();
        M = scan.nextInt();
        arr = new int[N][M];
        visit = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                arr[i][j] = scan.nextInt();
            }
        }
    }
    static void bfs(Node start) {
        queue = new LinkedList<>();
        queue.add(start);
        visit[start.x][start.y] = true;

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nx = node.x + dir[i][0];
                int ny = node.y + dir[i][1];
                if(nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                if(visit[nx][ny] || arr[nx][ny] == 1) continue;
                queue.add(new Node(nx, ny));
                visit[nx][ny] = true;
            }
        }
    }

    static void pro() {
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(arr[i][j] == 1) continue;
                if(visit[i][j]) continue;
                count++;
                bfs(new Node(i,j));
            }
        }
        System.out.println(count);
    }

    public static void main(String[] args) {
        input();
        pro();
    }

    static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        Integer nextInt() {
            return Integer.parseInt(next());
        }
    }
}

