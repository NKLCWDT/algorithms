package seohae.thiscodingtest.part03.Q04_CantMakeAmount;

import java.util.Arrays;
import java.util.OptionalInt;
import java.util.Scanner;

/*
 동빈이는 N개의 동전을 가지고 있다.
 N개의 동전을 이용하여 만들 수 없는 양의 정수 금액 중 최솟값을 구하라

 N = 5
 3원, 2원, 1원, 1원, 9원 일때
 만들 수 없는 최솟값은 8원
 */
public class Main {
    private static int N;
    private static int[] coin;

    public static void main(String[] args) {
        input();

        int target = 1;

        /*
           coin : 1 1 2 3 9
           target : 1
           target : 2 (+ 1)
           target : 3 (+ 1)
           target : 5 (+ 2)
           target : 8 (+ 3)
           -> + 9 여야하는데 8보다 9가 크므로 break

           동전의 정보가 target 보다 크면 target까지가 만들 수 있는 금액이다.
         */
        for (int i = 0; i < N; i++) {
            if (target < coin[i]) {
                break;
            }
            target += coin[i];
        }

        System.out.println(target);
    }

//    public static void main(String[] args) {
//        input();
//
//        /*
//          1 -> 1
//          2 -> 2, 1 + 1
//          3 -> 2 + 1
//          4 -> 2 + 1 + 1, 3 + 1
//          5 -> 2 + 3
//          6 -> 3 + 2 + 1
//          7 -> 3 + 2 + 1 + 1
//          8 -> 3 + 2 + 1 + 1 + 9 (불가)
//         */
//
//        int num = 1;
//        boolean isNotMake = true;
//
//        while (true) {
//            int sum = 0;
//            for (int i = 0; i < N; i++) {
//                int target = coin[i];
//
//                if (num < target) {
//                    break;
//                }
//
//                if (num < sum) {
//                    isNotMake = true;
//                    break;
//                }
//
//                sum = sum + target;
//
//                if (sum == num) {
//                    isNotMake = false;
//                    break;
//                }
//            }
//
//            if (isNotMake) {
//                System.out.println(num);
//                break;
//            }
//
//            num++;
//        }
//    }

    /*
       5
       3 2 1 1 9
     */
    private static void input() {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        coin = new int[N];

        for (int i = 0; i < N; i++) {
            coin[i] = sc.nextInt();
        }

        Arrays.sort(coin);
    }
}
