package jihye.thisiscodingtest.part03.Q26_cardSort;

import java.util.PriorityQueue;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //우선순위 큐를 사용한다.
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        int N = sc.nextInt();

        for(int i=0; i< N ; i++) {
            priorityQueue.add(sc.nextInt());
        }
        int ans=0;
        while(priorityQueue.size() != 1){
            //작은 값두개를 더한다.
            int n = priorityQueue.poll() + priorityQueue.poll();
            priorityQueue.add(n);
            ans+= n;
        }
        System.out.println(ans);
    }
}
