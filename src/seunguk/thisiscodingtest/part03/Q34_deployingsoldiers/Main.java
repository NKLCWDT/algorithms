package seunguk.thisiscodingtest.part03.Q34_deployingsoldiers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

// dp LIS 문제 구현 방법이 생각이안나 답지참고
// 범위가 작아 O(N^2) 풀이

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        List<Integer> arr = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            arr.add(sc.nextInt());
        }
        // 내림차순을 오름차순으로 바꿔 LIS 방식으로 푼다.
        Collections.reverse(arr);
        
        int[] dp = new int[N];
        for (int i = 0; i < N; i++) {
            dp[i] = 1; // 처음 값 1로 초기화
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < i; j++) {
                if (arr.get(j) < arr.get(i)) { // arr[i] 값이 비교값보다 크다면 dp값 갱신가능
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        
        int result = 0;
        for (int i = 0; i < N; i++) {
            result = Math.max(result, dp[i]);
        }
        System.out.println(N-result);

        
    }
}
