package seohae.thiscodingtest.part03.Q11_Snake;

import java.util.*;

/*
   Dummy 도스 게임

   사과를 먹으면 뱀 길이가 늘어나고, 벽 또는 자기 자신의 몸과 부딪히면 게임이 끝난다.

   N x N 정사각형 보드
   사과의 위치
   보드의 상하좌우 끝 : 벽
   첫 시작의 뱀 위치 (1,1) 뱀의 길이는 1
   뱀은 처음에 오른쪽을 향함

   1) 뱀은 몸길이를 늘려 머리를 다음 칸에 위치
   2) 이동할 칸에 사과가 있다면 사과가 없어지고 꼬리는 움직이지 않음
   3) 이동할 칸에 사과가 없다면 몸길이를 줄여서 꼬리가 위치한 칸을 비워줌. (몸길이 변화X)

   사과의 위치와 뱀의 이동 경로가 주어질때 이 게임이 몇초에 끝나는가?
 */
public class Main {
    private static int N; // 보드의 크기
    private static int appleCount; // 사과 개수
    private static int[][] appleArr; // 사과 위치 배열
    private static int directionCount; // 방향 이동 횟수
    private static List<List<String>> directionList = new ArrayList<>(); // 방향 이동 배열

    // 몸통과 겹치는 경우를 알아야한다. 그러므로 몸통부터 머리까지의 길이를 리스트에 담아야됨
    private static List<int[]> snakeLocationList = new ArrayList<>();
    // private static int[] startSnakeLocation = new int[N]; // 뱀의 현재 위치 시작 (x, y)
    // private static int[] endSnakeLocation = new int[N]; // 뱀의 현재 위치 종료 (x, y)
    private static int direction = 3; /* 처음에는 다음 칸에 위치이므로 우로 +1 */
    /*
        상(0), 하(1), 좌(2), 우(3)
        (-1, 0)
        (1, 0)
        (0, -1)
        (0, 1)
     */
    private static final int[] dx = new int[]{-1, 1, 0, 0}; // 행
    private static final int[] dy = new int[]{0, 0, -1, 1}; // 열

    private static Map<Integer, Integer> DMap = new HashMap<>();
    private static Map<Integer, Integer> LMap = new HashMap<>();

    public static void main(String[] args) {
        input();

        int resultTime = 0;

        while (true) {
            // 1초 증가
            resultTime = resultTime + 1;

            /* 이동 */
            if (!snakeMove()) {
                // resultTime = resultTime - 1;
                break;
            }

            /* 해당 초에 도달했을 때 */
            if (!directionList.isEmpty()) {
                if (Integer.parseInt(directionList.get(0).get(0)) == resultTime) {
                    // 방향 전환
                    changeDirection(directionList.get(0).get(1));
                    directionList.remove(0);
                }
            }
        }

        System.out.println(resultTime);
    }

    /**
     * 방향 전환
     */
    private static void changeDirection(String target) {
        /**
         * 현재 방향 기준으로 회전
         */
        if ("D".equalsIgnoreCase(target)) { // 오른쪽으로 90도 방향
            /*
              상(0), 하(1), 좌(2), 우(3)
               (-1, 0)
               (1, 0)
               (0, -1)
               (0, 1)

               1) 우 -> 하 (3 -> 1)
               2) 하 -> 좌 (1 -> 2)
               3) 좌 -> 상 (2 -> 0)
               4) 상 -> 우 (0 -> 3)
             */
            direction = DMap.get(direction);
        } else if ("L".equalsIgnoreCase(target)) { // 왼쪽으로 90도 방향
            /*
               1) 우 -> 상 (3 -> 0)
               2) 상 -> 좌 (0 -> 2)
               3) 좌 -> 하 (2 -> 1)
               5) 하 -> 우 (1 -> 3)
             */
            direction = LMap.get(direction);
        }
    }


    /**
     * 뱀 이동
     */
    private static boolean snakeMove() {
//        int startX = startSnakeLocation[0];
//        int startY = startSnakeLocation[1];
//
//        int endX = startSnakeLocation[0];
//        int endY = startSnakeLocation[1];

        // 뱀의 현재 위치
        int x = snakeLocationList.get(snakeLocationList.size() - 1)[0];
        int y = snakeLocationList.get(snakeLocationList.size() - 1)[1];

        // 이동될 위치
        int nx = x + dx[direction];
        int ny = y + dy[direction];

        // 이동 validation
        if (nx < 1 || nx > N || ny < 1 || ny > N) {
            return false;
        }

        // 뱀 몸통이랑 겹쳤을 경우 false
        for (int[] s : snakeLocationList) {
            if (s[0] == nx && s[1] == ny) {
                System.out.println("몸통에 겹침 : " + nx + " ," + ny);
                return false;
            }
        }

        /* 사과 개수 체크 */
        if (appleArr[nx][ny] == 1) { // 사과 존재
            appleArr[nx][ny] = 0; // 사과 없애기
            snakeLocationList.add(new int[]{nx, ny});
        } else {
            /* 사과가 없으면 이동은 되나 꼬리 제거 */
            snakeLocationList.add(new int[]{nx, ny});
            snakeLocationList.remove(0); // snake 꼬리 제거
        }

        return true;
    }

    /*
      6
      3
      3 4
      2 5
      5 3
      3
      3 D
      15 L
      17 D


      6 // 보드의 크기 N
      3 // 사과의 개수 appleCount
      3 4 // 사과의 위치 (행 열)
      2 5
      5 3
      3 // 뱀의 방향 변환 횟수 DirectionCount
      3 D // 3초가 끝난뒤 오른쪽으로 90도 회전
      15 L // 15초가 끝난뒤 왼쪽으로 90도 회전
      17 D

     */
    private static void input() {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt(); // 보드의 크기
        appleArr = new int[N + 1][N + 1];

        /* 사과 개수 및 사과 배열 셋팅 */
        appleCount = sc.nextInt();

        for(int i = 0; i < appleCount; i++) {
            int row = sc.nextInt();
            int column = sc.nextInt();

            appleArr[row][column] = 1;
        }

        /* 방향 이동 횟수 및 방향 이동 리스트 셋팅 */
        directionCount = sc.nextInt();

        for(int i = 0; i < directionCount; i++) {
            String second = sc.next();
            String direction = sc.next();

            List<String> list = new ArrayList<>();
            list.add(second);
            list.add(direction);

            directionList.add(list);
        }

        /* 뱀의 현재 위치 셋팅 */
        snakeLocationList.add(new int[]{1, 1});

        /* 회전 param set */
        DMap.put(0, 3);
        DMap.put(1, 2);
        DMap.put(2, 0);
        DMap.put(3, 1);

        LMap.put(0, 2);
        LMap.put(1, 3);
        LMap.put(2, 1);
        LMap.put(3, 0);
//        startSnakeLocation[0] = 1;
//        startSnakeLocation[1] = 1;
//
//        endSnakeLocation[0] = 1;
//        endSnakeLocation[1] = 1;
    }
}

/*
10
4
1 2
1 3
1 4
1 5
4
8 D
10 D
11 D
13 L
 */

/*
10
5
1 5
1 3
1 2
1 6
1 7
4
8 D
10 D
11 D
13 L
 */

/*
test case
https://stack07142.tistory.com/176
 */