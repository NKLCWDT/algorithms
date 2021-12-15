package seohae.thiscodingtest.part02.chapter4.S04_gameDevelop;

/*
   N x M 크기
   현재 위치 기준으로 왼쪽 방향부터 차례대로 갈 곳을 정한다.
   캐릭터의 바로 왼쪽 방향에 가보지 않은 칸이 존재한다면 왼쪽 방향으로 회전 -> 다음 왼쪽으로 한칸 전진
   왼쪽 방향에 모두 방문한 칸이라면, 왼쪽 방향으로 회전만 수행하고 1단계로 돌아간다.
   네 방향 모두 바다 이거나 방문한 칸일 경우, 바라보는 방향을 유지한채로 한칸 뒤로 가고 1단계로 돌아간다.
   뒤쪽 방향이 바다인 칸이라 뒤로 갈 수 없을때도 움직임을 멈춘다.

   매뉴얼에 따라 캐릭터를 이동시킨 뒤에, 캐릭터가 방문한 칸의 수를 출력

0 (북)
1 (동)
2 (남)
3 (서)

행렬에서의 0 (육지), 1 (바다)

 */

import java.util.Scanner;

/**
 * 답안 본 문제 (분석중..)
 */
public class Main {
    // 변수 선언
    public static int n, m, x, y, direction;

    // 방문한 위치를 저장하기 위한 맵을 생성하여 0으로 초기화
    public static int[][] d = new int[50][50];

    // 전체 맵 정보
    public static int[][] arr = new int [50][50];

    // 북, 동, 남, 서 방향 정의
    public static int dx[] = {-1, 0, 1, 0};
    public static int dy[] = {0, 1, 0, -1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt(); // N (행)
        m = sc.nextInt(); // M (열)

        // (x,y) 좌표에 direction(0,1,2,3:북/동/남/서)를 바라보고 서있다.
        x = sc.nextInt();
        y = sc.nextInt();
        direction = sc.nextInt();

        /* 현재 좌표 방문 처리 */
        d[x][y] = 1;

        /* 행렬 입력 */
        for (int i = 0; i < n; i++) { // n (행)
            for (int j = 0; j < m; j++) { // m (열)
                arr[i][j] = sc.nextInt();
            }
        }

        // 시뮬레이션 시작
        int cnt = 1;
        int turn_time = 0;

        while (true) {
            // 왼쪽으로 회전
            turn_left();
            int nx = x + dx[direction];
            int ny = y + dy[direction];

            // 회전한 이후 정면에 가보지 않은 칸이 존재하는 경우 이동
            if (d[nx][ny] == 0 && arr[nx][ny] == 0) {
                d[nx][ny] = 1;

                x = nx;
                y = ny;

                cnt += 1;
                turn_time = 0;

                continue;
            } else  { // 회전한 이후 정면에 가보지 않은 칸이 없거나 바다인 경우
                turn_time += 1;
            }

            // 네 방향 모두 갈 수 없는 경우
            if (turn_time == 4) {
                nx = x - dx[direction];
                ny = y - dy[direction];

                // 뒤로 갈 수 있다면 이동하기
                if (arr[nx][ny] == 0) {
                    x = nx;
                    y = ny;
                } else { // 뒤가 바다로 막혀있는 경우
                    break;
                }

                turn_time = 0;
            }
        }

        System.out.println(cnt);
    }

    /**
     * 왼쪽으로 회전
     */
    public static void turn_left() {
        direction = direction - 1;

        if (direction == -1) {
            direction = 3;
        }
    }
}
