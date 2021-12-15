package annmj.thisiscodingtest.part03.Q11_snake;

import java.util.*;

public class Main {
    // 지도와 뱀 위치 , 꼬리, 머리 필요
    // 뱀은 이동하면서 방향 회전, 사과 찾기, 꼬리 없애기, 몸 길이 증가

    // 방향
    static int[] dirX = {0, 1, 0, -1}; // 동, 남, 서, 북 , 행
    static int[] dirY = {1, 0, -1, 0}; // 동, 남, 서, 북 , 열
    static int dir = 0; // 처음은 동쪽에서 시작

    static int N; // 보드의 크기 N * N
    static int K; // 사과의 개수

    static int[][] graph;
    static Snake snake;
    static boolean[][] apple;
    static PriorityQueue<Move> rotations = new PriorityQueue<>((o1, o2) -> o1.second - o2.second); // 시간순 정렬

    static void input() {
        Scanner scanner = new Scanner(System.in);
        N = scanner.nextInt();
        K = scanner.nextInt();
        graph = new int[N+1][N+1];
        apple = new boolean[N+1][N+1];
        snake = new Snake(new ArrayList<>());

        // 사과 위치
        for (int i = 0; i < K; i++) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            apple[x][y] = true;
        }

        int count = scanner.nextInt();

        for (int i = 0; i < count; i++) {
            Move move = new Move(scanner.nextInt(), scanner.next());
            rotations.add(move);
        }
    }
    public static void main(String[] args) {
        input();
        process();
    }

    public static void process() {
        int nx, ny;
        int second = 0;

        int x = 1 , y = 1; // 현재 좌표
        snake.body.add(new Node(1, 1)); // 뱀의 몸 초기화

        while (true) {
            second++;

            nx = x + dirX[dir];
            ny = y + dirY[dir];
            x = nx;
            y = ny;

            if (nx > N || ny > N || nx < 1 || ny < 1) {
                break;
            }

            for (Node node : snake.body) {
                // 다음 위치할 좌표가 snake 의 몸과 접촉한다면 종료
                if (node.x == nx && node.y == ny) {
                    System.out.println(second);
                    return;
                }
            }

            snake.body.add(new Node(nx, ny)); // 머리 움직이기
            // 사과가 있는지 확인
            if (apple[nx][ny]) {
                // 몸길이 늘리기, 머리를 다음 칸에 위치
                apple[nx][ny] = false; // 사과 없애기
            } else {
                // 몸길이를 줄여서 꼬리가 위치한 칸을 비워준다
                snake.body.remove(0);
            }
            rotate(second);
        }

        System.out.println(second);
    }
    static void rotate(int second) {
        Move move = rotations.peek();
        if (!rotations.isEmpty() && move.second == second) {
            rotations.remove();
            if (move.dir.equals("L")) {
                // L
                dir--;
                if (dir < 0) {
                    dir = 3;
                }
            } else {
                // D
                dir++;
                if (dir > 3) {
                    dir = 0;
                }
            }
        }
    }
    static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static class Snake {
        ArrayList<Node> body;

        public Snake(ArrayList<Node> body) {
            this.body = body;
        }
    }

    static class Move {
        int second; // 초
        String dir; // 방향

        public Move(int second, String dir) {
            this.second = second;
            this.dir = dir;
        }
    }
}
/*
* 10
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
* */