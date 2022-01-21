package seunguk.thisiscodingtest.part03.Q17_competitivecontagion;

import java.util.*;

public class Main {
    static int N;
    static int K;
    static int S;
    static int X;
    static int Y;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static int[][] arr;

    static class Node{
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        K = sc.nextInt();
        arr = new int[N][N];
        List<Integer> viruses = new ArrayList<>(); // 바이러스 넣어줌
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                arr[i][j] = sc.nextInt();
                if (arr[i][j] != 0) {
                    viruses.add(arr[i][j]);
                }
            }
        }
        Set<Integer> set = new HashSet<>(viruses); // 바이러스 중복제거
        viruses = new ArrayList<>(set); // 중복제거 하고 다시 list로 바꿈
        Collections.sort(viruses); // 바이러스가 숫자 작은 수 부터 전염되기 때문에 오름차순 정렬
        S = sc.nextInt();
        X = sc.nextInt();
        Y = sc.nextInt();

        int time = 0;
        while (time != S) {
            for (Integer virus : viruses) { // 바이러스 한개씩 bfs실행
                bfs(virus);
            }
            time++;
            if (arr[X-1][Y-1] != 0) { // 목표 공간 바이러스로 채워지면 바로 종료
                break;
            }
        }

        System.out.println(arr[X-1][Y-1]);

    }
    private static void bfs(int virus) {
        Queue<Node> queue = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (arr[i][j] == virus) { // virus랑 같은 값 queue에 Node 만들어서 넣어줌
                    queue.add(new Node(i,j));
                }
            }
        }

        while (!queue.isEmpty()) {
            Node node = queue.remove();
            int x = node.x;
            int y = node.y;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx >= 0 && ny >= 0 && nx < N && ny < N) {
                    if (arr[nx][ny] == 0) {
                        arr[nx][ny] = arr[x][y];
                        // 상하좌우 한번만 이동하므로 queue에 안넣음
                    }
                }
            }
        }
    }
}
