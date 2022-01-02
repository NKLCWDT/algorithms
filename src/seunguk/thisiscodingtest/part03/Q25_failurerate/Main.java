package seunguk.thisiscodingtest.part03.Q25_failurerate;

import java.util.*;

// 못풀음, 다시 풀어보기

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

        Arrays.sort(stages); // 정렬 시켜서 작은 스테이지 부터 확인
        float count = 0;
        int index = 0; // stages 인덱스
        float focus = 0; // 이미 계산된 갯수 저장
        int stg = 1; // 게임 스테이지
        while (true) {
            if (index == N+1) { // 마지막 인덱스 까지 탐색 했을 시 종료
                break;
            }
            if (stg == stages[index]) { // 현재 스테이지와 stages 배열 값이 같다면
                count++; // 현재 스테이지 클리어 못한 수 증가
                index++; // 다음 인덱스 탐색
            } else if (count == 0 && stg != stages[index]) { // count가 0인 경우에 stg와 stages 배열값과 다르다면
                result.add(new Node(stg, 0)); // 결과 리스트에 추가 실패율 0으로 추가
                stg++;
            } else {
                float failure = count / (stages.length - focus); // 실패율 계산
                result.add(new Node(stg, failure)); // 결과 리스트에 실패율 추가
                stg++;
                index++;
                focus = count; // 계산된 값 저장

            }

        }
        // 마지막 스테이지 계산
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
