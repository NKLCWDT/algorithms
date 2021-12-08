package seunguk.chapter8.makeitone;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int X = sc.nextInt();

        // X에 값이 배열에 들어가야 하므로 +1
        int[] arr = new int[X+1];

        for (int i = 2; i < X+1; i++) {

            arr[i] = arr[i-1] + 1;

            // 현재값에 1을빼는 경우를 넣고 현재 값과 비교하여 더 작을 경우 갱신해줌
            if (i % 2 == 0) {
                arr[i] = Math.min(arr[i], arr[i/2] + 1);
            }
            if (i % 2 == 0) {
                arr[i] = Math.min(arr[i], arr[i/3] + 1);
            }
            if (i % 5 == 0) {
                arr[i] = Math.min(arr[i], arr[i/5] + 1);
            }
        }

        System.out.println(arr[X]);

    }
}
