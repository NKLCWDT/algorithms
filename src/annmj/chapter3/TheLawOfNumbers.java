package annmj.chapter3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class TheLawOfNumbers {

    static int N, M, K;
    static int[] arr;
    static int answer;

    static void input() {
        N = scan.nextInt();
        M = scan.nextInt();
        K = scan.nextInt();
        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = scan.nextInt();
        }
    }

    static void pro() {
        Arrays.sort(arr);
        int maxValueCount = (M / K) * K;
        int secondMaxValueCount = M - maxValueCount;
        answer += arr[arr.length - 1] * maxValueCount + arr[arr.length - 2] * secondMaxValueCount;
        System.out.println(answer);
    }

    static FastReader scan = new FastReader();

    public static void main(String[] args) {
        input();
        pro();
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
