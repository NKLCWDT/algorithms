package seohae.thiscodingtest.part03.Q36_EditStreet;

import java.util.Scanner;

/**
 * 두개의 문자열 A, B
 *
 * 문자열 A를 편집하여 문자열 B 를 만든다.
 *
 * 세 연산 중 한번에 하나씩 선택 가능
 * 삽입, 삭제, 교체
 *
 * 편집 거리: 문자열 A를 편집하여 문자열 B로 만들기 위해 사용한 연산의 수
 * 문자열 A를 문자열 B로 만드는 최소 편집 거리를 계산
 *
 *
 * 강의 참고 (여러번 봐야할것같다..)
 * https://www.youtube.com/watch?v=Dd_NgYVOdLk
 */
public class Main {
    static String A;
    static String B;
    static int[][] distance;
    
    public static void main(String[] args) {
        input();

        /*
           "" r e p l a c e
         "" 0 1 2 3 4 5 6 7
         d  1
         e  2
         l  3
         e  4
         t  5
         e  6

         1) r != d : 1 (replace)
         2) e != d : 1 (replace) -> 앞에 1)번의 경우를 더해서 1 + 1 = 2
         3) p != d : 1 (replace) -> 앞에 2)번의 경우를 더해서 2 + 1 = 3
         4) l 1= d : 1 (replace) -> 앞에 3)번의 경우를 더해서 3 + 1 = 4
         .
         .
         .
           "" r e p l a c e
         "" 0 1 2 3 4 5 6 7
         d  1 1 2 3 4 5 6 7
         e  2'2'1'2'
         l  3
         e  4
         t  5
         e  6

         1) r != de : [d][1] = 1 이므로 1 + 1(insert) = 2
         2) re != de : r -> d (replace) 1 이므로 아까 구했던 위에서 1)번에 해당
         3) rep != de : 2)번이 최소 1을 가지고있으므로 1 + 1(insert) = 2
         4) repl != del : 3)번의 최소 2이므로 2 + 1(insert l) = 3
         */
        for (int i = 1; i < distance.length; i++) {
            for (int j = 1; j < distance[i].length; j++) {

                if (A.charAt(i) == B.charAt(j)) {
                    distance[i][j] = distance[i-1][j-1];
                } else {
                    // 위 예시에서 4) repl != del 을 예시로 보자.
                    // i = 4, j = 2
                    // 아래 3가지 중에 min 값이 결과
                    // distance[3][2] : rep != de = 2 ---> 여기서 del 은 l 을 insert 하면 된다. + 1
                    // distance[3][1] : rep != d = 3
                    // distance[4][1] : repl != d = 4
                    int minDistance = Math.min(distance[i - 1][j], distance[i - 1][j - 1]);
                    int minCurrentDistance = Math.min(distance[i][j - 1], minDistance);

                    distance[i][j] = minCurrentDistance + 1; // 위에서 구한 결과에서 + 1(insert)
                }
            }
        }

        // 결과
        System.out.println(distance[A.length() - 1][B.length() - 1]);
    }

    private static void input() {
        Scanner scanner = new Scanner(System.in);
        A = scanner.next();
        B = scanner.next();

        distance = new int[A.length()][B.length()];

        distance[0][0] = 0; // 시작점은 0

        /*
           "" r e p l a c e
         "" 0 1 2 3 4 5 6 7
         d  1
         e  2
         l  3
         e  4
         t  5
         e  6
         */
        for (int i = 1; i < distance.length; i++) {
            // 0은 0 ("") 이므로, remove 로 하는 횟수로 채운다.
            // 0, 1, 2, 3, 4, 5, 6, 7
            distance[i][0] = i;
        }

        for (int i = 1; i < distance[0].length; i++) {
            // 0("")를 d 로 만들때 1, de 로 만들때 2, 그 다음 3자릿수 3 (insert 이므로)
            distance[0][i] = i;
        }
    }
}
