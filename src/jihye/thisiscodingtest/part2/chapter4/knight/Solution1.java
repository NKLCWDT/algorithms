package jihye.thisiscodingtest.part2.chapter4.knight;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class Solution1 {
    static List<Character> row = Arrays.asList('1', '2', '3','4','5','6','7','8','9');
    static List<Character> col = Arrays.asList('a','b','c','d','e','f','g','h');
    static int [] two = {2,-2};
    static int [] one = {1,-1};
    static int count =0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String place = br.readLine();
        int curr_row = col.indexOf(place.split("")[0]);
        int curr_col = col.indexOf(place.split("")[1]);
        countAnswer(curr_row, curr_col);
        countAnswer(curr_col, curr_row);
        System.out.println(count);
    }
    public static void countAnswer(int main, int side){
        for(int i=0; i < two.length; i++){
            main+=two[i];
            if(main > 8 || main < 0){
                continue;
            }
            for(int j=0; j < one.length; j++){
                side += one[j];
                if(side >= 0 && side < 8){
                    count++;
                }
            }
        }
    }
}
