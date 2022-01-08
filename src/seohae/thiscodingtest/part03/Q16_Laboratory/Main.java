package seohae.thiscodingtest.part03.Q16_Laboratory;

import java.util.*;

public class Main {
    static int N;
    static int M;
    static int[][] graph;
    static boolean[][] visited;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static List<Location> virusList = new ArrayList<>();
    // static List<Location> zeroList = new ArrayList<>();

    static final int WALL_CNT = 3;

    static int resultZeroSum = Integer.MIN_VALUE;

    static class Location {
        int x;
        int y;

        public Location(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) {
        input();

        dfs(0, 0);

        System.out.println(resultZeroSum);
    }

    private static void input() {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();

        graph = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                int val = sc.nextInt();
                graph[i][j] = val;

                if (val == 2) {
                    virusList.add(new Location(i, j));
                }
//                else if (val == 1) {
//                    zeroList.add(new Location(i, j));
//                }
            }
        }
    }

    private static void dfs(int start, int r) {
        // 탈출조건
        if (r == WALL_CNT) {
            /* 배열 깊은 복사 */
            // int[][] temp = graph.clone();
            int[][] temp = Arrays.stream(graph)
                                .map(int[]::clone)
                                .toArray(int[][]::new);

            virusAddBfs(temp);
            return;
        }


        /**
         * Point1. 빈곳(0)인 곳에 벽을 설치해본다.
         */
        /* 빈 곳만큼 반복 수행 */
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (graph[i][j] == 0) { // 빈칸인 경우
                    // 벽 설치
                    graph[i][j] = 1;

                    // 호출
                    dfs(start + 1, r + 1);

                    // 벽 제거
                    graph[i][j] = 0;
                }
            }
        }

//        for (int i = start; i < zeroList.size(); i++) {
//            Location location = zeroList.get(i);
//
//            // 벽 설치
//            graph[location.x][location.y] = 1;
//
//            // 호출
//            dfs(start + 1, r + 1);
//
//            // 벽 제거
//            graph[location.x][location.y] = 0;
//        }
    }

    private static void virusAddBfs(int[][] temp) {
        Queue<Location> queue = new LinkedList<>();

        for (Location location : virusList) {
            queue.add(new Location(location.x, location.y));
        }

        while(!queue.isEmpty()) {
            Location cur = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (nx >= 0 && ny >= 0 && nx < N && ny < M) {
                    if (temp[nx][ny] == 0) { // 빈 곳일때
                        temp[nx][ny] = 2; // 바이러스 전파
                        queue.add(new Location(nx, ny));
                    }
                }
            }
        }


        /**
         * Point2. 결과값은 결국, virusAddBfs 메서드의 연산이 끝난 후,
         * temp 배열의 0의 총 개수를 구하여 최종 결과변수에 최댓값을 저장한다.
         */
        /* 0 의 개수 구하기 */
        int count = (int) Arrays.stream(temp)
                                .flatMapToInt(Arrays::stream)
                                .filter(value -> value == 0)
                                .count();


        resultZeroSum = Math.max(resultZeroSum, count);
    }
}
