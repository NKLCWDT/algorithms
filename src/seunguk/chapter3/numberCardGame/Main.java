package seunguk.chapter3.numberCardGame;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();

        int[][] arr = new int[N][M];
        int[] minArr = new int[N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                arr[i][j] = sc.nextInt();
            }
            Arrays.sort(arr[i]);
            minArr[i] = arr[i][0];

        }
        Arrays.sort(minArr);

        System.out.println(minArr[minArr.length-1]);


    }
}
