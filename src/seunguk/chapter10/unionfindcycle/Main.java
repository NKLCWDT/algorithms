package seunguk.chapter10.unionfindcycle;

import java.util.Scanner;

public class Main {
    static int[] parent;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int V = sc.nextInt();
        int E = sc.nextInt();
        parent = new int[V+1];

        // 부모 테이블상에서, 부모를 자기 자신으로 초기화
        for (int i = 0; i < V + 1; i++) {
            parent[i] = i;
        }

        // 사이클 발생 여부
        boolean cycle = false;

        for (int i = 0; i < E; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            // 사이클이 발생한 경우 종료
            if (find(a) == find(b)) {
                cycle = true;
                break;
            } else {
                union(a, b);
            }
        }
        if (cycle) {
            System.out.println("사이클이 발생했습니다.");
        } else {
            System.out.println("사이클이 발생하지 않았습니다.");
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
3 3
1 2
1 3
2 3
*/
