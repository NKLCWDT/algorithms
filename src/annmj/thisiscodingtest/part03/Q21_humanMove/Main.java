package annmj.thisiscodingtest.part03.Q21_humanMove;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        input();
        process();
    }

    static int N, L, R;
    static int[][] arr;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static boolean[][] visit;
    static int[][] union;
    static int unionCount = 0;
    static boolean unionExist = false; // 연합이 존재하면 true , 초기화에 유의
    static int count = 0; // 인구이동 횟수
    static ArrayList<ArrayList<Integer>> contries = new ArrayList<>(); // 연합별 나라들 저장
    static ArrayList<Integer> unions = new ArrayList<>(); // 하나의 연합에 속한 나라 저장
    public static void process() {
        //dfs
        while (true) {
            init();
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    unions = new ArrayList<>();
                    dfs(unionCount, i,j);
                    if(unionExist) {
                        if (unions.size() > 1) {
                            union[i][j] = unionCount;
                            unionCount++;
                            contries.add(unions);
                        }
                    }
                }
            }
            if(!unionExist) break;
            moveHuman();
        }


        System.out.println(count);

    }

    public static void init() {
        unionExist = false;
        unionCount = 0;
        for (int i = 0; i < N; i++) {
            Arrays.fill(union[i], -1);
            Arrays.fill(visit[i], false);
        }
    }

    public static void moveHuman() {
        // 인구이동
        for (int k = 0; k < contries.size(); k++) {
            int sum = 0;
            for (int human : contries.get(k)) {
                sum += human;
            }
            int averageHuman = sum / contries.get(k).size();
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if(union[i][j] == k) {
                        arr[i][j] = averageHuman;
                    }
                }
            }
        }
        contries.clear();
        count++; // 인구 이동 횟수 증가
    }

    public static void dfs(int count, int x, int y) {
        visit[x][y] = true;
        unions.add(arr[x][y]);

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx < 0 || ny < 0 || nx >= N || ny >= N ) continue;
            if(visit[nx][ny]) continue;

            int diff = Math.max(arr[nx][ny], arr[x][y]) - Math.min(arr[nx][ny], arr[x][y]);
            if(diff >= L && diff <= R) {
                unionExist = true;
                union[nx][ny] = count;
                dfs(count, nx, ny);
            }
        }
    }

    public static void input() {
        Scanner scanner = new Scanner(System.in);
        N = scanner.nextInt();
        L = scanner.nextInt();
        R = scanner.nextInt();

        arr = new int[N][N];
        visit = new boolean[N][N];
        union = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                arr[i][j] = scanner.nextInt();
            }
        }
    }
}

