package jihye.thisiscodingtest.part03.Q11_snake;

import java.util.*;

public class Solution {
    static int board[][];
    static int dx[] = new int[]{0, 1, 0, -1};//
    static int dy[] = new int[]{1, 0, -1, 0};
    static String[] direction;
    static int N;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        board = new int[N + 1][N + 1];

        int K = sc.nextInt();

        //0으로 초기화
        for (int i = 1; i < N + 1; i++) {
            Arrays.fill(board[i], 0);
        }

        //사과 위치 표기
        for (int i = 0; i < K; i++) {
            board[sc.nextInt()][sc.nextInt()] = 1;
        }

        direction = new String[sc.nextInt()];
        sc.nextLine();

        //가야되는 루트 정보 받기
        for (int i = 0; i < direction.length; i++) {
            direction[i] = sc.nextLine();
        }
        System.out.println(game());

    }

    public static int game() {
        int apple = 0;
        int x = 1, y = 1;
        int curDir = 0;//현재 가리키고 있는 방향
        int index = 0;//direction정보의 index
        int time = 0;//시간
        int nx, ny;
        String[] oneDir = direction[0].split(" ");
        board[x][y] = 2;//뱀이 존재하는 2로 표시
        Stack<int[]> stack = new Stack<>();//뱀의 위치 정보
        Queue<int[]> queue = new LinkedList<>();

        int arr[] = {1, 1};
        queue.add(arr);
        while (true) {
            nx = x + dx[curDir];
            ny = y + dy[curDir];

            System.out.println(nx + " " + ny);
            //범위를 벗어나면거나
            if (nx < 1 || nx > N || ny < 1 || ny > N) {
                time++;
                System.out.println("out of board");
                break;
            }
            //자기 몸과 부딫히면
            if (board[nx][ny] == 2) {
                time++;
                System.out.println("snake body");
                break;
            }


            arr = new int[]{nx, ny};
            if (board[nx][ny] == 0) {
                board[nx][ny] = 2;//새로 움직인 정보
                //queue에 위치 정보 넣어주기
                queue.add(arr);
                //이동했으니 이제
                int temp[] = queue.poll();
                System.out.println("out " + temp[0] + temp[1]);
                board[temp[0]][temp[1]] = 0;//0으로 되돌려준다
            }
            if (board[nx][ny] == 1) {
                board[nx][ny] = 2;//사과를 먹었으니 머리정보만 추가해주고 꼬리는 그대로 둔다.
                apple++;
                System.out.println("apple");
                queue.add(arr);
            }

            //위치 이동한 정보 x와 y에 업데이트
            x = nx;
            y = ny;

            time++;//시간
            System.out.println("t" + time);
            //count가 명시된 만큼 왔을때
            if (index < direction.length && time == Integer.parseInt(oneDir[0])) {
                System.out.println("chang" + " " + oneDir[0]);
                curDir = turn(curDir, oneDir[1]);
                index++;
                if (index != direction.length) {
                    oneDir = direction[index].split(" ");
                }
            }
        }
        return time;
    }

    //회전
    public static int turn(int curDir, String direction) {
        if (direction.equals("D")) {//오른쪽으로
            curDir++;
            curDir = curDir % 4;
            System.out.println("D" + curDir);
        } else {//왼쪽으로
            curDir--;
            if (curDir < 0) {
                curDir = 3;
            }
            System.out.println("L");
        }

        return curDir;
    }
}
