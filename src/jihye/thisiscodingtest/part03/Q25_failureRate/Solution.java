package jihye.thisiscodingtest.part03.Q25_failureRate;

import java.util.*;
import java.util.Map.Entry;

public class Solution {
    public static void main(String[] args) {
        int N = 5;
        int[] stages = {2, 1, 2, 6, 2, 4, 3, 3};
        solution(N, stages);


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

    public static int[] solution(int N, int[] stages) {
        int[] answer = new int[N];
        Map<Integer, Double> failure = new HashMap<Integer, Double>();
        int[] success = new int[N];
        int[] fail = new int[N];


        for (int i = 0; i < stages.length; i++) {
            //성공횟수를 기록
            for (int j = 0; j <= stages[i] - 1; j++) {
                if (j < N) {//단 N보다 큰 수가 들어왔을때를 제외하고
//                    System.out.println(i+" "+j);
                    success[j]++;
                }

            }
            //범위를 넘어간다면 pass
            if (N < stages[i]) {
                continue;
            } else {
                //실패 횟수를 기록
                fail[stages[i] - 1]++;
            }


        }
        for (int i = 1; i <= N; i++) {
            //실패율을 저장한다.
//            System.out.println(fail[i - 1] + "   " + success[i - 1]);
            if (success[i - 1] != 0) {
                failure.put(i, ((double) fail[i - 1] / success[i - 1]));
            } else {//하지만 도전한 사람이 없어 실패율도 없다면 0.0 넣어준다.
                failure.put(i, 0.0);
            }

        }

        // Map.Entry 리스트 작성
        List<Entry<Integer, Double>> entryList = new ArrayList<Entry<Integer, Double>>(failure.entrySet());

        // value로 descending순으로 정렬
        entryList.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));


        int i = 0;
        for (Entry<Integer, Double> entry : entryList) {
            answer[i] = entry.getKey();
            i++;
        }

        return answer;
    }

}
