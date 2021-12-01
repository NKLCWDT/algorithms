package seohae.chapter4.S03_royalNights;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/*
  8 x 8
  이동은 L 자로만 가능
  1) 수평으로 두칸 이동뒤 수직으로 한칸 이동
  2) 수직으로 두칸 이동뒤 수평으로 한칸 이동

  (위/아래 : x (행), 왼/오 : y(열))
  - 오른쪽 2칸, 위로 1칸
  (-1, 2)
  - 오른쪽 2칸, 아래로 1칸
  (1, 2)
  - 왼쪽으로 2칸, 위로 1칸
  (-2, -2)
  - 왼쪽으로 2칸, 아래로 1칸
  (1, -2)
  - 아래로 2칸, 오른쪽으로 1칸
  (1, 2)
  - 위로 2칸, 오른쪽으로 1칸
  (1, -2)
  - 아래로 2칸, 왼쪽으로 1칸
  (-1, 2)
  - 위로 2칸, 왼쪽으로 1칸
  (-1, -2)
 */
public class Main {
    public static void main(String[] args) {
        /* 8개 열의 map */
        Map<String, Integer> map = new HashMap<>();
        map.put("a", 1);
        map.put("b", 2);
        map.put("c", 3);
        map.put("d", 4);
        map.put("e", 5);
        map.put("f", 6);
        map.put("g", 7);
        map.put("h", 8);

        Scanner sc = new Scanner(System.in);

        String inputData = sc.nextLine();

        // 열
        int column = map.get(String.valueOf(inputData.charAt(0)));
        // 행
        int row = inputData.charAt(1) - '0';

        /* 이동범위 선언 */
        int[] dx = {0, -1, 1, -2, 1, 1, 1, -1, -1};
        int[] dy = {0, 2, 2, -2, -2, 2, -2, 2, -2};

        int result = 0;

        /* 이동 가능 로직 횟수 카운트 */
        for (int i = 1; i < 9; i++) {
            int nx = row + dx[i]; // 행 이동
            int ny = column + dy[i]; // 열 이동

            /* case1. 행 이동 0 보다 크고 9보다 작음 */
            /* case2. 열 이동 0 보다 크고 9보다 작음 */
            if (nx > 0 && ny < 9 && nx < 9 && ny > 0) {
                result = result + 1;
            }
        }

        System.out.println(result);
    }
}
