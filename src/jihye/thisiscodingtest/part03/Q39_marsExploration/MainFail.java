//package jihye.thisiscodingtest.part03.Q39_marsExploration;
//
//
//import java.util.Arrays;
//import java.util.LinkedList;
//import java.util.Queue;
//import java.util.Scanner;
//
////class Coordinate {
////    int x;
////    int y;
////
////    Coordinate(int x, int y) {
////        this.x = x;
////        this.y = y;
////    }
////}
//
//public class MainFail {
//    static int dx[] = {-1, 1, 0, 0};
//    static int dy[] = {0, 0, -1, 1};
//    static int N;
//
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        int T = sc.nextInt();
//        int[][] map;
//
//        for (int i = 0; i < T; i++) {
//            N = sc.nextInt();
//            map = new int[N][N];
//
//            //각 map을 입력 받는다.
//            for (int j = 0; j < N; j++) {
//                for (int k = 0; k < N; k++) {
//                    map[j][k] = sc.nextInt();
//                }
//            }
//
//            System.out.println(findRoute(map));
//        }
//    }
//
//    public static int findRoute(int[][] map) {
//        int x = 0;
//        int y = 0;
//        boolean[][] visited = new boolean[N][N];
//        int[][] distance = new int[N][N];
//        Queue<Coordinate> queue = new LinkedList<>();
//        Coordinate coordinate = new Coordinate(0, 0);
//
//
//        //copy
//        for(int i=0; i < N; i++){
//            Arrays.fill(distance[i], 1250);
//        }
//
//        queue.add(coordinate);
//        distance[0][0] = map[0][0];
//
//        //queue가 빌때 까지
//        while (!queue.isEmpty()) {
//
//            Coordinate curr = queue.poll();
//            x = curr.x;
//            y = curr.y;
//
//            for (int i = 0; i < 4; i++) {
//                int nx = x + dx[i];
//                int ny = y + dy[i];
//                if (0 <= nx && nx < N && 0 <= ny && ny < N) {//범위를 넘지 않느다면
//                    if (visited[nx][ny] == false) {//그리고 방문하지 않은곳이라면
//                        distance[nx][ny] = Math.min(distance[x][y] + map[nx][ny], distance[nx][ny]);
//                        //최단 거리를 구한다.
//                        curr = new Coordinate(nx, ny);
//                        queue.add(curr);
//                        visited[nx][ny] = true;
////                        System.out.println(nx +" " +ny+" " + distance[nx][ny]);
//                    }
//                }
//            }
//
//        }
//        return distance[N - 1][N - 1];
//    }
//}
