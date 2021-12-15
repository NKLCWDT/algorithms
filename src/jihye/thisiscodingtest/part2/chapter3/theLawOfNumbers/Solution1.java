package jihye.thisiscodingtest.part2.chapter3.theLawOfNumbers;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Stream;

public class Solution1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int [] info = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int temp = 0;
        int small_biggest = 0;
        for(int i = 0; i < info[0]; i++){
            int [] row = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            Arrays.sort(row);
            temp = row[0];
            if(temp > small_biggest){
                small_biggest = temp;
            }
        }
        System.out.println(small_biggest);
    }
}
