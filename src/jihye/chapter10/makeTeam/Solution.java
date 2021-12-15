package jihye.chapter10.makeTeam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        List<String> list = new ArrayList<>();//답을 저장
        int[] parent = new int[N+1];

        Arrays.fill(parent, 0);//부모 테이블 초기화

        //부모 테이블에서 부모를 자기 자신으로 초기화
        for(int i=1; i <N+1; i++){
            parent[i]= i;
        }


        for(int i=0; i<M;i++){
            int selection = sc.nextInt();
            int a = sc.nextInt();
            int b = sc.nextInt();
            if(selection == 0){//0이면 팀 합치기
                unionParent(parent, a,b);
            }else{//1이면 두 학생이 같은 팀인지
                int parentA = findParent(parent,a);//각각 루트 노드 찾아서
                int parentB = findParent(parent, b);
                if(parentA == parentB){//비교해준다.
                    list.add("Yes");
                }else{
                    list.add("No");
                }
            }
        }

        for(int i=0; i < list.size();i++){
            System.out.println(list.get(i));
        }


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
