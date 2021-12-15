package seohae.thiscodingtest.part04.chapter4.S01_upDownLeftRight;

import java.util.Scanner;

/*
    N x N 배열
    L -> (0, -1)
    R -> (0, 1)
    U -> (-1, 0)
    D -> (1, 0)
 */
public class Main {
    static char[] types = {'L', 'R', 'U', 'D'};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt(); // N을 입력받기
        sc.nextLine();

        String[] plans = sc.nextLine().split(" "); // 이동 타입 입력받기

        /* L, R, U, D */
        int[] dx = {0, 0, -1, 1};
        int[] dy = {-1, 1, 0, 0};

        /* 시작점 */
        int x = 1;
        int y = 1;

        for (String target : plans) {
            int nx = 0;
            int ny = 0;

            for (int j = 0; j < dx.length; j++) {
                /* target 과 동일한 인덱스를 찾음 */
                if (target.equals(String.valueOf(types[j]))) {
                    nx = x + dx[j];
                    ny = y + dy[j];
                }

                /* 정상 지점일 경우 저장 */
                if (nx > 0 && ny > 0 && nx < n && ny < n) {
                    x = nx;
                    y = ny;
                }
            }

        }

        System.out.println(x + " " + y);
    }

    /**
     * 처음 풀이
     */
    void before() {
        Scanner sc = new Scanner(System.in);

        // N을 입력받기
        int n = sc.nextInt();
        sc.nextLine(); // 버퍼 비우기
        String[] plans = sc.nextLine().split(" ");
        int x = 1, y = 1;

        /* 구현 */
        for (int i = 0; i < plans.length; i++) {
            char plan = plans[i].charAt(0);

            if (plan == 'L') {
                if (y - 1 > 1) {
                    y = y - 1;
                }
            } else if (plan == 'R') {
                if (y + 1 < n) {
                    y = y + 1;
                }
            } else if (plan == 'U') {
                if (x - 1 > 1) {
                    x = x - 1;
                }
            } else if (plan == 'D') {
                if (x + 1 < n) {
                    x = x + 1;
                }
            }
        }

        System.out.println(x + " " + y);
    }
}
