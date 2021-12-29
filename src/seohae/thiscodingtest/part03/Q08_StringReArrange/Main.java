package seohae.thiscodingtest.part03.Q08_StringReArrange;

import java.util.Scanner;
import java.util.stream.Collectors;

/*
  알바펫 오름차순 정렬
  숫자 더한값을 마지막에 삽입

  예시)
  K1KA5CB7 -> ABCKK13
 */
public class Main {
    private static String param;

    public static void main(String[] args) {
        input();

        /* num sum */
        int numSum = param.chars()
                .filter(Character::isDigit)
                .map(a -> Character.digit(a, 10))
                .sum();

        /* string sort */
        String chars = param.chars()
                            .sorted()
                            .mapToObj(ch -> (char) ch)
                            .filter(ch -> !Character.isDigit(ch))
                            .map(Object::toString)
                            .collect(Collectors.joining());

        System.out.println(chars + numSum);
    }

    private static void input() {
        Scanner scan = new Scanner(System.in);
        param = scan.next();
    }
}
