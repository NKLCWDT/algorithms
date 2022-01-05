package jihye.thisiscodingtest.part03.populationMovement;

import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int L = sc.nextInt();
        int R = sc.nextInt();
        int graph[][] = new int[N][N];

        for(int i=0; i < N; i++){
            for(int j=0; j < N; j++){
                graph[i][j] = sc.nextInt();
            }
        }

        int totalCount = 0;
        while (true) {
            int union[][] = new int[N][N];
            for (int i = 0; i < N; i++) {
                Arrays.fill(union[N], -1);
            }


            int index = 0;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (union[i][j] == -1) {
                        process(i,j,index);
                        index +=1;
                    }if(index == N*N){
                        break;
                    }totalCount +=1;
                }
            }
        }
        System.out.println(totalCount);

    }
    public process(int x, int y, int index){
        ArrayList<int[]> united = new ArrayList<>();
        int[] info = {x,y};
        united.add(info);

        Queue queue = new LinkedList();
        queue.add(info);
        int [][] union ;
        union[x][y] = index;
        summary
    }
}
