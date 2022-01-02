package seohae.thiscodingtest.part03.Q25_FailureRate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
  실패율 구하기
  - 스테이지에 도달했으나 아직 클리어하지 못한 플레이어의 수 / 스테이지에 도달한 플레이어의 수

  스테이지의 전체 개수 N
  게임 이용하는 사용자가 현재 멈춰있는 스테이지의 번호가 담긴 배열 stages 가 매개변수로 주어진다.
  실패율이 높은 스테이지부터 내림차순으로 스테이지의 번호가 담겨있는 배열을 리턴하라.


  2 1 2 5 2 4 3 3
  1번에 멈춰있는 사용자 : 1 - 총 8명
  2번에 멈춰있는 사용자 : 3 - 총 7명 ( 8 - 1 )
  3번에 멈춰있는 사용자 : 2 - 총 4명 ( 7 - 3 )
  4번에 멈춰있는 사용자 : 1 - 총 2명 ( 4 - 2 )
  6번에 멈춰있는 사용자 : 1 - 총 1명 ( 2 - 1 )

  https://programmers.co.kr/learn/courses/30/lessons/42889
 */
public class Main {
    private static PriorityQueue<Node> queue = new PriorityQueue<>();

    static class Node implements Comparable<Node> {
        double failureRate;
        int stageNum;

        Node (double failureRate, int stageNum) {
            this.failureRate = failureRate;
            this.stageNum = stageNum;
        }

        @Override
        public int compareTo(Node o) {
            if (this.failureRate > o.failureRate) {
                return -1;
            } else if (this.failureRate < o.failureRate) {
                return 1;
            } else {
                if (this.stageNum > o.stageNum) {
                    return 1;
                } else {
                    return -1;
                }
            }
        }
    }

    public static void main(String[] args) {
        Main main = new Main();
        //System.out.println(Arrays.toString(main.solution(5, new int[]{2, 1, 2, 6, 2, 4, 3, 3})));
        //System.out.println(Arrays.toString(main.solution(4, new int[]{4, 4, 4, 4, 4})));
        // System.out.println(Arrays.toString(main.solution(1, new int[]{2})));
        //System.out.println(Arrays.toString(main.solution(5, new int[]{4, 4, 4, 4, 4})));
        System.out.println(Arrays.toString(main.solution(7, new int[]{1, 2, 3, 4, 5, 6})));
    }

    public int[] solution(int N, int[] stages) {
        int userCnt = stages.length; // 8

        /*
          stages 에서 클리어하지못한 사용자수 구하기
         */
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 1; i <= N; i++) {
            map.put(i, 0);
        }

        for (int stage : stages) {
            if (N < stage) {
                continue;
            }

            map.put(stage, map.get(stage) + 1);
        }

        /*
          1번에 멈춰있는 사용자 : 1 - 총 8명
          2번에 멈춰있는 사용자 : 3 - 총 7명 ( 8 - 1 )
          3번에 멈춰있는 사용자 : 2 - 총 4명 ( 7 - 3 )
          4번에 멈춰있는 사용자 : 1 - 총 2명 ( 4 - 2 )
          6번에 멈춰있는 사용자 : 1 - 총 1명 ( 2 - 1 )
         */
        int cnt = userCnt;
        for (int i = 1; i <= N; i++) {
            int stoppedUser = map.get(i);

            int finishedUser = cnt;

            double failureRate = getFailureRate(stoppedUser, finishedUser);
            queue.add(new Node(failureRate, i));

            cnt = cnt - map.get(i);
        }

        int[] answer = new int[N]; //결과
        IntStream.range(0, answer.length).forEach(i -> {
            answer[i] = queue.peek().stageNum;
            queue.remove();
        });

        return answer;
    }

    public double getFailureRate(int stoppedUser, int finishedUser) {
        // 스테이지에 도달했으나 아직 클리어하지 못한 플레이어의 수 / 스테이지에 도달한 플레이어 수
        return ((double) stoppedUser) / finishedUser;
    }
}