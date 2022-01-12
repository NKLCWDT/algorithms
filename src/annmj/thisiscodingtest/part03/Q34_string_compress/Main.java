package annmj.thisiscodingtest.part03.Q34_string_compress;


class Solution {
    static final int MAX_LENGTH = 1000;

    public int solution(String s) {
        int maxUnit = s.length();
        int length = MAX_LENGTH; // 가장 짧은 것의 길이
        int unit = 1; // 문자열을 나누는 단위
        while (unit <= maxUnit) {
            String result = ""; // 문자열 압축 결과
            for (int i = 0; i < s.length(); ) {
                StringBuilder prev = new StringBuilder();
                int count = 1; // 같은 묶음의 개수
                for (int j = i; j < i + unit && j < s.length(); j++) {
                    prev.append(s.charAt(j));
                }

                for (int j = i + unit; j < s.length(); j++) {
                    if (s.charAt(j) != prev.toString().charAt(j % unit)) {
                        break;
                    }

                    if ((j+1) % unit == 0) {
                        count++;
                    }
                }
                i = i + count * unit;
                if (count != 1) {
                    result += String.valueOf(count) + prev;
                }

                if (count == 1) {
                    result += prev;
                }
            }
            length = Math.min(result.length(), length);
            unit++;
        }
        return length;
    }
}
