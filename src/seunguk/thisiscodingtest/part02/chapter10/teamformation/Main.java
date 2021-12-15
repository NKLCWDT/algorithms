package seunguk.thisiscodingtest.part02.chapter10.teamformation;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static int[] parent;
    public static void main(String[] args) {
        ArrayList<String> result = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        parent = new int[N+1];

        // 부모 테이블상에서, 부모를 자기 자신으로 초기화
        for (int i = 0; i < N + 1; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < M; i++) {
            int check = sc.nextInt();
            int a = sc.nextInt();
            int b = sc.nextInt();

            if (check == 0) {
                union(a,b);
            } else {
                if (find(a) == find(b)) {
                    result.add("YES");
                } else {
                    result.add("NO");
                }
            }
        }

        for (String s : result) {
            System.out.println(s);
        }
    }
    private static void union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x != y) {
            parent[Math.max(x,y)] = Math.min(x,y);
        }
    }
    private static int find(int x) {
        if (x != parent[x]) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }
}

/*
7 8
0 1 3
1 1 7
0 7 6
1 7 1
0 3 7
0 4 2
0 1 1
1 1 1
*/
