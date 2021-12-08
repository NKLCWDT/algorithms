package jihye.chapter7.findingParts;

import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int arr[] = new int[N];
        //부품을 입력받는다.
        for(int i=0; i < N; i++){
            arr[i]=sc.nextInt();
        }
        int M = sc.nextInt();
        //값을 받으면 binary_search를 통해 값이 있으면 yes 없으면 no를 출력
        for (int i=0; i < M; i++){
            binary_search(arr, sc.nextInt(), 0, N-1);
        }
    }public static void binary_search(int[] arr , int target, int start, int end){
        while(start<= end){
            int mid = (start + end)/2;
            if(arr[mid]== target){
                System.out.println("yes ");
                return;
            }else if(arr[mid] < target){
                start = mid + 1;
            }else{
                end = mid -1;
            }
        }
        System.out.print("no ");
        return;
    }
}
