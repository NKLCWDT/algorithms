package annmj.thisiscodingtest.part03.Q13_chickenDelivery;

import java.util.*;

public class Main {
    static int MAX_VALUE = (int) 1e9;
    static int N;
    static int M; // 최대 치킨집 M개
    static int[][] arr; // 도시
    static int answer = MAX_VALUE; // 정답
    static boolean[] picked; // true : 개업 , false : 폐업
    static ArrayList<City> houses = new ArrayList<>();
    static ArrayList<City> chickenHouses = new ArrayList<>();

    public static void main(String[] args) {
        input();
        process();
    }

    private static void process() {
        // cities 와 houses 를 넣어둠.
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (arr[i][j] == 2) {
                    chickenHouses.add(new City(i, j));
                } else if (arr[i][j] == 1) {
                    houses.add(new City(i, j));
                }
            }
        }

        picked = new boolean[chickenHouses.size()];
        // M 개 고를때 까지 DFS
        DFS(0, 0);

        System.out.println(answer);
    }

    private static void DFS(int start, int count) {
        if (count == M) {
            int chickenDistance = 0;
            // 치킨 거리의 합을 구한다.
            // answer 를 최소값으로 갱신한다.

            for (int j = 0; j < houses.size(); j++) {
                int dist = getChickenDistance(houses.get(j));
                chickenDistance += dist;
            }

            answer = Math.min(answer, chickenDistance);
            return;
        }

        for (int i = start; i < chickenHouses.size(); i++) {
            // chickenHouse i 번째를 선택했다고 하고, 다른 치킨집을 고르자
            picked[i] = true;
            DFS(i + 1, count + 1);
            picked[i] = false;
            // 써먹었으면 다시 false
        }
    }

    /**
     *
     * @param city : 집
     * @return
     */
    private static int getChickenDistance(City city) {
        int chickenDistance = MAX_VALUE;

        for (int i = 0; i < chickenHouses.size(); i++) {
            if (picked[i]) {
                int dist = getDistance(city, chickenHouses.get(i));
                if (chickenDistance > dist) {
                    chickenDistance = dist;
                }
            }
        }
        return chickenDistance;
    }


    private static int getDistance(City city1, City city2) {
        return Math.abs(city1.r - city2.r) + Math.abs(city1.c - city2.c);
    }

    private static void input() {
        Scanner scanner = new Scanner(System.in);
        N = scanner.nextInt();
        M = scanner.nextInt();
        arr = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                arr[i][j] = scanner.nextInt();
            }
        }
    }

    static class City {
        int r;
        int c;

        public int getR() {
            return r;
        }

        public int getC() {
            return c;
        }

        public City(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}
