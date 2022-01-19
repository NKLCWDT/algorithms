package jihye.thisiscodingtest.part03.Q09_stringCompression;

public class Solution {

    public static void main(String[] args) {
        String s = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        System.out.println(solution(s));
    }

    public static int solution(String s) {
        int unit = 1;
        int answer = s.length();

        while (true) {
            if (unit > s.length() / 2) {    //자르는 단위가 절반을 넘어가면 break
                break;
            } else {
                int n = getCompressedStringLength(unit, s);
                answer = Math.min(answer, n);
            }
            unit++;
        }

        return answer;
    }

    public static int getCompressedStringLength(int unit, String s) {   //문자열 찾기 구간
        StringBuilder compressedString = new StringBuilder();
        String current = s.substring(0, unit);  //하나의 일치하는 스트링 단위
        int end = 0;
        
        //문자열을 자르는 단위만큼 잘라서 다음 단위와 일치하는지
        //일치한다면 그 수를 count해서 newString에 넣어준다
        
        for (int i = unit; i < s.length(); i += unit) {
            int count = 1;

            while (isSame(i , s, unit, current)) {
                count++;
                i = i + unit;
            }
            if (count > 1) {    //count가 2이상일때
                compressedString.append(Integer.toString(count));   //newString에 count와 반복되는 string(one)을 넣는다                System.out.println(newString);
            }
            compressedString.append(current);

            if (i + unit > s.length()) {    //범위 체크
                end = i;
                break;
            }
            current = s.substring(i, i + unit);
        }
        
        if (end <= s.length()) {    //만일 남은 unit단위 보다 작은 문자열이 남았을시 더해준다.
            compressedString.append(s.substring(end));
        }
        return compressedString.length();
    }
    
    public static boolean isSame(int i , String s, int unit, String oneUnit){
        return i <= s.length() - unit && s.substring(i, i + unit).equals(oneUnit);
    }
}
