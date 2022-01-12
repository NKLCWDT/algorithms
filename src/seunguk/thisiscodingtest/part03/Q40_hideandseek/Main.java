package seunguk.thisiscodingtest.part03.Q40_hideandseek;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// bfs 인접리스트 방식 성공

public class Main {
    static int N;
    static int M;
    static ArrayList<Integer>[] arr;
    static boolean[] visited;
    static int[] distance;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        arr = new ArrayList[N+1];
        visited = new boolean[N+1];
        distance = new int[N+1];

        for (int i = 1; i < N + 1; i++) {
            arr[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            arr[a].add(b);
            arr[b].add(a);
        }
        bfs();
        int maxx = 0;
        boolean check = false;
        int resultNo = 0;
        int resultCount = 0;

        // 거리값 가장 큰 값 구하기
        for (int i = 1; i < N + 1; i++) {
            maxx = Math.max(maxx, distance[i]);
        }

        for (int i = 1; i < N + 1; i++) {
            if (!check) { // 거리가 같은 헛간이 여러개이면 가장 작은 헛간번호 출력이므로 check 변수를 써서 가장 작은 값을 저장해준다.
                if (maxx == distance[i]) {
                    check = true;
                    resultCount++;
                    resultNo = i;
                }
            } else {
                if (maxx == distance[i]) {
                    resultCount++;
                }
            }
        }

        System.out.println(resultNo + " " + distance[resultNo] + " " + resultCount);

    }
    private static void bfs() {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);
        visited[1] = true;

        while (!queue.isEmpty()) {
            int now = queue.remove();
            for (Integer next : arr[now]) {
                if (!visited[next]) {
                    visited[next] = true;
                    distance[next] = distance[now] + 1;
                    queue.add(next);
                }
            }
        }
    }
}

/*
6 7
3 6
4 3
3 2
1 3
1 2
2 4
5 2
*/