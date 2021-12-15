package annmj.thisiscodingtest.part03.Q07_luckyStraight;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static int N;
    static ArrayList<Integer> result = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        N = scanner.nextInt();

        makeDigits(); // 자리수변환

        int leftSum = 0;
        int rightSum = 0;
        int size = result.size();
        for (int i = 0; i < size/2; i++) {
            leftSum += result.get(i);
        }
        for (int i = size/2; i < size; i++) {
            rightSum += result.get(i);
        }
        System.out.println(leftSum == rightSum ? "LUCKY" : "READY");
    }

    static void makeDigits() {
        int num = N;
        int remain;
        while (true) {
            remain = num % 10;
            result.add(remain);

            num = num / 10;
            if(num < 10) {
                result.add(num);
                break;
            }
        }
    }
}
