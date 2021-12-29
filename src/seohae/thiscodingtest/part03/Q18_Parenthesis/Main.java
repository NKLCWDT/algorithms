package seohae.thiscodingtest.part03.Q18_Parenthesis;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

/*
  괄호의 짝이 맞는지 체크

  case1. (()))( : 균형잡힌 괄호 문자열 O / 올바른 괄호 문자열 X
  case2. (())() : 균형잡힌 괄호 문자열 O / 올바른 괄호 문자열 O

  https://programmers.co.kr/learn/courses/30/lessons/60058
 */
public class Main {
    private static String value;
    private static Stack<Character> stack = new Stack<>();

    public static void main(String[] args) {
        input();

        String v = value;
        System.out.println(getResultString(v));
    }

    /*
      ()))((()
     */
    private static void input() {
        Scanner in = new Scanner(System.in);
        value = in.next();
    }

    private static String getResultString(String paramV) {
        if ("".equalsIgnoreCase(paramV)) {
            return "";
        }

        String result = "";

        String u = "";
        String v = "";

        int uStartIndex = 0;
        int uEndIndex = getULastIndex(paramV);

        u = paramV.substring(uStartIndex, uEndIndex + 1);
        v = paramV.substring(uEndIndex + 1);

        // 올바른 괄호 문자열이 아닐 경우
        if (!isCollect(u)) {
            // 첫번째 문자로 '('를 붙인다.
            String uResult = "(";

            // 문자열 v에 대해 1단계부터 재귀적으로 수행한 결과 문자열을 이어붙인다.
            uResult += getResultString(v);

            // ')'를 붙인다.
            uResult += ')';

            // u의 첫번째와 마지막 문자를 제거하고, 나머지 문자열의 괄호 방향을 뒤집어서 뒤에 붙인다.
            uResult += reverseString(u.substring(1, u.length() - 1));

            result = uResult;
        } else {
            result += u + getResultString(v);
        }

        return result;
    }

//    private static int getVLastIndex(String param) {
//        int lastIndex = 0;
//
//        /* step2. 올바른 괄호 문자열 체크 */
//        for (int i = 0; i < param.length(); i++) {
//            /* case1. '(' 일때 */
//            if (param.charAt(i) == '(') {
//                stack.push(param.charAt(i)); // push
//            } else if (param.charAt(i) == ')') { /* case2. ')' 일때 */
//                if (!stack.isEmpty()) { /* 비어져있지 않을때 */
//                    stack.pop(); /* '(' pop (짝을 찾는다) */
//                    lastIndex = i;
//                } else {
//                    /* 짝이 존재하지 않는다. */
//                    break;
//                }
//            }
//        }
//
//        return lastIndex;
//    }

    /**
     * 균형잡힌 괄호 문자열 U의 get Last Index
     * u 는 더이상 균형잡힌 문자열로 분리할 수 없어야한다. 첫번재 균형잡힌 문자열
     * @param param
     * @return
     */
    public static int getULastIndex(String param) {
        int open = 0;
        int close = 0;

        for (int i = 0; i < param.length(); i++) {
            if (param.charAt(i) == '(') {
                open++;
            } else {
                close++;
            }

            if (open == close) {
                return i;
            }
        }

        return 0;
    }

    /**
     * 올바른 괄호 문자열 여부
     * @return
     */
    private static boolean isCollect(String param) {
        boolean isTrue = false;
        for (int i = 0; i < param.length(); i++) {
            /* case1. '(' 일때 */
            if (param.charAt(i) == '(') {
                stack.push(param.charAt(i)); // push
            } else if (param.charAt(i) == ')') { /* case2. ')' 일때 */
                if (!stack.isEmpty()) { /* 비어져있지 않을때 */
                    stack.pop(); /* '(' pop (짝을 찾는다) */
                    isTrue = true;
                } else {
                    /* 짝이 존재하지 않는다. */
                    isTrue = false;
                    break;
                }
            }
        }

        return isTrue;
    }

    /**
     * 문자열 뒤집기
     * "))(()(" -> "()(())" 가 아니고
     * "))(()(" -> "(())()"
     * @param u
     * @return
     */
    public static String reverseString(String u) {
        Map<Character, String> map = new HashMap<>();
        map.put('(', ")");
        map.put(')', "(");

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < u.length(); i++) {
            result.append(map.get(u.charAt(i)));
        }

        return result.toString();
    }
}
