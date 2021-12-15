package annmj.thisiscodingtest.part03.Q01_adventure;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {

    static ArrayList<Integer> arrayList = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        for (int i = 0; i < N; i++) {
            arrayList.add(scanner.nextInt());
        }

        Collections.sort(arrayList); // 오름 차순 정렬

        int count = 0; // 그룹에 포함된 모험가 수
        int group = 0; // 그룹 수
        for (int member : arrayList) {
            count++; // 그룹에 포함 시키기
            if (count >= member) { // 현재의 공포도 이상이라면 그룹 결성 시키기
                group++;
                count = 0; // 그룹에 포함된 모험가 수 초기화
            }
        }

        System.out.println(group);
    }
}
