package seunguk.thisiscodingtest.part03.Q13_chickendelivery;

// 백트레킹 답지 참고

import java.util.ArrayList;
import java.util.Scanner;

public class Main2 {
    static int N;
    static int M;
    static int[][] arr;
    static ArrayList<Node> person;
    static ArrayList<Node> chicken;
    static boolean[] check;
    static int ans;

    static class Node{
        int x;
        int y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        arr = new int[N][N];
        person = new ArrayList<>();
        chicken = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                arr[i][j] = sc.nextInt();
                if (arr[i][j] == 1) {
                    person.add(new Node(i,j));
                } else if (arr[i][j] == 2) {
                    chicken.add(new Node(i,j));
                }
            }
        }
        ans = Integer.MAX_VALUE;
        check = new boolean[chicken.size()];
        DFS(0,0);
        System.out.println(ans);
    }
    private static void DFS(int start, int cnt) {
        if (cnt == M) {
            int res = 0;
            for (int i = 0; i < person.size(); i++) {
                int temp = Integer.MAX_VALUE;
                for (int j = 0; j < chicken.size(); j++) {
                    if (check[j]) {
                        int distance = Math.abs(person.get(i).x - chicken.get(j).x)
                                + Math.abs(person.get(i).y - chicken.get(j).y);

                        temp = Math.min(temp, distance); // 집과 치킨 거리중 가장 작은거리 구한다.
                    }
                }
                res += temp; // 치킨 거리값 구한다.
            }
            ans = Math.min(ans, res); // 치킨집 개수를 바꿔가면서 가장 작은 치킨거리를 구한다.
            return;
        }
        for (int i = start; i < chicken.size(); i++) {
            check[i] = true;
            DFS(i+1, cnt+1);
            check[i] = false;
        }
    }

}
