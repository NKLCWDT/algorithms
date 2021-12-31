package annmj.thisiscodingtest.part03.Q25_fail;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        int N = 5;
        int[] arr = {2, 1, 2, 6, 2, 4, 3, 3};

        int[] answer = solution(N, arr);
        for (int i = 0; i < answer.length; i++) {
            System.out.println(answer[i]);
        }
    }

    public static int[] solution(int N, int[] stages) {
        int[] answer = new int[N];
        Map<Integer, Double> map = new HashMap<>();

        for (int i = 1; i <= N; i++) {
            double value;
            int notClear = 0;
            int chalenge = 0;
            for (int j = 0; j < stages.length; j++) {
                if(stages[j] < i) continue;

                chalenge++;

                if(stages[j] == i){
                    notClear++;
                }
            }
            value = (double) notClear / chalenge;
            map.put(i, value);
        }

        List<Map.Entry<Integer, Double>> entryList = new LinkedList<>(map.entrySet());
        entryList.sort(new Comparator<Map.Entry<Integer, Double>>() {
            @Override
            public int compare(Map.Entry<Integer, Double> o1, Map.Entry<Integer, Double> o2) {
                if (o2.getValue() - o1.getValue() > 0) {
                    return 1;
                } else if (o2.getValue() - o1.getValue() < 0) {
                    return -1;
                } else {
                    return o1.getKey() - o2.getKey();
                }
            }
        });
        for (int i = 0; i < entryList.size(); i++) {
            answer[i] = entryList.get(i).getKey();
        }
        return answer;
    }
}
