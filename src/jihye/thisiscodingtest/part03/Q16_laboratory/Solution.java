package jihye.thisiscodingtest.part03.Q16_laboratory;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/*
인체에 치명적인 바이러스가 유출되 막기 위해 연구소에 벽을 세우려한다.
벽을 3개이고 꼭 3개를 세워야함
벽을 세우지 않으면 바이러스가 퍼져나감
이때 안전구역의 최대갯수를 구한다.

0 - 빈칸
1 - 벽
2 - 바이러스

처음 연구소의 모습
2 0 0 0 1 1 0
0 0 1 0 1 2 0
0 1 1 0 1 0 0
0 1 0 0 0 0 0
0 0 0 0 0 1 1
0 1 0 0 0 0 0
0 1 0 0 0 0 0

2행 1열, 1행 2열, 4행 6열에 벽을 세운다면 지도의 모양은 아래와 같아지게 된다.
2 1 0 0 1 1 0
1 0 1 0 1 2 0
0 1 1 0 1 0 0
0 1 0 0 0 1 0
0 0 0 0 0 1 1
0 1 0 0 0 0 0
0 1 0 0 0 0 0

바이러스가 퍼진 뒤의 모습은 아래와 같아진다.

2 1 0 0 1 1 2
1 0 1 0 1 2 2
0 1 1 0 1 2 2
0 1 0 0 0 1 2
0 0 0 0 0 1 1
0 1 0 0 0 0 0
0 1 0 0 0 0 0

이때 안전구역, 즉 0인 곳의 개수 : 27
 */
public class Solution {
    //바이러스의 위치
    static class virus {
        int x, y;

        virus(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int[][] map;
    static int N;
    static int M;
    static int answer = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();

        map = new int[N][M];

        //map초기화
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                map[i][j] = sc.nextInt();
            }
        }
        makewall(0);
        System.out.println(answer);

    }

    //벽을 쌓는 함수
    public static void makewall(int count) {
        if (count == 3) {//3개의 벽을 세웠을 시
            spreadVirus();
            return;
        }
        //벽 3개 못 세웠으면 다시 세워
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) {//빈칸이라면
                    map[i][j] = 1; //벽 세우고
                    makewall(count + 1);
                    map[i][j] = 0;//다시 돌려놓고
                }
            }
        }

    }

    //바이러스 퍼뜨리는 함수
    public static void spreadVirus() {
        int[][] virusMap = new int[N][M];
        Queue<virus> virusList = new LinkedList<virus>();

        //copy만들기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                virusMap[i][j] = map[i][j];
            }
        }

        //바이러스를 바이러스리스트에 담는다
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (virusMap[i][j] == 2) {
                    virusList.add(new virus(i, j));
                }
            }
        }
        //바이러스리스트가 빌때까지
        while (!virusList.isEmpty()) {
            //하나를 꺼내 주위를 오염
            virus one = virusList.poll();
            for (int i = 0; i < 4; i++) {
                int nx = one.x + dx[i];
                int ny = one.y + dy[i];
                //범위내에 있으면서
                if (nx >= 0 && ny >= 0 && nx < N && ny < M) {
                    //바이러스 오염시킨다
                    if (virusMap[nx][ny] == 0) {
                        virusMap[nx][ny] = 2;
                        virusList.add(new virus(nx, ny));
                    }
                }
            }
        }
        safeCount(virusMap);
    }

    //오염되지 않은 빈칸의 갯수
    public static void safeCount(int[][] virusMap) {
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (virusMap[i][j] == 0) {
                    count++;
                }
            }
        }
        //가장 큰 오염되지 않은 빈칸의 수 를 구해야하므로
        answer = Math.max(answer, count);
    }

}

