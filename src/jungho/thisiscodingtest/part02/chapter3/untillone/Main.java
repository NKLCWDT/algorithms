package jungho.thisiscodingtest.part02.chapter3.untillone;

import java.util.Scanner;

// 1이 될 때까지
public class Main {

    // 내 풀이
    public long solution(long n, long k) {
        int answer = 0;
        while(n > 1) {
            if(isDivisor(n, k)) {
                n /= k;
            } else {
                n--;
            }
            answer++;
        }
        return answer;
    }

    // 책 풀이 소스 : 정답 확인 용
    public long solution2(long n, long k) {
        long result = 0;
        while (true) {
            // N이 K로 나누어 떨어지는 수가 될 때까지만 1씩 빼기
            long target = (n / k) * k;
            result += (n - target);
            n = target;
            // N이 K보다 작을 때 (더 이상 나눌 수 없을 때) 반복문 탈출
            if (n < k) break;
            // K로 나누기
            result += 1;
            n /= k;
        }

        // 마지막으로 남은 수에 대하여 1씩 빼기
        result += (n - 1);

        return result;
    }

    // 약수인지 판단
    public boolean isDivisor(long n, long k) {
        return n % k == 0;
    }


    public static void main(String[] args) {
        Main T = new Main();
        Scanner sc = new Scanner(System.in);
        long n = sc.nextLong();
        long k = sc.nextLong();
        System.out.println(T.solution2(n, k));
    }

}
