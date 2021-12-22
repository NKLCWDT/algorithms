package seohae.thiscodingtest.part03.Q26_CardSort;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

/*
   정렬된 두 묶음의 숫자 카드
   각 묶음의 카드의 수를 A, B 라고 하면 보통 두 묶음을 합쳐서 하나로 만드는데에는 A + B 비교
   20장의 숫자카드, 30장의 숫자 카드 합치려면 50번의 비교가 필요

   10장, 20장, 40장

   10장 + 20장 = 30장 -> 30장 + 40장 = 70장
   총 100장

   10장 + 40장 = 50장 -> 50장 + 20장 -> 70장
   총 120장

   최소한 몇번의 비교가 필요한지?
   위 예제에서는 100장 vs 120장 이므로 100장

   카드 묶음의 수를 정렬
   10, 20, 40
   앞에 2개를 더하고, 해당 값을 다음 묶음과 더하고..

 */
public class Main {
    private static int n;

    /* 우선순위 큐 선언 */
    private static PriorityQueue<Integer> queue = new PriorityQueue<>();
    private static List<Integer> list = new ArrayList<>();

    public static void main(String[] args) {
        input();

        int next = queue.poll();
        int sum = 0;

        while (!queue.isEmpty()) {
            sum = next + queue.poll();
            next = sum;
            list.add(sum);
        }

        System.out.println(list.stream().mapToInt(i -> i).sum());
    }

    /*
      3
      10
      20
      40
     */
    private static void input() {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            queue.add(sc.nextInt());
        }
    }
}
