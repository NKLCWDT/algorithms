package annmj.chapter6.fromtoptobottom;

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

    static Integer[] arr;
    static void input() {
        int size = scan.nextInt();
        arr = new Integer[size];

        for (int i = 0; i < size; i++) {
            arr[i] = scan.nextInt();
        }
    }

    static void pro() {
        StringBuilder sb = new StringBuilder();
        List<Integer> answer = Stream.of(arr).sorted(Collections.reverseOrder()).collect(Collectors.toList());

        for (Integer integer : answer) {
            sb.append(integer).append(' ');
        }

        System.out.println(sb);
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
