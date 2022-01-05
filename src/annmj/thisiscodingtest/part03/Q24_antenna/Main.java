package annmj.thisiscodingtest.part03.Q24_antenna;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        input();
        process();
    }

    static int N;
    static int[] A;
    private static void process() {
        Arrays.sort(A);
        if (N % 2 == 0) {
            System.out.println(A[N / 2 - 1]);
        } else {
            System.out.println(A[N/2]);
        }
    }

    private static void input() {
        Scanner scanner = new Scanner(System.in);
        N = scanner.nextInt();
        A = new int[N];
        for (int i = 0; i < N; i++) {
            A[i] = scanner.nextInt();
        }
    }
}
