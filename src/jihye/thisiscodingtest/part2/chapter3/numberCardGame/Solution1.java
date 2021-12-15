package jihye.thisiscodingtest.part2.chapter3.numberCardGame;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

public class Solution1 {
    public static void main(String[] args) throws IOException {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(Collections.reverseOrder());
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        List<Integer> n = Arrays.stream(br.readLine().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        List<Integer> num_list = Arrays.stream(br.readLine().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        for(int i=0; i < n.get(0); i++){
            priorityQueue.add(num_list.get(i));
        }
        int count = 0;
        int total=0;
        int temp = 0;
        for(int i=0; i < n.get(1); i++){

            if(count == -1){
                total += priorityQueue.peek();
                priorityQueue.add(temp);
                count+=2;
                continue;
            }

            total += priorityQueue.peek();
            count++;

            if(count == n.get(2)){
                temp = priorityQueue.poll();
                count=-1;
                continue;
            }
        }
        System.out.println(total);
    }
}
