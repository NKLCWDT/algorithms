package jihye.thisiscodingtest.part03.Q25_failureRate;

import java.util.*;

public class Solution {
    public static void main(String[] args) {

//        int N = 5;
//        int[] stages = new int[]{2, 1, 2, 6, 2, 4, 3, 3};
//
//        ArrayList<int[]> stage = new ArrayList<>();
//
//
//        for(int i=0; i < N; i++){
//            int[] initialize = new int[2];
//            initialize[0] = i;
//            stage.add(initialize);
//        }
//
//        for(int i=0; i < stages.length; i++){
//            if(stages[i] < N){
//                int [] temp = stage.get(stages[i]-1);
//                temp[1]++;
//                temp[1] /= N;
//                stage.set(temp[0], temp);
//            }
//
//        }
//
//        Collections.sort(stage, Comparator.comparing(a -> a[1]));
//
//        for(int i=N-1; i >=0 ; i--){
//            System.out.println(stage.get(i)[0]+1);
//        }
    }

        public int[] solution(int N, int[] stages) {
            int[] answer = new int[N];
            Map<Integer, Double> failure = new HashMap<Integer, Double>();
            int[] success = new int[N];
            int[] fail = new int[N];
            for (int i = 0; i < stages.length; i++) {
                //성공횟수를 기록
                for (int j = 0; j < stages[i] - 1; j++) {
                    success[j]++;
                }
                //범위를 넘어간다면 pass
                if (N < stages[i]) {
                    break;
                } else {
                    //실패 횟수를 기록
                    fail[stages[i] - 1]++;
                }
            }
            for (int i = 1; i <= N; i++) {
                //실패율을 저장한다.
                failure.put(i, (double) fail[i - 1] / success[i - 1]);
            }
            for (int i = 0; i < N; i++) {
                //가장 실패율이 큰것을 maxKey
                Integer maxKey = Collections.max(failure.keySet());
                answer[i] = maxKey;//answer에 실패율이 큰순서로 기록
                failure.remove(maxKey);//failure에서 없에준다.
            }
            return answer;
        }

}
