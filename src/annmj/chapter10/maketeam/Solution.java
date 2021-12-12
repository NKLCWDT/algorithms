package annmj.chapter10.maketeam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static FastReader scan = new FastReader();

    static int N, M;

    static int[] teams;  /* index 는 학생 번호 , value 는 부모 ( 더 낮은 번호의 학생을 가리킴 ) */

    static void addTeam(int a, int b) {
        /*System.out.println("Solution.addTeam");*/
        /* union 연산 */
        if(a > b) {
            teams[a] = findTeam(b);
        } else {
            teams[b] = findTeam(a);
        }
    }

    static int findTeam(int a) {
        /*System.out.println("Solution.findTeam");*/
        /* a 가 속한 팀 찾기 */
        if (teams[a] == a) {
            return a;
        } else {
            teams[a] = findTeam(teams[a]);
        }

        return teams[a];
    }

    static void isSameTeam(int a, int b) {
        /*System.out.println("Solution.isSameTeam");*/
        if (teams[a] == teams[b]) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }

    static void input() {
        N = scan.nextInt();
        teams = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            teams[i] = i;
        }

        M = scan.nextInt();
        for (int i = 0; i < M; i++) {
            int operation = scan.nextInt();
            int a = scan.nextInt();
            int b = scan.nextInt();

            if (operation == 1) {
                process(a, b);
            } else {
                addTeam(a, b);
            }
        }
    }

    static void process(int a, int b) {
        isSameTeam(a, b);
    }

    public static void main(String[] args) {
        input();
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

