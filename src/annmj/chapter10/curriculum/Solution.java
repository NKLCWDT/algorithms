package annmj.chapter10.curriculum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
    static FastReader scan = new FastReader();

    static int N; // 강의 수

    static int[] inBounds;
    static int[] times;
    static int[] totalTimes; // 정답을 출력할 배열

    static int[][] graphs;

    static void input() {
        N = scan.nextInt();
        inBounds = new int[N + 1]; // 강의 번호는 1부터 시작
        graphs = new int[N + 1][N + 1];
        times = new int[N + 1];
        totalTimes = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            String str = scan.nextLine();
            String[] inputs = str.split(" ");
            times[i] = Integer.parseInt(inputs[0]);
            int toNode = i; // 선수 과목 등록을 위한 변수
            int fromNode = 0; // 초기화

            for (int j = 1; j < inputs.length - 1; j++) {
                fromNode = Integer.parseInt(inputs[j]);
                if (graphs[fromNode][toNode] == 0) {
                    graphs[fromNode][toNode] = 1;
                    inBounds[toNode]++;
                    if (j + 1 <= inputs.length - 2) {
                        toNode = fromNode;
                        fromNode = Integer.parseInt(inputs[j + 1]);
                    }
                }
            }
        }
    }

    static void process() {

        Queue<Integer> queue = new LinkedList<>();
        // 큐 초기화 ( inBound 가 0 인 노드를 넣는다. )
        for (int i = 1; i <= N; i++) {
            totalTimes[i] += times[i];
            if (inBounds[i] == 0) {
                queue.add(i);
            }
        }

        // 큐가 빌 때까지 반복
        while (!queue.isEmpty()) {
            Integer course = queue.poll();
            for (int i = 1; i <= N; i++) {
                if (graphs[course][i] == 1) {
                    // course 에서 연결된 간선이 있다면
                    inBounds[i]--;
//                    totalTimes[i] += totalTimes[course]; // origin
                    totalTimes[i] = Math.max(totalTimes[course] + times[i], totalTimes[i]); // answer
                    if (inBounds[i] == 0) {
                        queue.add(i);
                    }
                }
            }
        }

        for (int i = 1; i < totalTimes.length; i++) {
            System.out.println(totalTimes[i]);
        }
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

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return str;
        }

        Integer nextInt() {
            return Integer.parseInt(next());
        }
    }
}
