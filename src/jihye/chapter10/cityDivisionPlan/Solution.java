package jihye.chapter10.cityDivisionPlan;

import java.util.Arrays;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int [][]graph = new int[M][3];
        int result =0;
        int[] parent = new int[N+1];

        parent[0]=0;

        //부모테이블 초기화
        for(int i=1; i< N+1;i++){
            parent[i] = i;
        }

        //간선에 대한 정보 입력
        for(int i=1; i<M; i++){
            graph[i][0] = sc.nextInt();//start
            graph[i][1] = sc.nextInt();//end
            graph[i][2] = sc.nextInt();//cost
        }

        //정렬(오름차순으로)
        Arrays.sort(graph,(a, b)-> Integer.compare(a[2],b[2]));

        int last =0;

        //간선을 하나씩 확인
        for(int i=1; i<M;i++){
            //부모가 같으면 사이클이 발생하니 -> 사이클이 발생하지 않는 경우만 포함
            if(findParent(parent,graph[i][0]) != findParent(parent,graph[i][1])){
                unionParent(parent,graph[i][0], graph[i][1]);
                result+=graph[i][2];
                last= graph[i][2];
            }
        }
        System.out.println();
        System.out.println(result - last);
    }
    public static void unionParent(int[] parent, int a, int b){
        //각 부모노드를 가져와
        a = findParent(parent, a);
        b = findParent(parent, b);

        if(a<b){//비교후 더 작은 원소가 부모노드가 된다
            parent[b] = a;
        }else{
            parent[a]= b;
        }
    }

    //루트노드 찾기
    public static int findParent(int[] parent, int x){
        if(parent[x] != x){
            parent[x] = findParent(parent, parent[x]);
        }
        return parent[x];
    }
}
