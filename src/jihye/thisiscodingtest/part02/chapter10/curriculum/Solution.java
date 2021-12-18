package jihye.thisiscodingtest.part02.chapter10.curriculum;

import java.util.*;
import java.util.stream.Collectors;

public class Solution {
    static int N;
    static int [] time;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        sc.nextLine();
        int [] indegree = new int[N+1];
        time = new int[N+1];
        List<Integer> data = new ArrayList<>();
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        Arrays.fill(indegree,0);//진입차수 0으로
        Arrays.fill(time,0);

        //graph를 arrayList의 수 만큼 초기화 시켜준다.
        for(int i=0; i<N+1; i++) {
            graph.add(new ArrayList<Integer>());
        }

        for(int i=1; i < N+1; i++){
            data= Arrays.stream(sc.nextLine().split(" "))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
            time[i] = data.get(0);
            for(int x=1; data.get(x) != -1 ;x++){//-1이 나오면 줄이 끝나므로
                indegree[i]++;//진입차수를 증가시킨다.
                graph.get(data.get(x)).add(i);
            }

        }
        topologicalSort(indegree,graph);
    }

    static void topologicalSort(int[] indegree, ArrayList<ArrayList<Integer>> graph) {
        Queue<Integer> q = new LinkedList<Integer>(); // 간선의 갯수가 0인것
        int[] result = time.clone();

        //진입차수가 0인 노드에 노드 삽입
        for(int i=1; i<N+1; i++) {
            if(indegree[i]==0) {
                q.offer(i);
            }
        }


        while(!q.isEmpty()){
            int node = q.poll();
            for(Integer i : graph.get(node)) {//그래프에서
                result[i] = Math.max(result[i], result[node]+time[i]);
                indegree[i]--;

                if(indegree[i]==0)//진입차수가 0이 되면 큐에 넣어준다.
                    q.offer(i);
            }
        }

        for(int i=1; i < N + 1;i++){
            System.out.println(result[i]);
        }
    }
}
