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

**참고
DFS + 백트래킹 사용

 */
public class Main2 {
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

        dfs(new ArrayList<>(), 0);

        /* result */
        System.out.println("distanceList size : " + distanceList);

        // sum 중에 최솟값 출력
        OptionalInt min = distanceList.stream()
                            .mapToInt(i -> i)
                            .min();

        System.out.println(min.getAsInt());
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

    private static void dfs(List<City> findList, int start) {
        // 탈출조건
        if (findList.size() == M) {
            getDistance(findList);
            return;
        }

        // 구현
        for (int i = start; i < chickenList.size(); i++) {
            findList.add(chickenList.get(i));

            dfs(findList, i + 1);

            findList.remove(findList.size() - 1);
        }
    }

    private static void getDistance(List<City> findList) {
        /* 각 집 별로 찾은 3개의 치킨과의 min 거리 */
        int sum = 0;

        for (City house : houseList) {
            int distance = Integer.MAX_VALUE;

            // 찾은 3개의 치킨 까지의 거리 중 최솟값 저장
            for (City findChicken : findList) {
                distance = Math.min(distance, Math.abs(findChicken.x - house.x) + Math.abs(findChicken.y - house.y));
            }

            // sum 구하기
            sum += distance;
        }

        distanceList.add(sum);
    }
}
