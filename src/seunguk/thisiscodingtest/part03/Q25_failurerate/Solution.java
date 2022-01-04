package seunguk.thisiscodingtest.part03.Q25_failurerate;

import java.util.ArrayList;
import java.util.Collections;

public class Solution {
    static ArrayList<Node> failList = new ArrayList<>();

    static class Node implements Comparable<Node>{
        int node;
        double failure;

        Node(int node, double failure) {
            this.node = node;
            this.failure = failure;
        }

        @Override
        public int compareTo(Node o) {
            return Double.compare(o.failure, this.failure); // failure로 내림차순
        }
    }

    public static void main(String[] args) {
        int N = 5;
        int[] stages = {2,1,2,6,2,4,3,3};
//        int N = 4;
//        int[] stages = {4,4,4,4,4};
        int[] answer = solution(N, stages);
    }

    private static int[] solution(int N, int[] stages) {

        int challenge = stages.length;
        for (int i = 1; i < N+1; i++) {
            double fail = 0;
            for (int j = 0; j < stages.length; j++) {
                if (i == stages[j]) {
                    fail++;
                }
            }
            double failure = 0;
            if (challenge != 0) {
                failure = fail / challenge;
            }
            failList.add(new Node(i, failure));
            challenge -= fail;

        }

        Collections.sort(failList);

        int[] answer = new int[failList.size()];

        for (int i = 0; i < failList.size(); i++) {
            System.out.println(failList.get(i).failure);
        }

        for (int i = 0; i < failList.size(); i++) {
            answer[i] = failList.get(i).node;
        }

        return answer;



    }
}
