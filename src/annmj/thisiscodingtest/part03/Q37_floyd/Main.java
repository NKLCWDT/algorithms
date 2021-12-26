package annmj.thisiscodingtest.part03.Q37_floyd;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        input();
        process();
    }

    static int N;
    static int M;
    static int[][] arr;

    static void input() {
        Scanner scanner = new Scanner(System.in);
        N = scanner.nextInt();
        M = scanner.nextInt();
        arr = new int[N + 1][N + 1];
        for (int i = 0; i <= N; i++) {
            Arrays.fill(arr[i], 10000001);
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (i == j) {
                    arr[i][j] = 0;
                }
            }
        }

        for (int i = 0; i < M; i++) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            int c = scanner.nextInt();
            arr[a][b] = Math.min(arr[a][b], c);
        }
    }

    static void process() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                for (int k = 1; k <= N; k++) {
                    arr[j][k] = Math.min(arr[j][k], arr[j][i] + arr[i][k]);
                }
            }
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if(arr[i][j] == 10000001) System.out.print(0 + " ");
                else System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }
}
