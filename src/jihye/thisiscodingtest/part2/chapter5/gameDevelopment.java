package jihye.thisiscodingtest.part2.chapter5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class gameDevelopment {
    public static int N;
    public static int M;
    public static boolean [][] visited;
    public static int [][] iceMold;
    public static int count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        visited = new boolean[N+1][M+1];
        iceMold = new int[N+1][M+1];
        count=0;

        //얼음 틀 생성
        for(int i=0; i < N; i++){
            iceMold[i] = Arrays.stream(br.readLine().split(""))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        //dfs 수행
        for(int i=0; i < N; i++){
            for(int j=0; j < M; j++){
                if(dfs(i,j) == true){
                    count++;
                }
            }
        }

        System.out.println(count);

    }

    public static boolean dfs(int x, int y){
        if(x >= 0 && x < N && y >= 0 && y < M){//범위 확인
            if(!visited[x][y] && iceMold[x][y]==0){//방문확인 및 얼음공간인지확인
                visited[x][y] = true;
                dfs(x-1, y);
                dfs(x+1, y);
                dfs(x,y+1);
                dfs(x,y-1);
                return true;
            }

        }
        return false;
    }
    public static StringTokenizer readData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        return st;
    }
}
