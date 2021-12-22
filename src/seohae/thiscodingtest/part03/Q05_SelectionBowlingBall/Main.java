package seohae.thiscodingtest.part03.Q05_SelectionBowlingBall;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
A , B
두사람은 서로 다른 볼링공을 골라야한다.
볼링공은 총 N개

각 볼링공마다 무게가 있고, 공의 번호는 1번부터 N까지

예시)
N = 5, M = 3
1 2 3 4 5
1 3 2 3 2

이때 두사람이 고를 수 있는 볼링공 번호의 조합은?
(1, 2) -> (1, 3)
(1, 3) -> (1, 2)
(1, 4) -> (1, 3)
(1, 5) -> (1, 2)
(2, 3) -> (3, 2)
(2, 4) -> (3, 3) -> 불가능
(2, 5) -> (3, 2)
(3, 4) -> (2, 3)
(3, 5) -> (2, 2) -> 불가능
(4, 5) -> (3, 2)

-> 조합
 */
public class Main {
    private static int N;
    private static int M;
    private static int[] arrA;
    private static int[] arrB;

    private static int result;

    public static void main(String[] args) throws Exception {
        input();

        /**
         (1, 2) (1, 3) (1, 4) (1, 5)
         (2, 1) == (1, 2) 이므로 j 를 i + 1로 시작
         */
        for (int i = 1; i < arrA.length; i++) {
            for (int j = i + 1; j < arrB.length; j++) {
                if (arrA[i] != arrB[j]) {
                    result++;
                }
            }
        }

        System.out.println(result);
    }

    /*
     5 3
     1 3 2 3 2
     */
    private static void input() throws Exception {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int M = scanner.nextInt();

        arrA = new int[N + 1];
        arrB = new int[N + 1];

        for (int i = 1; i < N + 1; i++) {
            int value = scanner.nextInt();

            if (value > M) {
                throw new Exception();
            }

            arrA[i] = value;
            arrB[i] = value;
        }
    }
}
