package annmj.thisiscodingtest.part03.Q02_mulOradd;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        process();
    }

    private static void process() {
        Scanner scanner = new Scanner(System.in);
        String S = scanner.next();
        Queue<Integer> numbers = new LinkedList<>();
        for (int i = 0; i < S.length(); i++) {
            numbers.add(S.charAt(i) - '0');
        }

        if (numbers.size() == 1) {
            System.out.println(numbers.remove());
            return;
        }

        int result = 0;
        while (!numbers.isEmpty()) {
            int right = numbers.remove();
            if (right == 0) {
                continue;
            } else {
                if (result == 0) {
                    result += right;
                } else {
                    result = result * right;
                }
            }
        }

        System.out.println(result);
    }
}
