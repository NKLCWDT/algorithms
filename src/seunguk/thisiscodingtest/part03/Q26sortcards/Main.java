package seunguk.thisiscodingtest.part03.Q26sortcards;


import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // 맨처음에 정렬해준다음 순서대로 누적으로 더해주면 되는줄 알았는데
        // 계산된 값을 바로 사용하는게 아니라 계산된 값과 남아있는값을 비교하여 더 작은 값을 사용해야해서
        // 계속 가장 작은 값을 가져올 수 있는 우선순위 큐를 사용해야한다.
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int result = 0;
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            queue.add(sc.nextInt());
        }

        while(queue.size() != 1) { // queue.size가 1일 경우 모든 값을 계산했다.
            int a = queue.remove();
            int b = queue.remove();
            queue.add(a+b);
            result += a+b;
        }

        System.out.println(result);

    }
}
