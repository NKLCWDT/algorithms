package jihye.thisiscodingtest.part03.Q35_uglyNumber;

import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

//        int d[] = new int[10001];
//        d[1] = 1;
//        for (int i=2; i<n+1;i++){
//            if(i%2 == 0){
//                d[i] = d[i/2] * 2;
//            }else if(i % 3 == 0){
//                d[i] =
//            }
//        }

        int index = 2;
        int count = 1;

        while (true) {
            int temp = index;

            if(temp % 2 == 0){
                temp /= 2;
            }
            if(temp % 3 == 0){
                temp /= 3;
            }
            if(temp % 5 == 0){
                temp /= 5;
            }

            if (temp == 1){
                count++;
            }

            if (count == n) {
                break;
            }
            index++;
        }
        System.out.println(index);
    }
}
