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
//class Node{
//
//    public Node(int xFirst, int yFirst, int xEnd, int yEnd, boolean ishorizontal) {
//        this.xFirst = xFirst;
//        this.yFirst = yFirst;
//        this.xEnd = xEnd;
//        this.yEnd = yEnd;
//        this.ishorizontal = ishorizontal;
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
//
//
//
//}
//public class MainFail {
//    static int answer;
//    static int N;
//    static int dx[] = {-1,1,0,0};
//    static int dy[] = {0,0,-1,1};
//
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//
//    }
//    public int solution(int[][] board) {
//        answer = 0;
//        N = board.length;
//
//        bfs(board);
//        return answer;
//    }
//    public static void bfs(int[][] board){
//        Queue<Node> queue = new LinkedList<>();
//        boolean visited[][] = new boolean[board.length+1][board.length+1];
//        Arrays.fill(visited[0], true);
//
//        queue.add(new Node(1,1,1,2, true));
//        while (!queue.isEmpty()){
//            Node current = queue.poll();
//
//            int xFirst = current.getxFirst();
//            int yFirst = current.getyFirst();
//            int xEnd = current.getxEnd();
//            int yEnd = current.getyEnd();
//            boolean isHorizontal = current.isHorizontal();
//
//            for (int i = 0; i < 4; i++) {
//
//                //이동시에 두가지 케이스로 나뉜다.
//                //가로일시, 세로일시
//                if(isHorizontal){   //가로일때
//                    int nxFirst = xFirst + dx[i];
//                    int nyFirst = yFirst + dy[i];
//                    int nxEnd = xEnd + dx[i];
//                    int nyEnd = yEnd + dy[i];
//                }else{  //세로일때
//                    int nxFirst = xFirst + dx[i];
//                    int nyFirst = yFirst + dy[i];
//                    int nxEnd = xEnd + dx[i];
//                    int nyEnd = yEnd + dy[i];
//                }
//
//                if(boundaryCheck(nxFirst,nyFirst,nxEnd, nyEnd) || visit[nx][ny] ){
//                    //90도 도는 로직
//
//                }
//
//            }
//        }
//    }
//    public static void turn(){
//
//    }
//    //범위를 넘어가는지 확인하는 메서드
//    public static boolean boundaryCheck(int nxFirst,int nyFirst,int nxEnd, int nyEnd){
//        return nxFirst < 0 || nyFirst < 0 || nxFirst >= N || nyFirst >= N ||
//                nxEnd < 0 || nyEnd < 0 || nxEnd >= N || nyEnd >= N;
//    }
//}
