package jihye.chapter8.makeItOne;

import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int d[] = new int[N+1];
        for(int i=2; i < N+1; i++){
            d[i] = d[i-1] + 1;//-1을 할 경우
            //Math.min을 통해 d[i]에 들어 있는 값이 가장 작은값을 구한다.
            //왜냐하면 연산을 사용하는 횟수의 최솟값을 구해야 되기 때문
            if(i % 2 == 0){//2로 나누어 떨어질때 d[i/2]+1의 값과 d[i]의 값 비교
                //d[i/2]에는 전에 계산한 i/2의 숫자까지의 최소 연산횟수 값이 들어 있다
                d[i] = Math.min(d[i], d[i/2] +1);
            }if(i % 3 == 0){
                d[i] = Math.min(d[i], d[i/3] +1);
            }if(i % 5 == 0){
                d[i] = Math.min(d[i], d[i/5] +1);
            }
        }
        System.out.println(d[N]);
    }
}
