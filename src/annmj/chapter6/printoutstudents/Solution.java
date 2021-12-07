package annmj.chapter6.printoutstudents;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
    static FastReader scan = new FastReader();

    static int N;
    static Map<String, Integer> map = new HashMap<>();
    static void input() {
        N = scan.nextInt();
        for (int i = 0; i < N; i++) {
            String name = scan.next();
            Integer grade = scan.nextInt();
            map.put(name, grade);
        }
    }

    static void pro() {
        StringBuilder sb = new StringBuilder();
        List<Map.Entry<String, Integer>> entryList = new LinkedList<>(map.entrySet());
        entryList.sort((o1, o2) -> o1.getValue() - o2.getValue()); // 양수이면 두 값이 교체가 된다.

        for (Map.Entry<String, Integer> entry : entryList) {
            sb.append(entry.getKey()).append(' ');
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
