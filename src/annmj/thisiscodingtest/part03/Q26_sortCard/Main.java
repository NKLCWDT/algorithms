package annmj.thisiscodingtest.part03.Q26_sortCard;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {

    static int N;
    static PriorityQueue<Long> array = new PriorityQueue<>(Comparator.comparingLong(Long::longValue));
    static void input() {
        Scanner scanner = new Scanner(System.in);
        N = scanner.nextInt();
        for (int i = 0; i < N; i++) {
            array.add(scanner.nextLong());
        }
    }
    static void process() {
        long sum = 0;
        while (!array.isEmpty() && array.size() > 1) {
            long a1 = array.poll();
            long a2 = array.poll();
            sum += a1 + a2;
            array.add(a1 + a2);
        }
        System.out.println(sum);
    }
    public static void main(String[] args) {
        input();
        process();
    }
}
