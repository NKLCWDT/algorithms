package seunguk.thisiscodingtest.part03.Q18_parenthesisconversion;

import java.util.Scanner;

public class Main {

    static class Node {
        String u;
        String v;

        Node(String u, String v) {
            this.u = u;
            this.v = v;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();

        System.out.println(solution(s));
    }
    private static String solution(String s) {
        String result = "" + "(";
        if (s.isEmpty()) {
            return "";
        }

        Node node = divide(s);
        String u = node.u;
        String v = node.v;

        boolean check = check(u);

        if (check) {
            return u + solution(v);
        } else {
            result += solution(v);
            result += ")";
            u = u.substring(1, u.length()-1);
            u = reverse(u);
            return result + u;
        }
    }

    private static Node divide(String s) {
        int open = 0;
        int close = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                open++;
            } else {
                close++;
            }

            if (open == close) {
                return new Node(s.substring(0, i+1), s.substring(i+1, s.length()));
            }
        }
        return new Node("", "");
    }

    private static boolean check(String u) {
        int open = 0;
        int close = 0;
        for (int i = 0; i < u.length(); i++) {
            if (u.charAt(i) == '(') {
                open++;
            } else {
                close++;
            }

            if (open < close) {
                return false;
            }
        }
        return true;
    }

    private static String reverse(String u) {
        String answer = "";
        for (int i = 0; i < u.length(); i++) {
            if (u.charAt(i) == '(') {
                answer += ")";
            } else {
                answer += "(";
            }
        }
        return answer;
    }


}
/*
(()())()
)(
()))((()
*/

