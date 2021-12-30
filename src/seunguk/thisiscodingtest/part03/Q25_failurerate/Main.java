package seunguk.thisiscodingtest.part03.Q25_failurerate;

import java.util.*;

// 코드 개망 다시하자

public class Main {
    static class Node implements Comparable<Node>{
        int index;
        float fail;

        Node(int index, float fail) {
            this.index = index;
            this.fail = fail;
        }

        @Override
        public int compareTo(Node o) {
            return Float.compare(o.fail, this.fail);
        }
    }
    public static void main(String[] args) {
        int N = 4;
        int[] stages = {4,4,4,4,4};
        int [] answer = solution(N, stages);

        for (int i = 0; i < answer.length; i++) {
            System.out.println(answer[i]);
        }
    }

    public static int[] solution(int N, int[] stages) {
        ArrayList<Node> result = new ArrayList();

        Arrays.sort(stages);
        float count = 0;
        int index = 0;
        float focus = 0;
        int stg = 1;
        while (true) {
            if (index == N+1) {
                break;
            }
            if (stg == stages[index]) {
                count++;
                index++;
            } else if (count == 0 && stg != stages[index]) {
                result.add(new Node(stg, 0));
                stg++;
            } else {
                float failure = count / (stages.length - index);
                result.add(new Node(stg, failure));
                stg++;
                index++;
                focus = count;

            }

        }
        if (count != 0) {
            float failure = count / (stages.length - focus);
            result.add(new Node(stg, failure));
        }


        for (int i = 0; i < result.size(); i++) {
            System.out.println(result.get(i).fail);
        }

        Collections.sort(result);



        int[] answer = new int[result.size()];

        for (int i = 0; i < result.size(); i++) {
            answer[i] = result.get(i).index;
        }

        return answer;
    }
}
