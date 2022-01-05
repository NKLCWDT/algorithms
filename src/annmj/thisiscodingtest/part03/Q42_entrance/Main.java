package annmj.thisiscodingtest.part03.Q42_entrance;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        input();
        process();
    }

    static int G, P; // G : 1 부터 시작
    static int[] parents;
    static int[] entrances;

    private static void process() {
        int count = 0;
        for (int i = 1; i < entrances.length; i++) {
//            System.out.println("before");
//
//            for (int j = 1; j <= G; j++) {
//                System.out.print(parents[j] + " ");
//            }
//            System.out.println();
            int solution = findParent(entrances[i]);
            if (solution == 0) break; // 차선책이 0 이라면 종료

            /*
             * i 번 비행기가 entrance[i] 와 도킹을 한 상태
             * 도킹을 하고 나면, i + 1 번째 비행기는 entrance[i] 게이트와 도킹을 할 수 없고, 그것보다 작은 게이트로 도킹을 해야한다.
             * parents[i] 의 값은 각 게이트에서의 차선택을 의미
             * parents[4] = 2 라면, 비행기가 선택할 수 있는 게이트의 개수가 4라고 할때, 4,3은 이미 도킹이 되어있으니까 차선책은 2번 게이트라는 것
             */
            unionParent(solution, solution - 1);
            count++;
//            for (int j = 1; j <= G; j++) {
//                System.out.print(parents[j] + " ");
//            }
//            System.out.println();
//            System.out.println("after");

        }
        System.out.println(count);
    }

    // 차선책을 가리키도록 한다. parent : 차선책으로 생각
    // 만약, 1번 비행기의 게이트 개수가 3이라면
    // 1,2,3 중에서 가장 큰 번호의 게이트를 선택해야만, 도킹 개수가 최대가 된다.
    // 1번 게이트가 1을 선택했는데, 그 다음번 비행기의 게이트 개수가 1이라면 도킹을 최대로 할 수 없으니까.
    static int findParent(int entrance) {
        if (entrance == parents[entrance]) {
            return entrance;
        } else {
            parents[entrance] = findParent(parents[entrance]);
        }
        return parents[entrance];
    }

    // 기존 게이트의 차선책을 변경해주는 작업
    static void unionParent(int a, int b) {
        // 1, 2 라면
        int minParent = findParent(Math.min(a, b)); // 1의 부모
        int maxParent = findParent(Math.max(a, b)); // 2의 부모
//        System.out.print("maxParent = " + maxParent);
//        System.out.println(",  minParent = " + minParent);
        parents[Math.max(a, b)] = minParent;
    }

    private static void input() {
        Scanner scan = new Scanner(System.in);
        G = scan.nextInt();
        P = scan.nextInt();
        parents = new int[G + 1]; // 0 ~ G 까지
        entrances = new int[P + 1]; // 0 ~ P 까지

        for (int i = 0; i < parents.length; i++) {
            parents[i] = i; // 자기 자신을 루트로 가리키도록 초기화
        }

        for (int i = 1; i <= P; i++) {
            int maxEntrance = scan.nextInt();
            entrances[i] = maxEntrance; // 1번 비행기부터 P번 비행기 각각이 선택할 수 있는 게이트
        }
    }
}
