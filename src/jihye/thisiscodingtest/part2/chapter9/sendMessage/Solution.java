package jihye.thisiscodingtest.part2.chapter9.sendMessage;
import java.util.*;

//Comparable 알아보기
class Node implements Comparable<Node>{
    int end, weight;

    public Node(int end, int weight){
        this.end = end;
        this.weight = weight;
    }

    @Override
    public int compareTo(Node o) {
        return weight -o.weight;
    }
}
public class Solution {

    static int N, M, C;
    static List<Node>[] list;
    static int[] dist;
    static int INF = 100_1000_000;//MAX_VALUE+1 을 하면 MIN_VALUE가 된다=> think of binary

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        C = sc.nextInt();
        list = new ArrayList[N+1];
        dist = new int[N+1];
        int[][] graph = new int[N+1][N+1];

        Arrays.fill(dist, INF);

        for(int i=1; i<N+1;i++){
            list[i] = new ArrayList<>();
        }

        //리스트에 그래프 정보 초기화
        for(int i=0; i<M;i++){
            int start = sc.nextInt();
            int end = sc.nextInt();
            int weight = sc.nextInt();
            list[start].add(new Node(end, weight));
        }

        dijkstra(C);

        //출력
        int count =0;
        int max =0;
        for(int i=1;i<N+1;i++){
            if(!(dist[i] == INF) && !(dist[i] == 0)){//본인자신과 INF가 든곳은 제외하고
                count++;//몇개의 도시를 갈 수 있는지
                max = Math.max(max, dist[i]);// 그중에 최대 거리
            }
        }
        System.out.println(count + " "+ max);
    }

    public static void dijkstra(int start){
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
        boolean[] check = new boolean[N+1];
        priorityQueue.add(new Node(start, 0));
        dist[start] = 0;

        while(!priorityQueue.isEmpty()){//큐가 빌때까지
            Node curNode = priorityQueue.poll();//현재노드
            int cur = curNode.end;

            if(check[cur]==true){//이미 방문했을시에는 패스한다.
                continue;
            }check[cur] = true;

            for(Node node : list[cur]){//list에서 node를 하나씩 꺼내서
                if(dist[node.end] > dist[cur] + node.weight){//만일 현재 노드를 거쳐서 가는 경우가 더 짧은경우
                    dist[node.end] = dist[cur] + node.weight;//거리를 더 짧은 걸로 업데이트후
                    priorityQueue.add(new Node(node.end, dist[node.end]));//큐에 더해준다
                }
            }
        }
    }
}

