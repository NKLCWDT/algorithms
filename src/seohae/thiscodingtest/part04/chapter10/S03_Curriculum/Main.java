package seohae.thiscodingtest.part04.chapter10.S03_Curriculum;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.stream.IntStream;

/*
   강의 커리큘럼이 있다.
   먼저 들어야하는 강의가 있다.

   N개의 강의를 듣고자한다.
   1번 ~ N번의 강의

   동시에 여러개도 들을 수있다.
   예를들어, N=3) 3번 강의의 선수 강의로 1번과 2번 강의가 있고, 1번과 2번 강의는 선수 강의가 없을때
   1번 강의 : 30시간
   2번 강의 : 20시간
   3번 강의 : 40시간

   1번 강읠르 수강하기까지의 최소 시간 : 30시간
   2번 강의를 수강하기까지의 최소 시간 : 20시간
   3번 강의를 수강하기까지의 최소 시간 : 70시간

   -> 위상 정렬 알고리즘 (순서대로 나열하여 모든 선후 관계를 지키는 전체 순서를 계산)
 */
public class Main {
    public static int v; // 노드의 개수(V)

    // 노드의 개수는 최대 100,000개라고 가정
    // 모든 노드에 대한 진입차수는 0으로 초기화
    public static int[] indegree = new int[100001];

    // 각 노드에 연결된 간선 정보를 담기 위한 연결 리스트 초기화
    public static ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();

    // 각 강의 시간을 0으로 초기화
    public static int[] times = new int[501];

    // 위상 정렬 함수
    public static void topologySort() {
        ArrayList<Integer> result = new ArrayList<>(); // 알고리즘 수행 결과를 담을 리스트
        Queue<Integer> q = new LinkedList<>(); // 큐 라이브러리 사용

        // 처음 시작할 때는 진입차수가 0인 노드를 큐에 삽입
        for (int i = 1; i <= v; i++) {
            if (indegree[i] == 0) {
                q.offer(i);
            }
        }

        for (int i = 1; i <= v; i++) {
            result.add(times[i]);
        }

        // 큐가 빌 때까지 반복
        while (!q.isEmpty()) {
            // 큐에서 원소 꺼내기
            int now = q.poll();

            result.add(now);

            // 연결된 노드만큼 반복 (=자신을 선수강의를 가진 노드만큼 반복)
            IntStream.range(0, graph.get(now).size()).forEach(i -> {
                /* (다음 강의 시간), (현재 강의 시간 + 다음 강의 시간) 중 최대값 */
                /*
                 * 이해하기 어렵다. 문제에 있는 예시로 이해하자
                 * 3번 강의 (40시간)
                 * 3번강의의 진입 노드 : 1번 강의 (30시간) , 2번 강의 (20시간)
                 *
                 * 현재노드 40시간과 연결된 노드의 강의시간 + 현재 노드 40시간의 최대값이 정답이다.
                 * 1) 40시간 + 30시간
                 * 2) 40시간 + 20시간
                 *
                 * 1)번의 경우가 3번 강의를 수강하기까지의 최소 시간이다.
                 */
                result.set(graph.get(now).get(i),
                        Math.max(result.get(graph.get(now).get(i))
                                , result.get(now) + times[graph.get(now).get(i)]));

                /* 나가는 간선 삭제 */
                indegree[graph.get(now).get(i)] -= 1;

                /* 진입차수가 0인 노드를 다시 큐에 삽입 */
                if (indegree[graph.get(now).get(i)] == 0) {
                    q.offer(graph.get(now).get(i));
                }
            });
        }

        // 위상 정렬을 수행한 결과 출력
        for (int i = 1; i <= v; i++) {
            System.out.println(result.get(i));
        }
    }

    /*
      5
      10 -1
      10 1 -1
      4 1 -1
      4 3 1 -1
      3 3 -1

      5
      10 -1 : 1번 강의는 10시간
      10 1 -1 : 2번 강의는 10시간, 선수강의 : 1번 강의
      4 1 -1 : 3번 강의는 4시간, 선수 강의 : 1번 강의
      4 3 1 -1 : 4번 강의는 4시간, 선수 강의 : 3번 강의, 1번 강의
      3 3 -1 : 5번 강의는 3시간, 선수 강의 : 3번 강의
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        v = sc.nextInt();

        // 그래프 초기화
        for (int i = 0; i <= v; i++) {
            graph.add(new ArrayList<Integer>());
        }

        // 방향 그래프의 모든 간선 정보를 입력 받기
        for (int i = 1; i <= v; i++) {
            int x = sc.nextInt(); // 강의 시간
            times[i] = x;

            // 해당 강의를 듣기 위해 먼저 들어야 하는 강의들의 번호 입력
            while (true) {
                x = sc.nextInt();

                if (x == -1) {
                    break;
                }

                indegree[i] += 1; /* 선수 강의가 입력된 만큼 진입차수 +1 */
                graph.get(x).add(i); /* x번째 리스트는 자신을 선수강의로 가진 진입노드를 리스트로 가지고있을 것 */
            }
        }

        topologySort();
    }
}
