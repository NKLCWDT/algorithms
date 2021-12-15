package annmj.thisiscodingtest.part02.chapter5.escapethemaze;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Solution {
    static FastReader scan = new FastReader();

    static int N, M;
    static int[][] arr;
    static int[][] visitCount;
    static boolean[][] visit;
    static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static void input() {

        N = scan.nextInt();
        M = scan.nextInt();
        arr = new int[N+1][M+1];
        visit = new boolean[N + 1][M + 1];
        visitCount = new int[N+1][M+1];
        /*
         * 공백기준으로 인풋 입력 받을 때
         */
//        for (int i = 1; i <= N; i++) {
//            for (int j = 1; j <= M; j++) {
//                arr[i][j]= scan.nextInt();
//            }
//        }

        /*
         * 한줄로 입력 받을 때
         */
        for (int i = 1; i <= N; i++) {
            int[] digits = Stream.of(scan.next().split("")).mapToInt(Integer::parseInt).toArray();
            for (int j = 0; j < digits.length; j++) {
                arr[i][j+1] = digits[j]; // 범위 주의!!!
            }
        }
    }

    static void pro() {
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(1, 1));
        visit[1][1] = true;
        visitCount[1][1] = 1;
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nx = node.x + dir[i][0];
                int ny = node.y + dir[i][1];
                if(nx < 1 || ny < 1 || nx > N || ny > M) continue;
                if(arr[nx][ny] == 0) continue;
                if(visit[nx][ny]) continue;
                visitCount[nx][ny] = visitCount[node.x][node.y] + 1; // 미로찾기의 핵심
                queue.add(new Node(nx, ny));
                visit[nx][ny] = true;
            }
        }

        System.out.println(visitCount[N][M]);
    }

    static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    public static void main(String[] args) {
        input();
        pro();
    }

    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        Integer nextInt() {
            return Integer.parseInt(next());
        }

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;

        }


        /*
         * 1. charAt사용 for문으로 만들기
         *   String str = "12345";
         *         int[] digits = new int[str.length()];
         *         for(int i=0; i<str.length(); i++)
         *
         *                 digits[i] = str.charAt(i) - '0';
         *
         *
         *         System.out.println( Arrays.toString(digits) );
         *         // [1, 2, 3, 4, 5]
         *
         *
         *
         * 2. Stream으로 만들기
         * String str = "12345";
         *         int[] digits = Stream.of(str.split("")).mapToInt(Integer::parseInt).toArray();
         *         System.out.println( Arrays.toString(digits) );
         *         // [1, 2, 3, 4, 5]
         */
    }
}

