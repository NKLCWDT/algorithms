package seohae.thiscodingtest.part02.chapter10.S02_CityDivisionPlan;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/*
   N개의 집
   -> 2개로 분리해야한다.

   각 분리된 마을 안에 집들이 서로 연결되어있도록 분할 -> 신장 트리
   각 분리된 마을 안에 있는 임의의 두 집 사이에 경로가 항상 존재해야한다.

   각 분리된 마을 안에서도 임의의 두 집 사이에 경로가 항상 존재하게 하면서 길을 더 없앨 수 있다.

   마을의 이장은 위 조건을 만족하도록 길들을 모두 없애고 나머지 길의 유지비의 합을 최소로 하고싶다. -> 최소 신장 트리
 */
public class Main {
    public static int v; // 노드의 개수(V)
    public static int e; // 간선(Union 연산)의 개수(E)

    // 노드의 개수는 최대 100,000개라고 가정
    public static int[] parent = new int[100001]; // 부모 테이블 초기화하기

    // 모든 간선을 담을 리스트
    public static ArrayList<Edge> edges = new ArrayList<>();

    // 최종 비용을 담을 변수
    public static int result = 0;

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
       7 12
       1 2 3
       1 3 2
       3 2 1
       2 5 2
       3 4 4
       7 3 6
       5 1 5
       1 6 2
       6 4 1
       6 5 3
       4 5 3
       6 7 4
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        v = sc.nextInt();
        e = sc.nextInt();

        // 부모 테이블상에서, 부모를 자기 자신으로 초기화
        for (int i = 1; i <= v; i++) {
            parent[i] = i;
        }

        // 모든 간선에 대한 정보를 입력 받기
        for (int i = 0; i < e; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int cost = sc.nextInt();

            edges.add(new Edge(cost, a, b));
        }

        // 간선을 비용순으로 정렬
        Collections.sort(edges);

        int last = 0; // 최소 신장 트리에 포함되는 간선 중에서 가장 비용이 큰 간선

        // 간선을 하나씩 확인하며
        for (Edge edge : edges) {
            int cost = edge.getDistance();
            int a = edge.getNodeA();
            int b = edge.getNodeB();

            // 사이클이 발생하지 않는 경우에만 집합에 포함
            if (findParent(a) != findParent(b)) {
                unionParent(a, b);
                result += cost;

                // 정렬되어있으므로 (가장 큰 비용의 간선을 저장 : 결과에서 빼는 값)
                last = cost;
            }
        }

        System.out.println(result - last);
    }
}

class Edge implements Comparable<Edge> {

    private int distance;
    private int nodeA;
    private int nodeB;

    public Edge(int distance, int nodeA, int nodeB) {
        this.distance = distance;
        this.nodeA = nodeA;
        this.nodeB = nodeB;
    }

    public int getDistance() {
        return this.distance;
    }

    public int getNodeA() {
        return this.nodeA;
    }

    public int getNodeB() {
        return this.nodeB;
    }

    // 거리(비용)가 짧은 것이 높은 우선순위를 가지도록 설정
    @Override
    public int compareTo(Edge other) {
        if (this.distance < other.distance) {
            return -1;
        }
        return 1;
    }
}
