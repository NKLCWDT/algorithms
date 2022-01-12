package jihye.thisiscodingtest.part03.Q34_soldiers;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        sc.nextLine();
        int[] level = Arrays.stream(sc.nextLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();

        int dp[] = new int[N];


        for (int i = 0; i < N; i++) {
            dp[i] = 1;

            for (int j = 0; j < i; j++) {
                //j번째 원소가 i번째 원소보다 큰 경우(i번째 이전의 숫자가 큰 경우)
                //i번째 dp가 j번째 dp + 1 보다 작을 경우(i번째 이전의 dp가 i번째 dp보다 큰 경우)
                if (level[j] > level[i] && dp[i] < dp[j] + 1) {
                    dp[i] = dp[j] + 1;
                }
            }
        }

        int max = -1;

        //가장 긴 경우를 구해야됨
        for (int i = 0; i < N; i++) {
            max = Math.max(dp[i], max);
        }

        //제외되는 병사수 출력
        System.out.println(N-max);
    }
}
