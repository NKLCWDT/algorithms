package annmj.thisiscodingtest.part03.Q34_string_compress;


class Solution {
    static final int MAX_LENGTH = 1000;

    public int solution(String s) {
        int maxUnit = s.length();
        int answer = MAX_LENGTH;
        int unit = 1; // 문자열을 나누는 단위

        /* unit 이 최대길이(문자열의 길이)가 될 때까지 반복 */
        while (unit <= maxUnit) {
            String result = ""; // 문자열 압축 결과
            for (int i = 0; i < s.length(); ) {
                StringBuilder prev = new StringBuilder();
                int count = 1;            /* 같은 묶음의 개수, prev 와 같은 묶음의 개수 */

                /*
                * ababcd 문자열에서 unit 이 2인 경우,
                * prev 에는 ab 가 저장되고, 이후에 다시 반복문이 실행되면 ab, cd 순으로 저장되게 될 겁니다.
                *
                * ababcd 문자열에서 unit 이 5인 경우,
                * prev 에는 ababc 가 저장
                *
                * i + unit 이 s.length 넘어가면 안되므로 for 문의 조건에 추가
                * */
                for (int j = i; j < i + unit && j < s.length(); j++) {
                    prev.append(s.charAt(j));
                }

                /*
                 * prev 를 뽑았다면, prev 와 같은 문자를 압축해야 하는데,
                 * 이 때는 i + unit 부터 탐색을 해야한다.
                 */
                for (int j = i + unit; j < s.length(); j++) {
                    if (!isEqualToPrev(prev, j, s.charAt(j), unit)) {
                        break;
                    }

                    if ((j+1) % unit == 0) {
                        count++;
                    }
                }
                i = i + (count * unit);

                /* count 가 1보다 큰 경우 (ex. 2) 에만 result 에 더해준다. */
                if (count > 1) {
                    result += String.valueOf(count);
                }
                result += prev;
            }
            answer = Math.min(result.length(), answer);
            unit++;
        }
        return answer;
    }

    private boolean isEqualToPrev(StringBuilder prev, int j, char c, int unit) {
        return c == prev.toString().charAt(j % unit);
    }
}
