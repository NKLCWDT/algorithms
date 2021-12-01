package annmj.chapter4.gamedevelopment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    static FastReader scan = new FastReader();

    static int N, M;
    static int x, y, direction;
    static int[][] graph;
    static boolean[][] visit;
    static int count;
    static int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    static void rotate() {
        // 캐릭터 회전
        direction = (direction - 1) % 4;

    }

    static void input() {
        N = scan.nextInt();
        M = scan.nextInt();
        x = scan.nextInt();
        y = scan.nextInt();
        direction = scan.nextInt();
        graph = new int[N][M];
        visit = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                graph[i][j] = scan.nextInt();
            }
        }

    }

    static void pro() {
        count = 1;
        visit[x][y] = true; // 처음 좌표
        int turnCount = 0; // 주의 : 이 값이 언제 초기화 되는가 ? 1. 이동 했을 때 2. 뒤로 갔을 때
        while (true) {
            rotate();

            int nx, ny;
            nx = x + directions[direction][0];
            ny = y + directions[direction][1];

            if(visit[nx][ny] || graph[nx][ny] == 1) {
                turnCount++;
            }

            if (!visit[nx][ny] && graph[nx][ny] == 0) {
                // 이동 처리
                turnCount = 0;
                x = nx;
                y = ny;
                visit[x][y] = true;
                count++;
                continue;
            }

            if (turnCount == 4) {
                // 뒤로 간다.
                x = x - directions[direction][0];
                y = y - directions[direction][1];
                turnCount = 0;
                if (graph[x][y] == 1) break;
            }


        }
        System.out.println(count);
    }

    public static void main(String[] args) {
        input();
        pro();
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
