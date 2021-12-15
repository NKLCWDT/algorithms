package jihye.thisiscodingtest.part2.chapter5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class escapeMaze {
    public static int N;
    public static int M;
    public static boolean[][] visited;
    public static int[][] map;
    public static int[] dx = {-1,1,0,0};
    public static int[] dy = {0,0,-1,1};
    public static Queue<int[]> queue = new LinkedList<>();


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        visited = new boolean[N+1][M+1];
        map = new int[N+1][M+1];
        int count=0;

        //지도 생성
        for(int i=0; i < N; i++){
            map[i] = Arrays.stream(br.readLine().split(""))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }
        System.out.println(bfs(0,0));

    }
    public static int bfs(int x, int y){
        int[] arr = {x,y};
        queue.add(arr);
        visited[x][y] = true;
        visited[y][x] = true;
        while(!queue.isEmpty()){
            int[] curr = queue.poll();//현재 위치
            x = curr[0];
            y = curr[1];
            for(int i=1; i < 4;i++){//위, 아래, 양옆 총 4군데 확인
                int nx = x + dx[i];//넘어갈 위치를 담는다
                int ny = y + dy[i];
                if(nx >= 0 && nx < N && ny >= 0 && ny < M){//범위를 넘지 않는지 확인
                    if(map[nx][ny] == 1){//처음 방문한 곳인지 확인
                        map[nx][ny] = map[x][y]+1;//최단 거리를 구하기 위해 +1
                        arr[0] = nx;
                        arr[1] = ny;
                        queue.add(arr);
                    }
                }
            }
        }
        return map[N-1][M-1];
    }
}
