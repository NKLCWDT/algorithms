package seunguk.thisiscodingtest.part02.chapter6.lowergrades;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        Map<String, Integer> map = new HashMap<>();

        for (int i = 0; i < N; i++) {
            map.put(sc.next(), sc.nextInt());
        }

        // map entry 리스트
        // Entry는 Map에 구현된 interface
        List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() { // interface comparator 익명클래스 생성

            @Override // Comparator에 compare 재정의
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o1.getValue().compareTo(o2.getValue()); // Integer안에 compareTo가 재정의 되어있어서 그걸 사용한다.
            }

        });

        for (Map.Entry<String, Integer> stringIntegerEntry : list) {
            System.out.print(stringIntegerEntry.getKey() + " ");
        }
    }
}
