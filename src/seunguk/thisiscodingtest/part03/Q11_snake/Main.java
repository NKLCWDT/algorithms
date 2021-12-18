package seunguk.thisiscodingtest.part03.Q11_snake;

import java.util.*;

public class Main {
    // 동 남 서 북
    static int[] dx = {0,1,0,-1};
    static int[] dy = {1,0,-1,0};
    static int N;
    static int K;
    static int direction;
    static int time = 0;
    static int[][] arr;
    static HashMap<Integer, String> check = new HashMap<>();
    static class Node {
        int x;
        int y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        K = sc.nextInt();
        arr = new int[N+1][N+1];
        // 맨처음 방향 동쪽
        direction = 0;
        for (int i = 0; i < K; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            arr[a][b] = 1;
        }
        int L = sc.nextInt();
        for (int i = 0; i < L; i++) {
            Integer X = sc.nextInt();
            String C = sc.next();
            check.put(X, C);
        }
        // 처음 시작점 (1,1)
        bfs(1,1);
        System.out.println(time);
    }

    private static void bfs(int x, int y) {
        // arr 0은 => 빈값
        // arr 1은 => 사과
        // arr 2는 => 뱀
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(x,y));
        arr[x][y] = 2; // 출발위치에 뱀넣어줌

        // bfs처럼 queue에 들어있는값 계속 빼주는식으로 구현 했었는데
        // queue에 들어있는값을 꼬리값으로 보고 꼬리를 앞으로 당겨줘야 할 때만 queue에서 빼줬다. (이 부분 답지 참고)
        while (true) {
            time++;
            x += dx[direction];
            y += dy[direction];

            // 현재 시간인 키값이 존재할 경우 회전
            if (check.containsKey(time)) {
                direction(check.get(time));
            }
            if (x > 0 && y > 0 &&  x < N+1 && y < N+1) {
                if (arr[x][y] == 1) {
                    arr[x][y] = 2;
                    queue.add(new Node(x, y));
                } else if (arr[x][y] == 0) { // 빈값이면 꼬리를 줄여야 하기 때문에 꼬리값 받아옴
                    Node node = queue.remove();
                    int tx = node.x;
                    int ty = node.y;
                    arr[tx][ty] = 0;
                    arr[x][y] = 2;
                    queue.add(new Node(x, y));
                } else if (arr[x][y] == 2) { // 자기 몸통과 접촉하면 종료
                    return;
                }

            } else { // 범위 안에 없으면 종료
                return;
            }

        }
    }

    private static void direction(String direct) {
        if (direct.equals("L")) {
            direction -= 1;
            if (direction < 0) {
                direction = 3;
            }
        } else {
            direction += 1;
            if (direction > 3) {
                direction = 0;
            }
        }
    }
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
*/
