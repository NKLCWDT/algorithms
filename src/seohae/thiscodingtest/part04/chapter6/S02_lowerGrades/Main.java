package seohae.thiscodingtest.part04.chapter6.S02_lowerGrades;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/*
   이름과 성적으로 구분
   성적이 낮은 순서대로의 학생의 이름 출력
 */
public class Main {
    static Map<String, Integer> map = new HashMap<>();
    static int N;

    /**
     * 입력받기
     */
    private static void input() {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();

        for (int i = 0; i < N; i++) {
            String s = sc.next();
            int grade = sc.nextInt();

            map.put(s, grade);
        }
    }

    public static void main(String[] args) {
        input();

        /* Stream 사용 */
        map.entrySet().stream()
                .sorted(Map.Entry.comparingByValue()) /* value 정렬 */
                .map(Map.Entry::getKey) /* key String type */
                .forEach(System.out::println); /* 출력 */
    }
}
