package annmj.chapter4.aloyalnight;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Solution {
    static FastReader scan = new FastReader();

    static String input = "";
    static int startX, startY;
    static Map<Character, Integer> map = new HashMap<>();
    static void input() {
        input = scan.next();
        map.put('a', 1);
        map.put('b', 2);
        map.put('c', 3);
        map.put('d', 4);
        map.put('e', 5);
        map.put('f', 6);
        map.put('g', 7);
        map.put('h', 8);
        startX = map.get(input.charAt(0));
        startY = Integer.parseInt(String.valueOf(input.charAt(1)));
    }

    static void pro() {
        int cnt = 0;
        // 1. 수평으로 두 칸 이동한 뒤에 수직으로 한 칸
        // 2. 수직으로 두 칸 이동한 뒤에 수평으로 한 칸
        int[][] caseArray = {{-2, -1},{-2,1} , {2,-1} , {2,1}, {-1,-2} , {-1,2} , {1,-2} , {1,2}};
        for (int[] ints : caseArray) {
            int nx = startX + ints[0];
            int ny = startY + ints[1];
            if (nx > 0 && ny > 0 && nx < 9 && ny < 9) {
                cnt++;
            }
        }

        System.out.println(cnt);
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
