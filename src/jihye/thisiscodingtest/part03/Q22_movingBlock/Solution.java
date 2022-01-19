//package jihye.thisiscodingtest.part03.Q22_movingBlock;
//
//import java.util.Arrays;
//import java.util.LinkedList;
//import java.util.Queue;
//import java.util.Scanner;
//
///*
//(1,1)의 위치에서 (N,N)까지 이동하는데 걸리는 시간을 출력
//
//무지가 이동취할 수 있는 행동
//1. 상,하,좌,우 이동
//2. 90도 돌기 first기준, second기준 으로 2가지 방향으로 가능
//
// */
//class Node {
//
//    public Node(int xFirst, int yFirst, int xEnd, int yEnd) {
//        this.xFirst = xFirst;
//        this.yFirst = yFirst;
//        this.xEnd = xEnd;
//        this.yEnd = yEnd;
//    }
//
//    public int getxFirst() {
//        return xFirst;
//    }
//
//    public void setxFirst(int xFirst) {
//        this.xFirst = xFirst;
//    }
//
//    public int getyFirst() {
//        return yFirst;
//    }
//
//    public void setyFirst(int yFirst) {
//        this.yFirst = yFirst;
//    }
//
//    public int getxEnd() {
//        return xEnd;
//    }
//
//    public void setxEnd(int xEnd) {
//        this.xEnd = xEnd;
//    }
//
//    public int getyEnd() {
//        return yEnd;
//    }
//
//    public void setyEnd(int yEnd) {
//        this.yEnd = yEnd;
//    }
//
//    public boolean isHorizontal() {
//        return ishorizontal;
//    }
//
//    public void setisHorizontal(boolean horizontal) {
//        this.ishorizontal = horizontal;
//    }
//
//    int xFirst;
//    int yFirst;
//    int xEnd;
//    int yEnd;
//    boolean ishorizontal;
//}
//
//public class Solution {
//    static int answer;
//    static int N;
//    static int dx[] = {-1, 1, 0, 0};
//    static int dy[] = {0, 0, -1, 1};
//
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        int[][] board = {{0, 0, 0, 1, 1}, {0, 0, 0, 1, 0}, {0, 1, 0, 1, 1},
//                {1, 1, 0, 0, 1}, {0, 0, 0, 0, 0}};
//        System.out.println(solution(board));
//    }
//
//    public static int solution(int[][] board) {
//        answer = 0;
//        N = board.length;
//
//        bfs(board);
//        return answer;
//    }
//
//    public static void bfs(int[][] board) {
//        Queue<Node> queue = new LinkedList<>();
//        boolean visited[][][] = new boolean[board.length + 1][board.length + 1][2];
//        Arrays.fill(visited[0], true);
//        int[] rotate = {-1, 1};
//
//        queue.add(new Node(1, 1, 1, 2));
//        while (!queue.isEmpty()) {
//            Node current = queue.poll();
//
//            int xFirst = current.getxFirst();
//            int yFirst = current.getyFirst();
//            int xEnd = current.getxEnd();
//            int yEnd = current.getyEnd();
//            boolean isHorizontal = current.isHorizontal();
//
//            if (hasArrived(xFirst, yFirst, xEnd, yEnd)) {
//                return;
//            }
//
//            //상하좌우 이동
//            for (int i = 0; i < 4; i++) {
//                int nxFirst = xFirst + dx[i];
//                int nyFirst = yFirst + dy[i];
//                int nxEnd = xEnd + dx[i];
//                int nyEnd = yEnd + dy[i];
//
//                if ((board[nxFirst][nyFirst] == 0) && (board[nxEnd][nyEnd] == 0)) {
////                    if(!(visited[nxFirst][nyFirst]) && (visited[nxEnd][nyEnd]))//빙문 확인후
//                    queue.add(new Node(nxFirst, nyFirst, nxEnd, nyEnd));
//                    queue.add(new Node(nxEnd, nyEnd, nxFirst, nyFirst));
//                }
//            }
//
//            //가로 회전
//            if (xFirst == xEnd) {
//                for (int r : rotate) {
//                    int nxFirst = xFirst + r;
//                    int nyFirst = yFirst;
//                    int nxEnd = xEnd + r;
//                    int nyEnd = yEnd;
//
//                    if ((board[nxFirst][nyFirst] == 0) && (board[nxEnd][nyEnd] == 0)) {
//                        !(visited[nxFirst][nyFirst]) && (visited[nxEnd][nyEnd])
//                        queue.add(new Node(nxFirst, nyFirst, nxEnd, nyEnd));
//                        queue.add(new Node(nxEnd, nyEnd, nxFirst, nyFirst));
//                    }
//                }
//            }
//
//            //세로 회전
//            if (yFirst == yEnd) {
//                for (int r : rotate) {
//                    int nxFirst = xFirst;
//                    int nyFirst = yFirst + r;
//                    int nxEnd = xEnd;
//                    int nyEnd = yEnd + r;
//
//                    if (!(visited[nxFirst][nyFirst]) && (visited[nxEnd][nyEnd])) {
//                        queue.add(new Node(nxFirst, nyFirst, nxEnd, nyEnd));
//                        queue.add(new Node(nxEnd, nyEnd, nxFirst, nyFirst));
//                    }
//                }
//            }
//            answer++;
//        }
//    }
//
//
//    //범위를 넘어가는지 확인하는 메서드
//    public static boolean boundaryCheck(int nxFirst, int nyFirst, int nxEnd, int nyEnd) {
//        return nxFirst < 0 || nyFirst < 0 || nxFirst >= N || nyFirst >= N ||
//                nxEnd < 0 || nyEnd < 0 || nxEnd >= N || nyEnd >= N;
//    }
//
//    //도착했는지 확인
//    public static boolean hasArrived(int xFirst, int yFirst, int xEnd, int yEnd) {
//        return (xFirst == N && yFirst == N) || (xEnd == N && yEnd == N);
//    }
//}
