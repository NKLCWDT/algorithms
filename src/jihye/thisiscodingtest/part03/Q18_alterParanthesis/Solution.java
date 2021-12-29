//package jihye.thisiscodingtest.part03.Q18_alterParanthesis;
//
//import java.util.Stack;
//
//public class Solution {
//    public static void main(String[] args) {
//        String paranthesis = "(()())()";
//        String afterAlter = alter(paranthesis);
//
//
//    }
//
//    public static String alter(String paranthesis) {
//        if (paranthesis.isEmpty()) {
//            return paranthesis;
//        }
//        if (isBalanced(paranthesis)) {
//            return paranthesis;
//        }
//        makeBalanced(paranthesis);
//    }
//
//    public static boolean isBalanced(String paranthesis) {
//        Stack<Character> check = new Stack();
//        for (int i = 0; i < paranthesis.length(); i++) {
//            if (paranthesis.charAt(i) == '(') {
//                check.add('(');
//            } else {
//                if (!check.isEmpty()) {
//                    check.pop();
//                } else {
//                    return false;
//                }
//            }
//        }
//        if (!check.isEmpty()) {
//            return false;
//        }
//        return true;
//    }
//
//    public static String makeBalanced(String paranthesis) {
//        String balancedParanthesis = "";
//        balancedParanthesis += "(";
//        String u =
//    }
//}
