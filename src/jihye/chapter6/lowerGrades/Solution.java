package jihye.chapter6.lowerGrades;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        String arr[][] = new String[N][2];

        //array에 차례로 넣어준다.
        for(int i=0; i < N; i++){
            arr[i] = br.readLine().split(" ");
        }

        //arr[i][1]에 있는 값끼리 비교해준다
        Arrays.sort(arr, (a, b) -> Integer.parseInt(a[1]) - Integer.parseInt(b[1]));

        //출력
        for(int i=0; i < arr.length; i++){
            System.out.print(arr[i][0]+" ");
        }

    }
}
