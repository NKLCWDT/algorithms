package jihye.thisiscodingtest.part03.Q04_amountCannotMake;

import java.util.Arrays;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] arr = new int[N];
        int answer = 1;

        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }
        Arrays.sort(arr);
        while (true) {
//            if(answer < arr[0]){
//                System.out.println(answer);
//                break;
//            }
//            boolean check;
//            for(int i=0; i< arr.length; i++){
//                if(answer % arr[i] == 0){
//                    System.out.println(answer);
//                    check = true;
//                    break;
//                }
//
//            }
            int temp = 0;
            for (int i = 0; i < arr.length; i++) {
                temp += arr[i];
                if (answer <= temp) {//temp의 값이 answer을 넘거나 같으면 break
                    break;
                }
            }

            if (answer != temp) {//answer이 temp와 같지 않으면
                System.out.println(answer);
                break;
            }

            answer++;
        }
    }
}
