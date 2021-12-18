package annmj.thisiscodingtest.part02.chapter7.findingparts;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class Solution {
    static FastReader scan = new FastReader();

    static int N,M;
    static int[] arrayN, arrayM;
    static void input() {
        N = scan.nextInt();
        arrayN = new int[N];
        for (int i = 0; i < N; i++) {
            arrayN[i] = scan.nextInt();
        }
        M = scan.nextInt();
        arrayM = new int[M];
        for (int i = 0; i < M; i++) {
            arrayM[i] = scan.nextInt();
        }
    }

    static void process() {
        Arrays.sort(arrayN); // 오름차순 정렬 되어 있어야 한다.
        List<Boolean> yesOrNo = new LinkedList<>();
        for (int i = 0; i < M; i++) {
            yesOrNo.add(myBinarySearch(arrayM[i]));
        }
        List<String> answer = yesOrNo.stream().map(v -> v ? "yes" : "no").collect(Collectors.toList());
        for (String ans : answer) {
            System.out.print(ans + ' ');
        }
    }

    private static boolean myBinarySearch(int target) {
        int start = 0;
        int end = arrayN.length;
        int mid = (start + end) / 2;
        boolean result = false;
        while (start <= end) {
            int midValue = arrayN[mid];
            if(target == midValue) {
                result = true;
                break;
            } else if(target < midValue) {
                end = mid - 1;
                mid = (start + end) / 2;
            } else {
                start = mid + 1;
                mid = (start + end) / 2;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        input();
        process();
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

