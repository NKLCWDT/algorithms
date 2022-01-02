package jungho.thisiscodingtest.part03.Q25_failurerate;

import java.util.*;

// 프로그래머스 제출용
public class Solution {
    
    static class GameStage {

        private int stage;

        private double failureRate;

        public GameStage(int stage, double failureRate) {
            this.stage = stage;
            this.failureRate = failureRate;
        }

        public int getStage() {
            return stage;
        }

        public double getFailureRate() {
            return failureRate;
        }

        public void changeFailureRate(double failureRate) {
            this.failureRate = failureRate;
        }
    }

    private static GameStage[] storages;

    public int[] solution(int N, int[] stages) {
        Arrays.sort(stages);
        initStorages(N);

        int reachedPlayerCount = stages.length;
        int notClearPlayerCount = 1;
        int stageNumber = stages[0];
        for (int i = 1; i < stages.length; i++) {
            if(stages[i] == stageNumber) {
                notClearPlayerCount++;
            } else {
                // 실패율 변경
                changeFailureRate(notClearPlayerCount, reachedPlayerCount, stageNumber);
                // stageNumber 값 변경
                stageNumber = stages[i];
                // 도달한 플레이어의 수 재 계산
                reachedPlayerCount -= notClearPlayerCount;
                // 클리어 하지 못한 플레이어의 수 초기화
                notClearPlayerCount = 1;
            }
            // 한 숫자가 연속적으로 마지막 인덱스 까지 이어질때 계산하기 위함
            // Ex. 4,4,4,4,4 or 1,2,3,3,3
            if(i == stages.length - 1 && stageNumber <= N) {
                changeFailureRate(notClearPlayerCount, reachedPlayerCount, stageNumber);
            }
        }

        List<GameStage> gameStages = Arrays.asList(storages);
        gameStages.sort((o1, o2) -> {
            if(o2.getFailureRate() == o1.getFailureRate()) {
                return o1.getStage() - o2.getStage();
            }
            return o2.getFailureRate() > o1.getFailureRate() ? 1 : -1;
        });

        int[] answer = new int[gameStages.size()];
        for (int i = 0; i < gameStages.size(); i++) {
            GameStage gameStage = gameStages.get(i);
            answer[i] = gameStage.getStage();
        }

        return answer;
    }

    /**
     * GameStage 를 저장하는 자료구조를 초기화하고, 실패율을 0으로 초기화
     * @param N
     */
    public void initStorages(int N) {
        storages = new GameStage[N];
        for (int i = 0; i < N; i++) {
            storages[i] = new GameStage(i + 1, 0.0);
        }
    }

    /**
     * 실패율 변경
     * @param notClearPlayerCount 스테이지에 도달 했으나 클리어 하지 못한 플레이어의 수
     * @param reachedPlayerCount 스테이지에 도달한 플레이어의 수
     * @param stageNumber 스테이지 번호
     */
    public void changeFailureRate(int notClearPlayerCount, int reachedPlayerCount, int stageNumber) {
        double failureRate = (double) notClearPlayerCount / reachedPlayerCount;
        GameStage gameStage = storages[stageNumber - 1];
        gameStage.changeFailureRate(failureRate);
    }
}
