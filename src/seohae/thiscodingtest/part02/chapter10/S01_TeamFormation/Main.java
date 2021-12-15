package seohae.thiscodingtest.part02.chapter10.S01_TeamFormation;

import java.util.Scanner;

/*
   학생 : 0번 ~ N번 (총 N명)
   모든 학생이 서로 다른팀으로 구분되어 총 N + 1 개의 팀 존재
   1. 팀 합치기 ( 0 a b )
   2. 같은 팀 여부 확인 ( 1 a b ) : 두 학생이 같은 팀인가?

   선생님이 M 개의 연산을 수행할 수 있을때 '같은 팀 여부 확인' 연산에 대한 연산결과 출력

   > 팀을 합치고, 같은 팀인지 확인하는 거 -> 서로소 집합

   서로소 집합은 두 종류의 연산을 지원한다고 했다.
   1) 합집합 (Union)
    두개의 원소가 포함된 집합을 하나의 집합으로 합친다.
   2) 찾기 (Find)
    특정한 원소가 속한 집합이 어떤 집합인지 알려준다.
 */
public class Main {
    public static int v; // 노드의 개수(V)
    public static int m; // 연산 개수

    // 노드의 개수는 최대 100,000개라고 가정
    public static int[] parent = new int[100001];

    // 특정 원소가 속한 집합을 찾기
    public static int findParent(int x) {
        // 루트 노드가 아니라면, 루트 노드를 찾을 때까지 재귀적으로 호출
        if (x == parent[x]) {
            return x;
        }

        return parent[x] = findParent(parent[x]);
    }

    // 두 원소가 속한 집합을 합치기
    public static void unionParent(int a, int b) {
        a = findParent(a);
        b = findParent(b);

        if (a < b) {
            parent[b] = a;
        } else {
            parent[a] = b;
        }
    }

    /*
       7 8
       0 1 3
       1 1 7
       0 7 6
       1 7 1
       0 3 7
       0 4 2
       0 1 1
       1 1 1
     */
    public static void main(String[] args) {
        /* input */
        Scanner sc = new Scanner(System.in);

        v = sc.nextInt(); // node 개수
        m = sc.nextInt(); // 연산 개수

        // 부모 테이블상에서, 부모를 자기 자신으로 초기화
        for (int i = 1; i <= v; i++) {
            parent[i] = i;
        }

        /* 연산 개수만큼 수행 */
        for (int i = 0; i < m; i++) {
            int flag = sc.nextInt();
            int a = sc.nextInt();
            int b = sc.nextInt();

            // flag a b
            // flag = 1) 같은 팀 여부 확인
            // flag = 0) 팀 합치기

            if (flag == 0) {
                // 팁 합치기
                unionParent(a, b);
            } else {
                // 같은 팀 여부 (parent 가 같다면 같은 팀)
                if (findParent(a) == findParent(b)) {
                    System.out.println("YES");
                }
                else {
                    System.out.println("NO");
                }
            }
        }
    }
}
