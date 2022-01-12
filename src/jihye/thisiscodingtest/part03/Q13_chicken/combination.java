package jihye.thisiscodingtest.part03.Q13_chicken;

import java.util.Arrays;

public class combination {
    static int backtracking = 0;
    static int recurrsion = 0;

    public static void main(String[] args) {
        int n = 4;
        int[] arr = {1, 2, 3, 4};
        boolean[] visited = new boolean[n];
//           for (int i = 1; i <= n; i++) {
//               System.out.println("\n" + n + " 개 중에서 " + i + " 개 뽑기");
        comb(arr, visited, 0, n, 2);
        System.out.println(recurrsion);
//           }
//        for (int i = 1; i <= n; i++) {
//            System.out.println("\n" + n + " 개 중에서 " + i + " 개 뽑기");
        combination(arr, visited, 0, n, 2);
        System.out.println(backtracking);
//        }
    }

    // 백트래킹 사용
    // 사용 예시 : combination(arr, visited, 0, n, r)
    static void combination(int[] arr, boolean[] visited, int start, int n, int r) {
        backtracking++;
        if (r == 0) {
            print(arr, visited, n);
            return;
        }
        for (int i = start; i < n; i++) {
            visited[i] = true;
            System.out.println("backtracking" + Arrays.toString(visited));
            combination(arr, visited, i + 1, n, r - 1);
            visited[i] = false;
        }
    }

    // 재귀 사용
    // 사용 예시 : comb(arr, visited, 0, n, r)
    static void comb(int[] arr, boolean[] visited, int depth, int n, int r) {
        recurrsion++;
        if (r == 0) {
            print(arr, visited, n);
            return;
        }
        if (depth == n) {
            return;
        }
        visited[depth] = true;
        System.out.println("recursion" + Arrays.toString(visited));
        comb(arr, visited, depth + 1, n, r - 1);
        visited[depth] = false;
        System.out.println("recursion" + Arrays.toString(visited));
        comb(arr, visited, depth + 1, n, r);
    }

    static int[][] dp = new int[30][30];//적당한 크기

    //리턴주목 : 배열에 넣어준다.
    static int combin(int n, int r) {
        if (dp[n][r] > 0) {// 메모이제이션
            return dp[n][r];
        }
        if (r == 0 || n == r) {
            return dp[n][r] = 1;
        }

        return dp[n][r] = combin(n - 1, r - 1) + combin(n - 1, r);

    }

    // 배열 출력
    static void print(int[] arr, boolean[] visited, int n) {
        for (int i = 0; i < n; i++) {
            if (visited[i]) {
                System.out.print(arr[i] + " ");
            }
        }
        System.out.println();
    }
}
