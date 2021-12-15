package jihye.thisiscodingtest.part2.chapter3.untilOne;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public class Solution1 {
    public static void main(String[] args) throws IOException {
        int count =0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int [] nums = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        //n이 1이 될때까지 n을 -1이나 /k해준다.
        while(nums[0] != 1){
            if(nums[0] % nums[1] == 0){
                nums[0] /= nums[1];
            }else{
                nums[0]--;
            }
            count++;
        }
        System.out.println(count);
    }

}
