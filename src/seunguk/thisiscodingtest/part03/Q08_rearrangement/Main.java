package seunguk.thisiscodingtest.part03.Q08_rearrangement;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        int count = 0;
        String result = "";
        for (int i = 0; i < s.length(); i++) {
            if (0 <= s.charAt(i) - '0' && s.charAt(i) - '0' <= 9) { // 0 과 9 사이값이면 count에 더한다.
                count += s.charAt(i) - '0';
            } else { // 문자값이면 result에 더해준다.
                result += s.charAt(i);
            }
        }
        char[] charResult = result.toCharArray(); // 정렬을 위해 배열로 바꿔준다.
        Arrays.sort(charResult); // 정렬
        result = new String(charResult); // 다시 String으로 바꿔준다.
        result += count;
        System.out.println(result);
    }
}

/*
* K1KA5CB7
* AJKDLSI412K4JSJ9D
* */