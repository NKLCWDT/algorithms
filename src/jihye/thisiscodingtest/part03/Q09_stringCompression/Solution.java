package jihye.thisiscodingtest.part03.Q09_stringCompression;

public class Solution {
    private static boolean check = false;

    public static void main(String[] args) {
        String s = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        System.out.println(solution(s));
    }

    public static int solution(String s) {
        int unit = 1;
        int ans = Integer.MAX_VALUE;

        while (true) {
            if (unit > s.length() / 2) {//자르는 단위가 절반을 넘어가면 break
                break;
            } else {
                int n = findCompression(unit, s);
                if (check == true) {
                    ans = Math.min(ans, n);
                }
            }
            unit++;
            check = false;
        }

        if(ans == Integer.MAX_VALUE){
            return s.length();
        }
        return ans;
    }

    public static int findCompression(int unit, String s) {//문자열 찾기 구간
        String newString = "";
        String one = s.substring(0, unit);//하나의 일치하는 스트링 단위
        int end = 0;
        for (int i = unit; i < s.length(); i += unit) {
            int count = 1;
            while (i <= s.length() - unit && s.substring(i, i + unit).equals(one)) {
                count++;
                i = i + unit;
            }
            if (count != 1) {//count가 2이상일때
                check = true;
                newString += count + one;//newString에 count와 반복되는 string(one)을 넣는다                System.out.println(newString);
           } else {
                newString += one;
            }
            if (i + unit > s.length()) {
                end = i;
                break;
            }
            one = s.substring(i, i + unit);

        }
        if (end <= s.length()) {
            newString += s.substring(end);
        }
        return newString.length();
    }
}
