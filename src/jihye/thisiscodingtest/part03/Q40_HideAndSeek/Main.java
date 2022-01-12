//package jihye.thisiscodingtest.part03.Q40_HideAndSeek;
//
//import java.util.*;
//
//public class Main {
//
//    public static int[][] graph;
//    public static boolean[] visited;
//    public static int INF = Integer.MAX_VALUE;
//    public static int N;
//    public static int M;
//
//    public static void main(String[] args) {
//
//        Scanner sc = new Scanner(System.in);
//        N = sc.nextInt();
//        M = sc.nextInt();
//
//        graph = new int[N+1][N+1];
//        visited = new int[N+1];
//
//        for(int i=0; i < N+1; i++){
//            Arrays.fill(graph[i],INF);
//        }
//
////        graph[1][1] = 0;
//
//        for (int i = 0; i < M; i++) {
//            int start = sc.nextInt();
//            int end = sc.nextInt();
//
//            graph[start][end] = 1;
//            graph[end][start] = 1;
//        }
//
//
//    }
//    public int bfs(int start){
//        Queue<Integer> queue = new LinkedList<>();
//        queue.add(2);
//        visited[start] = true;
//
//        while(!queue.isEmpty()){
//
//            int now = queue.poll();
//
//            for(int i=0; i<N+1; i++) {
//                int temp = graph[now][i];
//                // 방문하지 않았으면 방문처리 후 큐에 넣기
//                if(!visited[temp]) {
//                    visited[temp] = true;
//                    queue.add(temp);
//                    graph[1][]
//                }
//            }
//        }
//
//    }
//
//}
//
