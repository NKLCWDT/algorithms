package annmj.thisiscodingtest.part03.Q34_soldier_deployment;

import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static List<Integer> A;

    public static void main(String[] args) {
        input();
        process();
    }

    private static void process() {
        // 기존 배열을 뒤집자
        Collections.reverse(A);
        int[] dp = new int[N];
        int maxLength = 1;
        Arrays.fill(dp, 1);

        // 최장 증가 부분 수열을 찾자
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < i; j++) {
                // i 번째 원소보다 작은 경우의 dp 값에 1을 더한값과 비교
                if (A.get(j) < A.get(i)) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            if (dp[i] > maxLength) {
                maxLength = dp[i];
            }
        }

        // 최장 증가 부분 수열을 뒤집으면 내림차순이 되고, 그게 우리가 구할 답이다.
        // 최장 증가 부분 수열의 길이를 구했으니 , 전체 배열의 길이 N 에서 이를 빼주면, 열외된 병사의 수가 나온다.
        System.out.println(N - maxLength);
    }

    private static void input() {
        FastReader scan = new FastReader();
        N = scan.nextInt();
        A = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            A.add(scan.nextInt());
        }
    }

    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        Integer nextInt() {
            return Integer.parseInt(next());
        }
    }
}
