package jihye.chapter9.futureCity;
import java.util.*;

public class Solution {
    static int N;
    static int M;
    static int [][]graph;
    static int INF = Integer.MAX_VALUE/2;//MAX_VALUE+1 을 하면 MIN_VALUE가 된다=> think of binary

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        graph = new int[N+1][N+1];


        //무한으로 초기화
        for(int i=0; i< N+1;i++){
            Arrays.fill(graph[i],INF);
        }

        //자기자신에서 자기자신으로 가는것은 0으로 초기화
        for(int i=1; i< N+1;i++){
            for(int j=1; j<N+1; j++){
                if(i == j){
                    graph[i][j] = 0;
                }
            }
        }

        //각선에 대한 정보를 입력받아 그 값으로 초기화
        for(int i=0; i<M;i++){
            int a= sc.nextInt();
            int b= sc.nextInt();
            graph[a][b] = 1;
            graph[b][a] = 1;//양방향 그래프이므로
        }

        int K = sc.nextInt();
        int X = sc.nextInt();

        //플로이드 워셜 알고리즘
        for(int k=1; k < N + 1; k++){
            for(int i=1; i <N + 1;i++) {
                for (int j = 1; j < N + 1; j++) {
                    System.out.println(graph[i][j]+" + "+ graph[i][k]+ " + "+ graph[k][j]);
                    graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
                    System.out.println(graph[i][j]);
                }
            }
        }

        int distance = graph[1][K]+graph[K][X];
        System.out.println(graph[1][K] + " "+ graph[K][X]);
        if(distance >= INF){
            System.out.println(-1);
        }else{
            System.out.println(distance);
        }
    }
//
//
//    public dijkstra(int start, int end, ArrayList graph,int N){
//        int [] distance = new int[N+1];
//        Queue<Object> queue = new LinkedList();
//        Integer[] starting = {0,start}; // count와 시작인덱스를 담는다.
//        queue.add(starting);
//        distance[start] = 0;
//        while(!queue.isEmpty()){
//            Integer [] arr=new Integer[2];
//            arr = (Integer[]) queue.poll();
//            if( distance[arr[1]] < arr[0] ){
//                continue;
//            }
//            for(int i=0; i < graph.size(); i++){
//                int cost = arr[0]+ (int)graph.get(arr[1]);
//                if(cost < distance[graph.get(arr[0]){
//
//                }
//            }
//        }


}
