package annmj.thisiscodingtest.part03.Q17_competitivecontagion.solution1;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int[][] graph;
    static boolean[][] visit;
    static int N, K, S, X, Y;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static class Node implements Comparable<Node> {
        private int x;
        private int y;
        private int virusNumber;
        private int time; // 전파된 시간

        public int getTime() {
            return time;
        }

        public Node(int x, int y, int virusNumber, int time) {
            this.x = x;
            this.y = y;
            this.virusNumber = virusNumber;
            this.time = time;
        }

        public int getVirusNumber() {
            return virusNumber;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        @Override
        public int compareTo(Node o) {
            if (this.time != o.time) {
                return this.time - o.time;
            }
            return this.virusNumber - o.virusNumber;
        }
    }

    public static void main(String[] args) {
        input();
        process();
    }

    private static void process() {
        int answer = 0;
        PriorityQueue<Node> q = new PriorityQueue<>();
        initVirus(q);

        while (!q.isEmpty()) {
            Node virus = q.poll();

            //TODO 바이러스 전파 시키기
            for (int i = 0; i < 4; i++) {
                int nx = virus.getX() + dx[i];
                int ny = virus.getY() + dy[i];
                if (isNotInGraph(nx, ny)) continue;
                if (visit[nx][ny]) continue;
                if (isVirus(graph[nx][ny])) continue;
                if (virus.getTime() == S) continue;
                q.offer(new Node(nx, ny, virus.getVirusNumber(), virus.getTime() + 1));
                visit[nx][ny] = true;
                graph[nx][ny] = virus.getVirusNumber();
            }
        }
        System.out.println(graph[X - 1][Y - 1]);
    }

    private static boolean isNotInGraph(int nx, int ny) {
        return nx < 0 || ny < 0 || nx >= N || ny >= N;
    }

    private static void initVirus(PriorityQueue<Node> q) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (visit[i][j]) continue;
                if (isVirus(graph[i][j])) {
                    q.offer(new Node(i, j, graph[i][j], 0));
                    visit[i][j] = true;
                }
            }
        }
    }

    private static boolean isVirus(int virusNumber) {
        return virusNumber > 0;
    }

    private static void input() {
        FastReader scan = new FastReader();
        N = scan.nextInt();
        K = scan.nextInt();
        graph = new int[N][N];
        visit = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                graph[i][j] = scan.nextInt();
            }
        }
        S = scan.nextInt();
        X = scan.nextInt();
        Y = scan.nextInt();
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

        String nextLine() {
            String str = "";

            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;

        }
    }
}

