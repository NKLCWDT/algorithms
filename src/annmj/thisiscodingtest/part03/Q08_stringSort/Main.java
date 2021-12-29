package annmj.thisiscodingtest.part03.Q08_stringSort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();
        char[] arr = input.toCharArray();
        Arrays.sort(arr);
        int sum = 0;
        boolean isNumberInclude = false;
        ArrayList<String> answer = new ArrayList<>();

        for (int i = 0; i < arr.length; i++) {
            if (((int) arr[i]) < 58) {
                isNumberInclude = true;
                sum += arr[i] - '0';
            } else {
                answer.add(String.valueOf(arr[i]));
            }
        }
        if(isNumberInclude) answer.add(String.valueOf(sum));

        for (String value : answer) {
            System.out.print(value);
        }
    }
}
