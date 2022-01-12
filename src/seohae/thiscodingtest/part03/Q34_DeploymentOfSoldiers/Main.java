package seohae.thiscodingtest.part03.Q34_DeploymentOfSoldiers;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/*
    N명의 병사가 무작위로 나열되어있다.
    각 병사는 특정한 값의 전투력을 보유하고 있고,
    전투력이 높은 병사가 앞쪽에 오도록 내림차순으로 배치하고자 한다.
    특정한 위치에 있는 병사를 열외시키는 방법으로 배치한다.
    -> 전투력 순으로 내림차순

    남아있는 병사의 수는 최대여야한다.

    참고 : https://sskl660.tistory.com/89
 */
public class Main {
    static int N;
    static int[] arr;
    static int[] DP;

    public static void main(String[] args) {
        input();

        /*
         7
         15 11 4 8 5 2 4
         */
        for (int i = 0; i < N; i++) { // i 는 1번 인덱스부터
            int current = arr[i];

            // 자기 자신의 개수 1 셋팅
            DP[i] = 1;

            for (int j = 0; j < i; j++) { // j 는 처음부터 i 직전까지
                if (current < arr[j]) { // 내림차순이므로 앞에꺼가 더 크면
                    // 현재는 i 번째가 대상이다.
                    DP[i] = Math.max(DP[i], DP[j] + 1);
                }
            }
        }

        // DP 안의 최댓값이 내림차순으로 최대 배열의 길이다.
        OptionalInt max = Arrays.stream(DP, 0, N).max();

        System.out.println(arr.length - max.getAsInt());
    }

    /*
       7
       15 11 4 8 5 2 4
     */
    private static void input() {
        Scanner scanner = new Scanner(System.in);

        N = scanner.nextInt();
        arr = new int[N];
        DP = new int[N];

        for (int i = 0; i < N; i++) {
            arr[i] = scanner.nextInt();
        }
    }

     /** 실패 */
//    public static void main(String[] args) {
//        input();
//
//        int index = N - 1;
//        while (index > 0) {
//            // 왼쪽이 현재 요소보다 작다면 remove
//            if (list.get(index - 1) < list.get(index)) {
//                list.remove(index - 1);
//                count++;
//            }
//
//            index--;
//        }
//
//        list.stream().forEach(System.out::println);
//        System.out.println(count);
//    }
}
