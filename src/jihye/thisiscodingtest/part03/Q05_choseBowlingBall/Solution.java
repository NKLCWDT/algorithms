package jihye.thisiscodingtest.part03.Q05_choseBowlingBall;

import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int weight[] = new int[N];

        for (int i = 0; i < weight.length; i++) {
            weight[i] = sc.nextInt();
        }

        int count = 0;
        for (int i = 0; i < N - 1; i++) {
            for (int j = i + 1; j < N; j++) {
                if (weight[i] != weight[j]) {
                    count++;
                }
            }
        }
        System.out.println(count);

    }
}
