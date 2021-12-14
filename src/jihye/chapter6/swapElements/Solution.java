package jihye.chapter6.swapElements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        //A 배열과 B 배열을 Integer array 로 받는다(Integer로 받은 이유는 오름차순 정렬하기 위해)
        Integer [] A = Arrays.stream(br.readLine().split(" "))
                .map(Integer::parseInt).toArray(Integer[]::new);

        Integer [] B = Arrays.stream(br.readLine().split(" "))
                .map(Integer::parseInt).toArray(Integer[]::new);

        //오름차순으로 정렬
        Arrays.sort(A);
        //내림차순으로 정렬
        Arrays.sort(B, Collections.reverseOrder());

        //비교
        for(int i=0; i < K; i++){
            if(A[i] < B[i]){
                A[i] = B[i];
            }
        }

        //A배열의 합
        int ans = Arrays.stream(A).reduce((a,b)->a+b).get();
        System.out.println(ans);
    }
}
