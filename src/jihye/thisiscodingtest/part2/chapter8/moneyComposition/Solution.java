package jihye.thisiscodingtest.part2.chapter8.moneyComposition;

import java.util.Arrays;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int money[] = new int[N];
        int d[] = new int[10001];

        Arrays.fill(d, 10001);
        //화폐 단위 저장
        for(int i=0; i<N;i++){
            money[i] = sc.nextInt();
        }

        //0 index 에는 0으로 초기화 시킨다.
        d[0]=0;
        for(int i=0; i < money.length;i++){
            for(int j=money[i];j < M+1;j++){//j에 화폐단위를 줘서 화폐단위보다 작은 숫자가 오는것을 막는다.
                d[j] = Math.min(d[j],d[j-money[i]] + 1);//화폐가 있는 값들이 여기서 처음 초기화되게 된다.
            }
        }
        /*
        예를들면, 2와 3이 화폐단위일때 d[2]가 처음에 d[2]과 d[2-2] +1중 작은값을 넣게 된다.
         */

        if(d[M] == 10001){
            System.out.println(-1);
        }else{
            System.out.println(d[M]);
        }
    }
}
