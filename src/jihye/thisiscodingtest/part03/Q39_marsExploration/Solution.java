package jihye.thisiscodingtest.part03.Q39_marsExploration;

import java.util.*;


class Coordinate implements Comparable<Coordinate>{
    int x;
    int y;
    int dist;

    Coordinate(int x, int y, int dist) {
        this.x = x;
        this.y = y;
        this.dist = dist;
    }

    @Override
    public int compareTo(Coordinate other) {
        if (this.dist < other.dist) {
            return -1;
        }
        return 1;
    }
}

public class Solution {

    static int dx[] = {-1, 1, 0, 0};
    static int dy[] = {0, 0, -1, 1};
    static int N;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        int[][] map;

        for (int i = 0; i < T; i++) {
            N = sc.nextInt();
            map = new int[N][N];

            //각 map을 입력 받는다.
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    map[j][k] = sc.nextInt();
                }
            }

            System.out.println(findRoute(map));
        }
    }

    public static int findRoute(int[][] map) {
        int x = 0;
        int y = 0;
        int dist = 0;
        boolean[][] visited = new boolean[N][N];
        int[][] distance = new int[N][N];
        PriorityQueue<Coordinate> pq = new PriorityQueue<>();;
        Coordinate coordinate = new Coordinate(0, 0, map[0][0]);


        //
        for (int i = 0; i < N; i++) {
            Arrays.fill(distance[i], Integer.MAX_VALUE);
        }

        pq.add(coordinate);
        distance[0][0] = map[0][0];

        //queue가 빌때 까지
        while (!pq.isEmpty()) {

            //queue에서 꺼낸다.
            Coordinate curr = pq.poll();
            x = curr.x;
            y = curr.y;
            dist = curr.dist;

            //이미 처리 된적 있으면 무
            if (distance[x][y] < dist) continue;

            for (int i = 0; i < 4; i++) {//인접한 노드
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue; //범위를 넘지 않느다면
                int cost = dist + map[nx][ny];

                if (cost < distance[nx][ny]) {//가중치가 더 작다면
                    distance[nx][ny] = cost;//distance갱신
                    pq.add(new Coordinate(nx,ny,cost));
                }

            }
        }
        return distance[N - 1][N - 1];
    }
}
