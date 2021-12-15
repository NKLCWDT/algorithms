//package jihye.thisiscodingtest.part2.chapter4.gameDevelopment;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.nio.Buffer;
//import java.util.Arrays;
//import java.util.stream.Stream;
//
//public class Solution1 {
//    public static void main(String[] args) throws IOException {
//        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
//        int [] info = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
//        int [][] dir = {{1,0},{0,1},{-1,0},{0,-1}};
//        int [] curr = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
//        int [] future = new int[2];
//        int [][] arr= new int[info[0]][info[1]];
//        int count = 0;
//        int allsea = 0;
//        //array 바다인지 육지인지
//        for(int i=0; i < info[0];i++){
//            String line = br.readLine();
//            for(int j=0; j < info[1];j++){
//                arr[i][j] = Character.getNumericValue(line.charAt(j));
//            }
//        }
//
//        while(true){
//            curr[2]--;
//            if(curr[2] == -1){
//                curr[2] = 3;
//            }
//            future[0]=curr[0]+dir[curr[2]][0];
//            future[1] = curr[1]+dir[curr[2]][1];
//            if(arr[curr[0]][curr[1]] != 1 && future[0]>=0 && future[0]<info[0]
//                    && future[1]>=0 && future[1] < info[1]) {//바다가 아니면 그리고 칸을 넘지 않을 시에
//                arr[curr[0]][curr[1]] = -1;
//                curr[0]+=dir[curr[2]][0];
//                curr[1]+=dir[curr[2]][1];
//                count++;
//                allsea = 0;
//            }else{
//                System.out.println("sea");
//                allsea++;
//            }
//            if(allsea == 4){
//                curr[3] = curr[3]-2;
//                if(curr[2] == -1){
//                    curr[2] = 3;
//                }
//                if(curr[2] == -2){
//                    curr[2] = 2;
//                }
//                    future[0]=curr[0]+dir[curr[2]][0];
//                    future[1] = curr[1]+dir[curr[2]][1];
//                    if(arr[curr[0]][curr[1]] == 1 || future[0]>=0 && future[0]<info[0]
//                            && future[1]>=0 && future[1] < info[1]){
//                        count++;
//                        curr[0] = future[0];
//                        curr[1] = future[1];
//                    }
//
//            }
//        }
//        System.out.println(count);
//    }
//
//}
