package jihye.thisiscodingtest.part2.chapter4.upDownLeftRight;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution1 {

    public static void main(String[] args) throws IOException {
        int row =1;
        int col =1;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(br.readLine());
        String [] map = br.readLine().split(" ");
        for(int i=0; i < map.length;i++){
            System.out.println(map[i]);
            if(map[i].equals("R")){
                System.out.println("R");
                if(col < size){
                    col++;
                }
            }
            if(map[i].equals("L")){
                if(col > 2 ){//1부터 시작이니
                    col--;
                }
            }
            if(map[i].equals("U")){
                if(row > 2){
                    row--;
                }
            }
            if(map[i].equals("D")){
                if(row < size){
                    row++;
                }
            }
        }
        System.out.println(row + " " + col);
    }
}
