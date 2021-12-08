package jihye.chapter8.constructionFloor;

import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        int N = sc.nextInt();
        int d[] = new int[N+1];
        d[1] = 1;//2,1일때의 경우의 수=>(2,1)
        d[2] = 3; //2,2 일때의 경우의 수=>(1,2)*2, (2,2), (2,1) * 2
        for(int i=3; i < N; i++){//덮개의 형태가 최대(2,2)이므로
            d[i] = ((d[i-1] + d[i-2]*2 )%796796);
        }
        System.out.println(d[N]);
    }
}
