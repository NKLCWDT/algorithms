package seunguk.thisiscodingtest.part02.chapter4.leftrightupdown;

import java.util.Scanner;

public class Main {
    // 상하좌우
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static int startX = 1;
    static int startY = 1;
    static int N;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        sc.nextLine();
        String s = sc.nextLine();

        String[] arr = s.split(" ");

        for (String s1 : arr) {
            if (s1.equals("U")) {
                move(0);
            } else if (s1.equals("D")) {
                move(1);
            } else if (s1.equals("L")) {
                move(2);
            } else {
                move(3);
            }
        }
        System.out.println(startX + " " + startY);
    }
    private static void move(int d) {
        int nx = startX + dx[d];
        int ny = startY + dy[d];
        if (nx < 1 || ny < 1 || startX > N || startY > N) {
            return;
        }
        startX = nx;
        startY = ny;
    }
}
