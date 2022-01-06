package annmj.thisiscodingtest.part03.Q16_laboratory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    // 1. 벽을 세운다. ( 백트래킹 )
    // 2. 바이러스를 퍼뜨린다.
    // 3. 안전 영역의 개수를 구한다.

    static int N, M;
    static int[][] A;
    static int[][] virusMap; // 바이러스 오염 시키고 난 뒤의 배열
    static int answer;
    static int NUM_OF_WALL = 3;
    static int NUM_OF_DIRECTION = 4;
    static List<Node> safeArea = new ArrayList<>();
    // 상 하 좌 우
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static void input() {
        FastReader scan = new FastReader();
        N = scan.nextInt(); // 세로
        M = scan.nextInt(); // 가로
        A = new int[N][M];
        virusMap = new int[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                A[i][j] = scan.nextInt();
            }
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

    private static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) {
        input();
        process();
    }

    private static void process() {
        // 안전영역 개수 구하기, 백트래킹을 위해
        int[][] copyMap = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                copyMap[i][j] = A[i][j];
                if (A[i][j] == 0) {
                    safeArea.add(new Node(i, j));
                }
            }
        }
        DFS(copyMap,  0,0);
        System.out.println(answer);
    }

    private static void DFS(int[][] map, int start, int count) {
        int[][] copyMap = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                copyMap[i][j] = map[i][j];
            }
        }

        // 벽이 다 세워졌다.
        if (count == NUM_OF_WALL) {
            initVirusMap(copyMap);
            // 바이러스를 퍼뜨리자.
            spreadVirus(new boolean[N][M]);
            answer = Math.max(answer, countSafeArea(virusMap));
            return;
        }

        for (int i = start; i < safeArea.size(); i++) {
            copyMap[safeArea.get(i).x][safeArea.get(i).y] = 1;
            DFS(copyMap, i+1, count + 1);
            copyMap[safeArea.get(i).x][safeArea.get(i).y] = 0;
        }
    }

    private static void initVirusMap(int[][] copyMap) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                virusMap[i][j] = copyMap[i][j];
            }
        }
    }

    private static int countSafeArea(int[][] map) {
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) {
                    count ++;
                }
            }
        }
        return count;
    }

    private static void spreadVirus(boolean[][] visit) {
        int nx = 0, ny = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (virusMap[i][j] == 2) {
                    for (int k = 0; k < NUM_OF_DIRECTION; k++) {
                        nx = i + dx[k];
                        ny = j + dy[k];
                        if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                        if (visit[nx][ny]) continue;
                        if (virusMap[nx][ny] == 1) continue;
                        virusMap[nx][ny] = 2;
                        visit[nx][ny] = true;
                        spreadVirus(visit);
                    }
                }
            }
        }
    }
}
