//package jihye.thisiscodingtest.part03.Q36_editing;
//
//import java.util.Scanner;
//
//public class Main {
//    static int[] dp;
//    static String A;
//    static String B;
//
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        A = sc.nextLine();
//        B = sc.nextLine();
//        StringBuilder sb = new StringBuilder();
//
//        dp = new int[B.length()];
//
//        System.out.println(findLength(sb, 0));
//    }
//
//    // 재귀
//    public static int findLength(StringBuilder current, int index) {
//        if (current.toString() == B) {
//            return dp[index];
//        }
//        if (A.charAt(index) == B.charAt(index)) {
//            dp[index] = dp[index - 1];//같다면 굳이 편집을 안해도 되니 값 유지 하지만 초기 값 설정해줘야됨
//            return findLength(current.append(A.charAt(index)), index + 1);
//        }
////        getExchangeCount(current, index);//
////        getRemoveCount();
////        getAddCount();
////        return count;
//    }
//
//    //문자를 삭제할 때의 로직
//    public int getRemoveCount(StringBuilder current, int index) {
//        int count = 0;
//        while (!(A.charAt(index) == current.charAt(index))) {//같은 문자가 나올때 까지 삭제한다
//            if (current.length() == 0) {
//                return 10000;//나올 수 없는 최대의 수
//            }
//            count++;//그 수를 count
//            current.deleteCharAt(index);
//        }
//        dp[index] = Math.min(dp[index - 1] + count, dp[index]);//기존에 dp[index]에 기록된것과 비교
//        return findLength(current, index);
//    }
//
//    //문자 더하기
//    public static int getAddCount(StringBuilder current, int index) {
//        dp[index] = Math.min(dp[index - 1] + 1, dp[index]);//문자를 더하기 때문에 +1
//        return findLength(current.append(A.charAt(index)), index + 1);
//    }
//
//    //문자 교체
//    public static void getExchangeCount(char before, char current) {
////        dp[index] = Math.min();/
////        return findLength(current.replac());
//    }
//}
