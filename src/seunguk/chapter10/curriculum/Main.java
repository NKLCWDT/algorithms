package seunguk.chapter10.curriculum;

import java.util.*;

public class Main {
    static int[] indegree;
    static int[] time;
    static ArrayList<Integer>[] graph;
    static int N;
    static int[] dp;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();

        // 진입차수 저장
        indegree = new int[N+1];
        // 강의시간 저장
        time = new int[N+1];
        //
        graph = new ArrayList[N+1];
        // 강의시간 누적
        dp = new int[N+1];

        // 그래프 초기화
        for (int i = 0; i < N+1; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 1; i < N+1; i++) {
            time[i] = sc.nextInt();
            while (true) {
                int a = sc.nextInt(); // a : 선수강의
                if (a == -1) {
                    break;
                }
                graph[a].add(i); // 선수강의 a에 i를 추가해 그래프 화살표를 그려준다.
                indegree[i]++; // i는 선수강의가 추가 되었으므로 진입차수를 올려준다.
            }
        }
        topology_sort();
    }
    private static void topology_sort() {
        Queue<Integer> queue = new LinkedList<>();

        // 처음 시작할 때 진입차수가 0인 노드를 큐에 삽입
        for (int i = 1; i < N+1; i++) {
            if (indegree[i] == 0) {
                queue.add(i);
            }
        }

        // dp배열 time배열로 초기화
        for (int i = 1; i < N + 1; i++) {
            dp[i] = time[i];
        }

        while (!queue.isEmpty()) {
            int now = queue.remove();
            for (int next : graph[now]) {
                // 다음 강의에 저장된 시간보다 현재 강의시간 + 다음강의 시간 중 더 큰 값을 선택한다.
                // 왜냐하면 선수과목을 모두 들어야 현재 강의를 들을 수 있기 때문이다.
                dp[next] = Math.max(dp[next], dp[now] + time[next]);
                indegree[next]--;
                if (indegree[next] == 0) {
                    queue.add(next);
                }
            }
        }

        for (int i = 1; i < dp.length; i++) {
            System.out.println(dp[i]);
        }
    }
}

/*
5
10 -1
10 1 -1
4 1 -1
4 3 1 -1
3 3 -1
*/