package jihye.thisiscodingtest.part03.Q31_goldMine;

import java.util.Scanner;

/*
금과에서 첫번째 열의 어느행에서(자유) 출발해 m번에 걸쳐서 가장 많은 금을 캘 수 있는 양

금광
1 3 3 2
2 1 4 1
0 6 4 7

(2,1)2+(3,2)6+(3,3)4+(3,4)7 = 19

 */
public class Solution {
    static int N;
    static int M;
    static int[][] gold;
    static int[] answer;
    static int count;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();
        count = 0;
        answer = new int[T];
        //테스트의 수 만큼 진행
        for (int i = 0; i < T; i++) {
            N = sc.nextInt();
            M = sc.nextInt();
            gold = new int[N][M];
            //금광 값 받는다
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < M; k++) {
                    gold[j][k] = sc.nextInt();
                }
            }
            getGold();
        }

    }

    public static void getGold() {
        int[][] d = new int[N][M];
        int y = 0;

        //gold를 첫번째줄만 복사해온다(아무곳에서 출발할 수 있으므로)
        for (int i = 0; i < N; i++) {
            for(int j=0; j< M; j++){
                d[i][j] = gold[i][j];
            }
        }
        int leftUp = 0;
        int leftDown = 0;
        int left = 0;
        for (int j = 1; j < M; j++) {
            for (int i = 0; i < N; i++) {
                //왼쪽 위에서 오는 경우
                if (i == 0) {//i가 0 이면 왼쪽 위의 값이 없어서
                    leftUp = 0;
                } else {
                    leftUp = d[i - 1][j - 1];
                }

                //왼쪽 아래에서 오는 경우
                if (i == N - 1) {//i가 N-1 이면 왼쪽 아래의 값이 없어서
                    leftDown = 0;
                } else {
                    leftDown = d[i + 1][j - 1];
                }
                //왼쪽에서 오는 경우
                left = d[i][j - 1];
                //d[i][j] = gold[i][j] + Math.max(d[i-1][j-1], d[i][j-1], d[i+1][j-1]
                d[i][j] = d[i][j] + Math.max(Math.max(leftUp, leftDown),left);
            }
        }
        int result=0;
        for (int i = 0; i < N; i++) {
            result = Math.max(result, d[i][M-1]);
        }
        answer[count] = result;
        count++;
    }
}
