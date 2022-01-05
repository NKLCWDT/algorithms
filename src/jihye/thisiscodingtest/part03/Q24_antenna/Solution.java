package jihye.thisiscodingtest.part03.Q24_antenna;

import java.util.Arrays;
import java.util.Scanner;

public class Solution {
    //어떤 값들이 있어도 정확히 중간값에 해당하는 위치에 안테나 설치시 모든 집까지의 최소거리가 된다.
    //[1,2,3,5,8,9]로 예를 들면 3과 5가 모든 집에서 최소거리를 가질 수 있다.
    //3-> 2+1+2+5+6=16
    //5-> 4+3+2+3+4=16
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] houses = new int[N];
        for (int i = 0; i < N; i++) {
            houses[i] = sc.nextInt();
        }
        Arrays.sort(houses);


        int mid = houses.length / 2;
        System.out.println(houses[mid - 1]);
    }
}
