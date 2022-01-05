package seohae.thiscodingtest.part03.Q02_MultiplyAndSum;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
   각 자리수가 숫자 0~9로만 이루어진 문자열

   왼쪽부터 오른쪽으로 하나씩 모든 숫자를 확인하며 숫자 사이에 'x', '+' 연산자를 넣어 결과적으로 만들어질 수 있는 가장 큰 수

 */
public class Main {
    static String param;
    static List<Integer> list = new ArrayList<>();

    public static void main(String[] args) {
        input();


        int sum = 0;
        int index = 0;

        // list.size() = 5
        while (list.size() > index) {
            boolean isMultiply = false;

            int target = list.get(index);

            // 0 이 아닐경우 곱셈
            if (sum != 0 && target != 0 && sum != 1 && target != 1) {
                isMultiply = true;
            }

            if (isMultiply) {
                sum *= target;
            } else {
                sum += target;
            }

            index++;
        }

        System.out.println(sum);

    }

    private static void input() {
        Scanner sc = new Scanner(System.in);
        param = sc.next();

        // List 변환
        list = param.chars()
                .mapToObj(ch -> ch - '0')
                .collect(Collectors.toList());
    }
}
