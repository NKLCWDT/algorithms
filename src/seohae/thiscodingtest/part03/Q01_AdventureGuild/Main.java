package seohae.thiscodingtest.part03.Q01_AdventureGuild;

import java.util.*;
import java.util.stream.IntStream;

public class Main {
    private static List<Integer> list = new ArrayList<>();
    private static int result;

    public static void main(String[] args) {
        input();

        /* 오름차순 정렬 */
        Collections.sort(list);

        /* list 데이터가 존재할때까지 반복 */
        while (list.size() > 0) {
            /* max 값 추출 */
            int max = list.get(list.size() - 1);

            /* remove 될 시작 인덱스 */
            int i = list.size() - 1;

            /* max 보다 남은 리스트의 개수가 작다면 더이상 그룹을 만들 수 없다. */
            if (max > list.size()) {
                break;
            }

            /* max 감소, i(인덱스) 감소하며 그룹에 포함될 원소 제거 */
            while (max > 0) {
                list.remove(i);
                max--;
                i--;
            }

            /* 결과 */
            result += 1;
        }

        System.out.println(result);
    }

    /*
      5
      2 3 1 2 2
     */

    /*
    5
    1 1 1 1 1 : 5
    4 4 4 4 4 : 1
    1 2 4 3 1 : 2
    2 3 1 2 2 : 2
     */
    private static void input() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            list.add(sc.nextInt());
        }
    }
}
