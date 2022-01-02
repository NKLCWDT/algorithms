package seohae.thiscodingtest.part03.Q42_Gate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/*
  공항에 G개의 탑승구 존재
  각각의 탑승구는 1번 ~ G 번까지의 번호로 구분

  공항의 비행기 개수 P
  i번재 비행기를 위 탑승구 중 하나에 영구적으로 도킹해야함
  (다른 비행기가 도킹하지 않은 탑승구에만 도킹 가능)

  P개의 비행기를 순서대로 도킹하다가, 어떠한 탑승구도 도킹할 수 없는 비행기가 나오는 경우
  그 시점에서 공항의 운행을 중지

  공항의 관리자는 최대한 많은 비행기를 공항에 도킹해야한다.
  최대 몇대 도킹이 가능한가?

4 -> 탑승구 개수
3 -> 비행기 개수
(P개의 개수만큼 입력)
4 : 1번 ~ 4번 탑승구 중 하나에 도킹 가능
1 : 1번 ~ 1번 탑승구 중 하나에 도킹 가능
1 : 1번 ~ 1번 탑승구 중 하나에 도킹 가능

 */
public class Main {
    private static int G;
    private static int P;

    private static int[] arr;
    private static boolean[] visited;

    public static void main(String[] args) {
        input();

        int cnt = 0;

        for (int i = 1; i < arr.length; i++) {
            boolean isVisited = false;

            for (int j = arr[i]; j > 0; j--) {
                if (!visited[j]) { // 도킹되지 않았을때
                    visited[j] = true;
                    isVisited = true;
                    cnt++; // 결과 +1
                    break;
                }
            }

            // 도킹이 아예 안됬으면 break
            if (!isVisited) {
                break;
            }
        }

        System.out.println(cnt);
    }

    /*
        4
        3
        4
        1
        1

        4
        6
        2
        2
        3
        3
        4
        4
     */
    private static void input() {
        Scanner sc = new Scanner(System.in);
        G = sc.nextInt();
        P = sc.nextInt();
        visited = new boolean[G + 1];
        arr = new int[P + 1];

        for (int i = 1; i < P + 1; i++) {
            arr[i] = sc.nextInt();
        }

    }
}
