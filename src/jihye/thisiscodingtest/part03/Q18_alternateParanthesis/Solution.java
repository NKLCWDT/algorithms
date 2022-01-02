package jihye.thisiscodingtest.part03.Q18_alternateParanthesis;

import java.util.Stack;

public class Solution {
    public static void main(String[] args) {
        String p = "()))((()";
        System.out.println(alternate(p));
    }

    public static String alternate(String p) {

        //p가 비어 있으면 그대로 return
        if (p.isEmpty()) {
            return p;
        }
        if (isBalanced(p)) {//만약에 올바른 문자열이면 그대로 return
            return p;
        } else {//아닐시에 올바른 문자열을 만드는 함수 호출
            return uBalance(p);
        }
    }

    //올바른 문자열인지 확인
    public static boolean isBalanced(String p) {
        Stack<Character> characters = new Stack<>();

        for (int i = 0; i < p.length(); i++) {
            if (p.charAt(i) == '(') {//열려있는 괄호면 character에 더하기
                characters.push('(');
            } else {
                if (characters.empty()) {//만약 중간에 비어있으면 짝이 맞지 않으므로 return
                    return false;
                } else {//짝이 맞으면 pop
                    characters.pop();
                }
            }
        }

        //다 돌았는데 character이 남아있으면 짝이 맞지 않으므로 return
        if (characters.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    //밸런스가 맞추는 함수
    public static String uBalance(String p) {
        String w = p;
        String u = "";
        String v = "";
        //만일 p가 비어있다면 return ""
        if (p.isEmpty()) {
            return "";
        }
        boolean isBalance = false;
        int open = 0, close = 0;
        //균형잡힌 최소한의 문자열로 u 만들고 v 만들기
        for (int i = 0; i < p.length(); i++) {
            if (p.charAt(i) == '(') {//열린 괄호와 닫힌 괄호 갯수 세기
                open++;
            } else {
                close++;
            }
            u += Character.toString(p.charAt(i));
            if (open == close) {//괄호의 갯수가 같으면 균형잡힌 문자열
                isBalance = true;
                v = w.substring(i + 1);
                break;
            }
        }


        String newOne = "";
        if (isBalanced(u)) {//u가 균형잡힙 문자열이라면 계속 재귀적으로 uBalanced 호출
            return u + uBalance(v);
        } else {//아니라면
            newOne += "(";//빈문자열에 "("붙이고
            newOne += v;//v를 붙이고
            newOne += ")";//다시 닫는 괄호 붙인다
            System.out.println(newOne);
            u = u.substring(1, u.length() - 1);//u의 첫글자와 마지막 글자 제외하고

            for (int i = 0; i < u.length(); i++) {//뒤집어서 붙인다
                if (u.charAt(i) == '(') {
                    newOne += ")";
                } else {
                    newOne += "(";
                }
            }
            return newOne;
        }
    }
}


/**
 * import java.util.Stack;
 * <p>
 * class Solution {
 * int pos = 0;
 * boolean isCorrect(String str){//균형잡인, 올바른 인지 구분
 * boolean ret = true;
 * int open = 0, close=0;
 * Stack<Character> stack = new Stack<>();
 * <p>
 * for(int i =0; i < str.length(); i++){
 * if(str.charAt(i)=='('){
 * stack.push('(');
 * open++;
 * }else{
 * close++;
 * if(stack.empty()){
 * ret = false;
 * }else{
 * stack.pop();
 * }
 * }
 * if(close==open){//가장 짧은 균형잡힌
 * pos = i+1;
 * return ret;
 * }
 * }
 * <p>
 * return true;
 * }
 * public String solution(String p) {
 * String answer = "";
 * if(p.isEmpty()){
 * return answer;
 * }
 * boolean correct = isCorrect(p);
 * <p>
 * String u = p.substring(0,pos);//균형잡힌 문자열
 * String v = p.substring(pos,p.length());//나머지
 * <p>
 * if(correct){
 * return u+solution(v);
 * }
 * <p>
 * //올바른 괄호 문자열이 아님
 * answer = "(" + solution(v) + ")";
 * for(int i =1; i < u.length()-1; i++){
 * if(u.charAt(i)=='('){
 * answer +=')';
 * }else{
 * answer +='(';
 * }
 * }
 * return answer;
 * }
 * }
 */
