package annmj.thisiscodingtest.part03.Q18_bracketChange;

import java.util.Scanner;
import java.util.Stack;

public class Main {
    static String p = "";
    public static void main(String[] args) {
        input();
        System.out.println(solution(p));
    }

    public static String solution(String p) {
        if(p.length() == 0) return p;

        if(isRightBracket(p)) {
            return p;
        }
        String answer = "";
        answer = convertBracket(p);
        return answer;
    }

    public static String convertBracket(String p) {
        if(p.length() == 0) return p;

        String w = p;
        String u = "";
        String v = "";

        // 균형잡힌 괄호 문자열 u, v로 분리하기
        u = makeUString(w);
        v = makeVString(w, u);

        if (isRightBracket(u)) {
            u += convertBracket(v);
            return u;
        } else {
            // step1
            String steps = "(";

            // step2
            steps += convertBracket(v);

            // step3
            steps += ")";

            // step4
            u = u.substring(1, u.length() - 1);
            u = reverseString(u);
            steps += u;
            return steps;
        }
    }

    public static String reverseString(String u) {
        String result = "";
        for (int i = 0; i < u.length(); i++) {
            if (u.charAt(i) == '(') {
                result += ")";
            } else {
                result += "(";
            }
        }
        return result;
    }

    public static String makeVString(String w, String u) {
        return w.substring(u.length(), w.length());
    }

    /*
    w : 균형잡힌 문자열
     */
    public static String makeUString(String w) {
        int open = 0;
        int close = 0;
        for (int i = 0; i < w.length(); i++) {
            if (w.charAt(i) == '(') {
                open++;
            } else {
                close++;
            }

            if(open == close) {
                return w.substring(0, i + 1);
            }
        }
        return "";
    }

    public static void input() {
        Scanner scanner = new Scanner(System.in);
        p = scanner.next();
    }

    public static boolean isRightBracket(String brackets) {
        Stack<Character> stack = new Stack<>();
        stack.add(brackets.charAt(0));
        for (int i = 1; i < brackets.length(); i++) {
            if(!stack.isEmpty() && stack.peek() == '(' && brackets.charAt(i) == ')') {
                stack.pop();
            } else {
                stack.add(brackets.charAt(i));
            }
        }

        return stack.isEmpty();
    }
}
