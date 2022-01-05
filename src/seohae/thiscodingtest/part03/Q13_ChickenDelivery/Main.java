package seohae.thiscodingtest.part03.Q13_ChickenDelivery;

import java.util.*;
import java.util.stream.IntStream;

/*
 0 : 빈칸
 1 : 집
 2 : 치킨

치킨거리 : 집과 가장 가까운 치킨집 사이의  거리
(r1, c1), (r2, c2) => |r1-r2| + |c1-c2|

도시 치킨 거리 : 모든 집의 치킨 거리의 합

**결과
도시의 치킨 거리가 가장 작게 되도록 치킨집 최대 M개 고르기

가장 작으려면 가까워야한다.
bfs / dfs 사용하여 탐색

 */
public class Main {
    static int N;
    static int M; // 고를 수 있는 치킨집 최대 개수
    static int[][] graph;
    static boolean[][] visited;
    static List<Integer> distanceList = new ArrayList<>();
    static List<City> chickenList = new ArrayList<>();
    static List<City> houseList = new ArrayList<>();

    static class City {
        int x;
        int y;

        public City(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) {
        input();

        /* bfs */
        for (City house : houseList) {
            if (!visited[house.x][house.y]) {
                bfs(house.x, house.y);
            }
        }

        /* result */
        Collections.sort(distanceList);
        int sum = IntStream.range(0, M).map(i -> distanceList.get(i)).sum();

        System.out.println(sum);
    }

    /*
        5 3
        0 0 1 0 0
        0 0 2 0 1
        0 1 2 0 0
        0 0 1 0 0
        0 0 0 0 2
     */
    private static void input() {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();

        graph = new int[N + 1][N + 1];
        visited = new boolean[N + 1][N + 1];

        for(int i = 1; i < N + 1; i++) {
            for(int j = 1; j < N + 1; j++) {
                int val = sc.nextInt();

                // 치킨 위치 저장
                if (val == 2) {
                    chickenList.add(new City(i, j));
                } else if (val == 1) { // 집 위치 저장
                    houseList.add(new City(i, j));
                }
            }
        }
    }

    private static void bfs(int x, int y) {
        /* 방문 */
        visited[x][y] = true;

        int distance = Integer.MAX_VALUE;

        for (City chicken : chickenList) {
            System.out.println(x + "," + y
                    + "," + chicken.x + "," + chicken.y);

            distance = Math.min(distance, getDistance(x, y, chicken.x, chicken.y));

            System.out.println("distance : " + distance);
        }

        distanceList.add(distance);
    }

    private static int getDistance(int x, int y, int nx, int ny) {
        return Math.abs(x - nx) + Math.abs(y - ny);
    }
}
