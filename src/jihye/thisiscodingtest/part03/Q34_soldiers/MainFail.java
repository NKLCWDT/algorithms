//package jihye.thisiscodingtest.part03.Q34_soldiers;
//
//import java.util.Arrays;
//import java.util.Scanner;
//
///*
//N명의 병사 나열(특정 전투력값 보유)
//전투력이 있는 병사를 앞에 배치
// */
//public class Main {
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        int N = sc.nextInt();
//
//        int [] level = Arrays.stream(sc.nextLine().split(" "))
//                .mapToInt(Integer::parseInt).toArray();
//
//        int dp[] = new int[2000];
//        dp[0] = 1;
//        int prev = level[0];
//        int split = 0;
//
//        for(int i=1; i< N; i++){
//            if(prev < level[i]){
//                for(int j = i; j >= 0; j--){
//                    if(level[j] > level[i]){
//                        dp[i] = Math.max(dp[i-1]+ (i-j), dp[i]);
//                        prev = level[j];
//                        break;
//                    }
//                }
//            }
//            dp[i] = Math.max(dp[i], )
//        }
//    }
//}
