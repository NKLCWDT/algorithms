package jihye.thisiscodingtest.part03.Q15_findCity;

import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int K = sc.nextInt();
        int X = sc.nextInt();
        //그래프의 정보를 담는다.
        ArrayList<Integer> graph[] = new ArrayList[N+1];

        //그래프를 ArrayList로
        for(int i=0; i < N+1; i++){
            graph[i] = new ArrayList<Integer>();
        }

        //간선 정보를 담는다.
        for(int i=0; i < M; i++){
            graph[sc.nextInt()].add(sc.nextInt());
        }

        int [] distance = bfs(graph, X);

        boolean check = false;
        for(int i=0; i < distance.length;i++){
            if(distance[i] == K){
                System.out.println(i);
                check = true;
            }
        }
        if(check == false){
            System.out.println(-1);
        }
    }
    public static int[] bfs(ArrayList[] graph, int start){
        //최단 거리를 담는다.
        int distance[] = new int[graph.length +1];

        //처음에는 모든 거리를 -1로 초기화 한다.
        Arrays.fill(distance,-1);
        //출발지점은 0으로 설정한다.
        distance[start] =0;
        Queue<Integer> queue = new LinkedList<>();

        //시작 노드를 큐에 담는다.
        queue.add(start);
        //큐가 빌때까지
        while(!queue.isEmpty()){
            //큐에서 꺼낸다.
            int now = queue.poll();
            int count = 0;
            //큐에서 꺼낸 노드의 연결된 노드들까지 거리 담는다.
            while (graph[now].size() > count) {
                int next = (int) graph[now].get(count);
                if(distance[next]==-1){
                    distance[next] = distance[now]+1;
                    queue.add(next);
                }
                count++;
            }
        }
        return distance;
    }
}
