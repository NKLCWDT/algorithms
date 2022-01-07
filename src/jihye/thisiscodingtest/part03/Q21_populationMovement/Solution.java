package jihye.thisiscodingtest.part03.Q21_populationMovement;

import java.util.*;

public class Solution {
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[][] union;
    static int[][] map;
    static int N;
    static int L;
    static int R;


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        L = sc.nextInt();
        R = sc.nextInt();
        map = new int[N][N];

        //국가마다 인구수를 담는다
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = sc.nextInt();
            }
        }

        int totalCount = 0;

        while (true) {

            //각국에 방문정보를 초기화 시켜준다.
            union = new int[N][N];
            for (int i = 0; i < N; i++) {
                Arrays.fill(union[i], -1);
            }

            int unionNum = 0;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (union[i][j] == -1) {// 방문하지 않은 국가는 방문한다
                        process(i, j, unionNum);
//                        System.out.println("union"+ unionNum);
                        unionNum++;
                    }
                }
            }
            if (unionNum == N * N) {
                break;
            }
            totalCount++;
        }
        System.out.println(totalCount);
    }

    public static void process(int x, int y, int index) {
        ArrayList<int[]> united = new ArrayList<>();
        int[] coordinate;
        coordinate = new int[2];
        coordinate[0] = x;
        coordinate[1] = y;
        united.add(coordinate);

        Queue<int[]> queue = new LinkedList<>();
        queue.add(coordinate);
//        System.out.println(x + " " + y);
        //몇번째 연합인지 담아준다
        union[x][y] = index;
        //연합국의 전체 인구수를 구하기 위해
        int totalUnion = map[x][y];
        //현재 연합국의 수
        int count = 1;

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            x = curr[0];
            y = curr[1];
            //상하좌우 확인한다
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (0 <= nx && nx < N && 0 <= ny && ny < N && union[nx][ny] == -1) {
                    int diff = Math.abs(map[nx][ny] - map[x][y]);
                    if (L <= diff && diff <= R) {//국가들의 사람수 차이가 L이상 R이하라면
                        coordinate = new int[2];
                        coordinate[0] = nx;
                        coordinate[1] = ny;
                        queue.add(coordinate);//큐에 더해준다
                        union[nx][ny] = index;//현재 몇번째 연합인지 표시
                        totalUnion += map[nx][ny];// 전체 연합 사람수에 더해준다
                        count++;
                        united.add(coordinate);
                    }
                }
            }
        }
        for(int[] one : united){//연합국들의 사람 수 업데이트해준다
            map[one[0]][one[1]] = totalUnion/count;
        }
    }
}
