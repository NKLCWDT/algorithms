package annmj.thisiscodingtest.part03.Q05_bowlingballpick;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        input();
        process();
    }

    static int N;
    static int M;
    static ArrayList<Integer>[] balls; // 무게별로 가지고 있음
    static void input() {
        Scanner scan = new Scanner(System.in);
        N = scan.nextInt(); // 공 갯수
        M = scan.nextInt(); // 최대 무게
        balls = new ArrayList[M+1];
        for (int i = 0; i <= M; i++) {
            balls[i] = new ArrayList<>();
        }
        for (int i = 0; i < N; i++) {
            int ball = scan.nextInt();
            balls[ball].add(ball);
        }
    }

    static void process() {
        int count = 0;
        for (int i = 0; i < balls.length; i++) {
            for (int ball : balls[i]) {
                for (int j = ball+1; j <= M; j++) {
                    count += balls[j].size();
                }
            }
        }
        System.out.println(count);

    }
}
