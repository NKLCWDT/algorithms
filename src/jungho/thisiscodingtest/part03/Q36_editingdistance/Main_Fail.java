package jungho.thisiscodingtest.part03.Q36_editingdistance;

/*
    문자열 A 를 편집하여 문자열 B 를 만들려고 함
    문자열 A 편집할 때 3가지 연산 사용 가능

    1. INSERT : 특정한 위치에 문자 하나 삽입
    2. REMOVE : 특정한 위치에 있는 하나의 문자를 삭제
    3. REPLACE : 특정한 위치에 있는 하나의 문자를 다른 문자로 교체

    편집 거리 : 문자열 A 를 B 로 만들기 위해 사용한 연산의 수
    최소 편집 거리를 구하시오

    최대 편집 거리는 문자열 B 의 길이

    -- 문제 풀이 후
    입력값
        saturdayz
        sundaymorning
    넣었을 때, 10 나와야하는 데 7 나옴.. 실패
*/

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// 편집 거리
public class Main_Fail {

    static String A;
    static String B;
    static int[] dp;

    public static void main(String[] args) {
        input();
        System.out.println(solution());
    }

    static void input() {
        Scanner sc = new Scanner(System.in);
        A = sc.nextLine();
        B = sc.nextLine();
        dp = new int[B.length()];
    }

    static int solution() {
        // 최대 편집 거리는 문자열 B 의 길이
        // 문자열 B 의 길이로 초기화
        dp[0] = B.length();

        List<Character> wordsA = new ArrayList<>();
        for (int i = 0; i < A.length(); i++) {
            wordsA.add(A.charAt(i));
        }

        List<Character> wordsB = new ArrayList<>();
        for (int i = 0; i < B.length(); i++) {
            wordsB.add(B.charAt(i));
        }

        for (int i = 0; i < B.length(); i++) {
            int index = A.indexOf(B.charAt(i));
            if(index != -1) {
                Character character = B.charAt(i);
                int indexA = wordsA.indexOf(character);
                int indexB = wordsB.indexOf(character);

                /*
                    둘 다 같이 제거해야 하므로 둘 중 하나라도 List 에 원소가 없으면 삭제 불가능
                    wordsB.remove(character) 로 하면
                    character 가 a 일때 List 에 존재하는 a 객체를 전부 제거하므로
                    index 로 제거하는 방식으로 함
                 */
                if(indexA != -1 && indexB != -1) {
                    wordsA.remove(indexA);
                    wordsB.remove(indexB);
                }
            }
        }

        /*
            wordsA 의 길이가 wordsB 보다 긴 경우 + wordsB 는 empty -> 이때 필요한 연산은 삭제 연산
            A : Sunday
            B : Sunda
         */
        if(wordsA.size() > wordsB.size() && wordsB.isEmpty()) {
            return wordsA.size();
        }

        /*
            wordsA 의 길이가 wordsB 보다 긴 경우 + wordsB 는 not empty -> 이때 필요한 연산은 삭제 연산 + 교체연산
            A : SundayM -> yM
            B : Sundaz -> z
         */
        if(wordsA.size() > wordsB.size() && !wordsB.isEmpty()) {
            return wordsA.size() + wordsB.size();
        }

        /*
            wordsA 의 길이가 wordsB 랑 같은 경우 -> 이때 필요한 연산은 교체 연산
            A : Sunday
            B : Sundaz
         */
        if(wordsA.size() == wordsB.size()) {
            return wordsA.size();
        }

        /*
            wordsA 의 길이가 wordsB 보다 작은 경우 + wordsA 는 empty -> 이때 필요한 연산은 삽입 연산
            A : Sunday
            B : SundayMorning
         */
        if(wordsA.size() < wordsB.size() && wordsA.isEmpty()) {
            return wordsB.size();
        }

        /*
            wordsA 의 길이가 wordsB 보다 작은 경우 + wordsA 는 not empty -> 이때 필요한 연산은 삽입 연산 + 교체연산
            A : Sundayz -> z
            B : SundayMorning -> Morning
         */
        if(wordsA.size() < wordsB.size() && !wordsA.isEmpty()) {
            return wordsB.size();
        }

        return 0;
    }
}
