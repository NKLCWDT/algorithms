package seunguk.chapter10.topological;

import java.util.*;

public class Main {
    static int[] indegree;
    static ArrayList<Integer>[] graph;
    static int V;
    static int E;
    static List<Integer> result = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        V = sc.nextInt();
        E = sc.nextInt();
        indegree = new int[V+1];
        graph = new ArrayList[V+1];

        // 그래프 초기화
        for (int i = 0; i < V+1; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < E; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            // 방향그래프
            graph[a].add(b);
            // 진입차수 증가
            indegree[b] += 1;
        }
        topology_sort();
        for (Integer integer : result) {
            System.out.print(integer + " ");
        }

    }
    private static void topology_sort() {
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 1; i < V+1; i++) {
            if (indegree[i] == 0) {
                queue.add(i);
            }
        }

        while (!queue.isEmpty()) {
            int node = queue.remove();
            result.add(node);
            for (int next : graph[node]) {
                // 진입 차수 감소
                indegree[next]--;
                // 만약 진입 차수가 0이라면 큐에 넣는다.
                if (indegree[next] == 0) {
                    queue.add(next);
                }
            }
        }
    }
}
/*
7 8
1 2
1 5
2 3
2 6
3 4
4 7
5 6
6 4
*/
