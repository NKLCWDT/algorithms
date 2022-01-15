package seunguk.thisiscodingtest.part03.Q09_stringcompression;


public class Solution {
    public int solution(String s) {
        int answer = s.length();
        for (int i = 1; i <= s.length() / 2; i++) { // 문자열을 압축할수 있는범위는 문자열에 절반까지이므로 절반까지 탐색
            String prev = s.substring(0, i); // 비교값
            int count = 1;
            int j = i; // j는 문자열 자를 시작값
            StringBuilder sb = new StringBuilder(); // 압축한 문자열 담을 변수
            String now = ""; // 현재 자른 문자열 값
            String last = ""; // 자르고 남은값
            while(true) {
                if (j + i > s.length()) { // 문자열길이를 벗어나면 자르지 못하고 남은값을 last에 넣어준다.
                    last = s.substring(j);
                    break;
                }
                now = s.substring(j, j + i);
                if (!prev.equals(now)) { // 이전값이랑 현재자른값이랑 비교하여 다르다면 answer에 더해준다.
                    if (count > 1) { // 카운트가 1보다 크다면 압축된게 있기때문에 count + prev를 answer에 합쳐준다.
                        sb.append(count);
                        count = 1;
                    }
                    sb.append(prev);
                    prev = now;
                } else { // 이전값이랑 현재값이랑 같다면 count 증가
                    count++;
                }
                j += i; // 문자열 자를 시작값 압축 단위 만큼 증가
            }
            // while문에서는 이전값만 더하므로 while문 끝나고 남은 현재값도 더해줘야한다.
            if (count > 1) {
                sb.append(count);
            }
            sb.append(now).append(last);
            answer = Math.min(answer, sb.length());
        }
        return answer;
    }
}

