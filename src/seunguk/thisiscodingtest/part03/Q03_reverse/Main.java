package seunguk.thisiscodingtest.part03.Q03_reverse;

import java.util.Scanner;

// 11101101 이 있을 때 숫자가 바뀔 때 (1 -> 0, 0 -> 1) 두 가지 경우중
// 횟수가 더 작은 횟수 출력

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String exampleString = sc.nextLine();

        char[] exampleChars = exampleString.toCharArray();
        int oneToZero = 0; // 1이였다가 0으로 바뀐 카운트
        int zeroToOne = 0; // 0이였다가 1로 바뀐 카운트
        boolean check = false;

        // 1 -> 0 바뀌는 카운트 세준다.
        for (char exampleChar : exampleChars) {
            if (check) { // check가 true이면 1에서 0으로 바뀌었다는 것
                if (exampleChar == '0') { // true일 때 0이면 연속되는 0이므로 카운트를 세지않고 continue
                    continue;
                } else { // 1인경우는 다시 check를 false로 바꿔준다.
                    check = false;
                }
            }
            if (exampleChar == '0') { // check가 false 이므로 현재 0인 경우는 1 -> 0 으로 바뀐 상태이므로 카운트 증가
                oneToZero++;
                check = true;
            }
        }

        check = false; // check 초기화

        // 0 -> 1 바뀌는 카운트 세준다.
        for (char exampleChar : exampleChars) {
            if (check) {
                if (exampleChar == '1') {
                    continue;
                } else {
                    check = false;
                }
            }

            if (exampleChar == '1') {
                zeroToOne++;
                check = true;
            }
        }

        System.out.println(Math.min(oneToZero, zeroToOne));
    }
}
