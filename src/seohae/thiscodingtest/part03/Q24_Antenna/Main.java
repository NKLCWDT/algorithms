package seohae.thiscodingtest.part03.Q24_Antenna;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/*
   일직선상의 마을에 여러 채의 집이 위치
   이중에서 특정 위체의 집에 특별히 한개의 안테나를 설치하기로 진행

   효율성
   : 안테나로부터 모든 집까지의 거리의 총합이 최소가 되도록 설치

   안테나는 집이 위치한 곳에만 설치 가능
   논리적으로 동일한 위치에 여러 개의 집이 존재하는 거이 가능

   집들의 위치 값이 주어질때 안테나를 설치할 위치를 선택해야한다.
   N = 4,
   각 위치가 1, 5, 7, 9

   -> 5에 설치했을때 (4 + 0 + 2 + 4) = 10으로 최소.
 */
public class Main {
    static int N;
    static List<Integer> list = new ArrayList<>();

    public static void main(String[] args) {
        input();

        // 동일하거나 큰 숫자
        int mid = N % 2 == 0 ? (N / 2) - 1 : (N / 2);
        Collections.sort(list);
        System.out.println(list.get(mid));
    }

    private static void input() {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();

        for (int i = 0; i < N; i++) {
            list.add(sc.nextInt());
        }
    }
}
