package seunguk.thisiscodingtest.part03.Q01adventurerguild.try2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        ArrayList<Integer> arrayList = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            arrayList.add(sc.nextInt());
        }

        Collections.sort(arrayList);

        int result = 0; // 총 그룹의 수
        int count = 0; // 현재 그룹에 포함된 모험가의 수

        for (int i = 0; i < N; i++) { // 공포도를 낮은 것 부터 하나씩 확인하며
            count += 1; // 현재 그룹에 해당 모험가를 포함시키기
            if (count >= arrayList.get(i)) { // 현재 그룹에 포함된 모험가의 수가 현재의 공포도 이상이라면, 그룹 결성
                result += 1;
                count = 0;
            }
        }
        System.out.println(result);
    }
}
