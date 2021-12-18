package jihye.thisiscodingtest.part02.chapter4.sight;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.Integer.parseInt;

public class Solution1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int time = Integer.parseInt(br.readLine());
        int count=0;
        for(int hour=0; hour <= time; hour++){
            for(int min = 0; min <= 59; min++){
                for(int sec =0; sec <= 59; sec++){
                    String shour = String.valueOf(hour);
                    String smin  = String.valueOf(min);
                    String ssec = String.valueOf(sec);
                    if((shour+smin+ssec).contains("3")){
                        count++;
                    }
                }
            }
        }
        System.out.println(count);
    }
}
