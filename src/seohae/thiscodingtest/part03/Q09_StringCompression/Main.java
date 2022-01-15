package seohae.thiscodingtest.part03.Q09_StringCompression;

import java.util.ArrayList;
import java.util.List;

/*
 문자열 압축
 : 문자열에서 같은 값이 연속해서 나타나는 것을 그 문자의 개수와 반복되는 값으로 표현하여 더 짧은 문자열로 줄여서 표현

 (예시)
 aabbaccc -> 2a2ba3c
 앞의 a 개수 : 2
 b 개수 : 2
 a 개수 (1이므로 a)
 c 개수 : 3

 문자열을 1개 이상의 단위로 잘라서 압축하여 더 짧은 문자열로 표현할 수 있는지 찾아보자.

 (예시)
 ababcdcdababcdcd

 ab : 2개
 cd : 2개
 bc : 2개
 dcd

 2ab2cd2ab2cd
또는 ababcdcd : 2개 : 2ababcdcd

가장 짧게 압축하기

https://programmers.co.kr/learn/courses/30/lessons/60057
 */
public class Main {
    public static void main(String[] args) {
        Main main = new Main();

        System.out.println(main.solution("aabbaccc"));
//        System.out.println(main.solution("ababcdcdababcdcd"));
//        System.out.println(main.solution("abcabcdede"));
//        System.out.println(main.solution("abcabcabcabcdededededede"));
//        System.out.println(main.solution("xababcdcdababcdcd"));
//        System.out.println(main.solution("aaaaaaaaaaaabcd"));
//        System.out.println(main.solution("a"));
    }

    public int solution(String s) {
        int answer = s.length();

        /* 문자열 하나일때 체크 */
//        if (s.length() == 1) {
//            return 1;
//        }

        /* 반복 문자열 개수 1부터 s.length() / 2 까지 */
        for (int i = 1; i <= s.length() / 2; i++) {
            /* 반복개수 단위 i 만큼 add 할 리스트 */
            List<String> stringList = new ArrayList<>();
            /* 해당 문자열의 반복 개수를 add 할 리스트 */
            List<Integer> numList = new ArrayList<>();

            /* 첫 문자열부터 i 길이 만큼 체크 */
            for (int j = 0; j < s.length(); j = j + i) {
                /* aabbaccc : j : 6이고, i가 3일때 aab(0,1,2) bac(3,4,5) cc(6,7)
                 * 여기서 j = 6일때 j + i = 9이고, s.length = 8
                 * 이때는 i만큼 자를 수 없다는 뜻
                 * */
                if (j + i <= s.length()) {
                    String result = s.substring(j, j + i);

                    if (j == 0) { // 처음에
                        // 문자열 add
                        stringList.add(result);
                        numList.add(1);
                    } else {
                        // 마지막 문자열
                        String target = stringList.get(stringList.size() - 1);

                        // 동일하다면
                        if (target.equals(result)) {
                            int index = numList.size() - 1;
                            // + 1을 위해서 첫 문자열 add 할때 numList 에 1 을 setting
                            numList.set(index, numList.get(index) + 1);
                        } else {
                            // 동일하지 않다면 새로운 문자열
                            stringList.add(result);
                            numList.add(1); // 1 set
                        }
                    }
                } else {
                    /* 반복 개수에 걸리지 않으면 남은 문자열 add */
                    stringList.add(s.substring(j));
                    numList.add(1); // 1 set
                }
            }

            /* 문자열 체크 종료 - 각 리스트별 개수 세기 */
            // 1. 각 리스트의 원소별 length 로 변환하여 sum (5, 12, 1 -> 5(1자릿수) + 12(2자릿수) = 3)
            int numCount = (int) numList.stream()
                    .filter(value -> value > 1)
                    .map(value -> value.toString().length())
                    .mapToInt(Integer::intValue)
                    .sum();

            // 2. 각 리스트의 원소별 String length sum  (aa, bb, cc : 6)
            int stringCount = stringList.stream()
                    .mapToInt(String::length)
                    .sum();

            answer = Math.min(answer, numCount + stringCount);

        }

        return answer;
    }
}
