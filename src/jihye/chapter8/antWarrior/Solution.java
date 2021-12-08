package jihye.chapter8.antWarrior;

import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int arr[] = new int[N];
        int max = 0;
        int d[] = new int[N];
        //배열을 받는다.
        for(int i=0; i < N; i++){
            arr[i] = sc.nextInt();
        }
        d[0] = arr[0];
        d[1] = Math.max(arr[0], arr[1]);//i의 값이 올라갈수록 가장 큰 조합을 찾아야되기 때문에
        for(int i=2; i<N-2; i++){
            //d[i-1]에 합쳐진 값은 arr의 한칸 옆에 있는 값이 더해진 값이므로
            d[i] = Math.max(d[i-1],d[i-2] + arr[i]);
        }
        System.out.println(max);
    }
}
