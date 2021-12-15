package jungho.thisiscodingtest.part02.chapter3.change;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

// 거스름돈
public class Main {

    private static final List<Integer> COINS = Arrays.asList(500, 100, 50, 10);

    public int solution(int change) {
        int answer = 0;
        for(int coin : COINS) {
            answer += (change / coin);
            change %= coin;
        }
        return answer;
    }

    public static void main(String[] args) throws IOException {
        Main T = new Main();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String n = reader.readLine();
        int change = Integer.parseInt(n);
        System.out.println(T.solution(change));
    }

}
