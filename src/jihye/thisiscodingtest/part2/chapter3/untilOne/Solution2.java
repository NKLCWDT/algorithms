package jihye.thisiscodingtest.part2.chapter3.untilOne;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public class Solution2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int [] nums = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int count =0;
        while (true){
            //나눌 수 있는 수까지 -1을 몇번해주는지 횟수 세는부분
            int temp = (nums[0] / nums[1]) * nums[1];
            count += (nums[0] - temp);
            nums[0] = temp;
            //만약 n이 k보다 작으면 while종료
            if(nums[0] <nums[1]){
                break;
            }
            //한번 나눠주기때문에 count세어준다.
            count++;
            nums[0] /= nums[1];

        }
        count += (nums[0]-1);
        System.out.println(count);
    }
}
