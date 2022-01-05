package jihye.thisiscodingtest.part03.Q24_antenna;

import java.util.Arrays;
import java.util.Scanner;

public class Solution {
    //주석으로 예시들기
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] houses = new int[N];
        for(int i=0; i<N; i++){
            houses[i]= sc.nextInt();
        }
        Arrays.sort(houses);


        int mid = houses.length/2;
        System.out.println(houses[mid-1]);
    }
}
