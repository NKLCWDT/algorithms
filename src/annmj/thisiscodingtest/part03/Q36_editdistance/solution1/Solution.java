package annmj.thisiscodingtest.part03.Q36_editdistance.solution1;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String A = scanner.next();
        String B = scanner.next();
        Queue<Character> AQueue = new LinkedList<>();
        Queue<Character> BQueue = new LinkedList<>();
        for (char a : A.toCharArray()) {
            AQueue.offer(a);
        }

        for (char b : B.toCharArray()) {
            BQueue.offer(b);
        }

        int distance = 0;
        int currentIndex = 0;
        while (!AQueue.isEmpty()) {
            char a = AQueue.peek();
            char b = BQueue.peek();

            /* 같으면 넘어간다 */
            if (a == b) {
                AQueue.poll();
                BQueue.poll();

                /* 만들어야 할 문자열의 인덱스를 가리킨다. cut 에서 currentIndex 가 1이면 u 를 가리킴. */
                currentIndex++;
            } else {

                int findIndex = B.indexOf(a, currentIndex);
                /* 다르면서도, 해당 문자가 없는 경우 */
                if (findIndex == -1) {
                    /* replace 해주는 경우이다. */
                    AQueue.poll();
                    BQueue.poll();

                    /* B의 다음번을 탐색해야 하므로 ++ 해준다. */
                    currentIndex++;

                    /* replace 이므로 distance++ 해준다. */
                    distance++;
                } else {

                    /* 같은 경우 Bqueue 에서 빼주어야 한다.*/
                    while (BQueue.peek() != a) {
                        BQueue.poll();
                        distance++;
                        currentIndex++;
                    }
                }
            }
        }
        System.out.println(distance);
    }
}
