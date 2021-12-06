package annmj.chapter6.replacementofelementsintwoarrays;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Solution {
    static FastReader scan = new FastReader();

    static int N, K;
    static Integer[] A, B;

    static void input() {
        N = scan.nextInt();
        K = scan.nextInt();
        A = new Integer[N];
        B = new Integer[N];

        for (int i = 0; i < N; i++) {
            A[i] = scan.nextInt();
        }

        for (int i = 0; i < N; i++) {
            B[i] = scan.nextInt();
        }
    }

    static void pro() {
        List<Integer> sortedA = Stream.of(A).sorted().collect(Collectors.toList());
        List<Integer> sortedB = Stream.of(B).sorted(Collections.reverseOrder()).collect(Collectors.toList());

        int sum = 0;

        for (int i = 0; i < K; i++) {
            int temp = sortedB.get(i);
            sortedB.set(i, sortedA.get(i));
            sortedA.set(i, temp);
        }

        for (Integer a : sortedA) {
            sum+= a;
        }

        System.out.println(sum);
    }

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

