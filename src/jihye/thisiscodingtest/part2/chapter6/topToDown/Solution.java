package jihye.thisiscodingtest.part2.chapter6.topToDown;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N  = Integer.parseInt(br.readLine());

        Integer [] arr = new Integer[N];

        for(int i=0; i < N; i++){//숫자 저장
            arr[i] = Integer.parseInt(br.readLine());
        }
        //자바가 제공하는 클래스 이용해 정렬
        Arrays.sort(arr, Collections.reverseOrder());
        System.out.println(Arrays.toString(arr));
    }
}
