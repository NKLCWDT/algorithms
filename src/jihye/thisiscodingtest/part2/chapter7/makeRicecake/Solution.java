package jihye.thisiscodingtest.part2.chapter7.makeRicecake;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        Integer arr[] = new Integer[N];
        for(int i=0;i < N; i++){
            arr[i]= sc.nextInt();
        }
        int ans = binary_search(arr, M, 0, Collections.max(Arrays.asList(arr)));
        System.out.println(ans);

    }public static int binary_search(Integer[] arr , int target, int start, int end){
        int max = 0;
        while(start<= end){
            int mid = (start + end)/2;//중간값을 기준으로
            int temp =0;
            for(int i=0; i < arr.length; i++){
                temp += Math.max(0,arr[i] - mid);//떡에서 절단기의 높이를 뺀값을 다 더한다.
            }
            if(temp == target){//다 계산이 완료된값이 target이랑 같으면 최대값을 찾아야하니 비교후 더 큰 값 저장
                max = Math.max(max, mid);
                start = mid+1;//시작부분을 다음값으로 지정
            }else if(temp > target){
                start = mid + 1;
            }else{
                end = mid -1;
            }
        }
        return max;
    }
}
