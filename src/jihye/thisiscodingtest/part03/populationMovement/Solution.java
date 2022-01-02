package jihye.thisiscodingtest.part03.populationMovement;

import java.util.*;

public class Solution {
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static int N;
    static int R;
    static int graph[][] = new int[N][N];
    static int[][] union = new int[N][N];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        int L = sc.nextInt();
        R = sc.nextInt();


        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                graph[i][j] = sc.nextInt();
            }
        }

        int totalCount = 0;
        while (true) {
            int union[][] = new int[N][N];
            for (int i = 0; i < N; i++) {
                Arrays.fill(union[N], -1);
            }


            int index = 0;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (union[i][j] == -1) {
                        process(i, j, index);
                        index += 1;
                    }
                    if (index == N * N) {
                        break;
                    }
                    totalCount += 1;
                }
            }System.out.println(totalCount);
        }


    }

    public static int process(int x, int y, int index) {
        ArrayList<int[]> united = new ArrayList<>();
        int[] info = {x, y};
        united.add(info);

        Queue queue = new LinkedList();
        queue.add(info);
        union[x][y] = index;
        int summary = graph[x][y];
        int count = 1;

        while (!queue.isEmpty()) {
            int[] direction = (int[]) queue.poll();
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (0 <= nx && nx < N && 0 <= ny && ny < N && union[nx][ny] == -1) {
                    int calc = Math.abs(graph[nx][ny] - graph[x][y]);
                    if (1 <= calc && calc <= R){
                        info = new int[2];
                        info[0] = nx;
                        info[1] = ny;
                        queue.add(info);

                        union[nx][ny] = index;
                        summary += graph[nx][ny];
                        count++;
                        united.add(info);
                    }
                }
            }
        }
        for(int i=0; i< N;i++){
            for(int j=0; j < N; j++){
                graph[i][j] = summary/count;
            }
        }
        return count;

    }
}
