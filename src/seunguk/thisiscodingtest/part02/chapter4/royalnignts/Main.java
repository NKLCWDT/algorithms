package seunguk.thisiscodingtest.part02.chapter4.royalnignts;

import java.util.Scanner;

public class Main {
    static int[] dx = {-2, -1, 1, 2, 2, 1, -1, -2};
    static int[] dy = {-1, -2, -2, -1, 1, 2, 2, 1};
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        int nx;
        int ny;
        int count = 0;
        int row = s.charAt(1) - '0';
        int col = s.charAt(0) - 'a' + 1;

        for (int i = 0; i < 8; i++) {
            nx = row + dx[i];
            ny = col + dy[i];
            if (nx >= 1 && ny >= 1 && nx <= 8 && ny <= 8) {
                count++;
            }
        }
        System.out.println(count);
    }
}
