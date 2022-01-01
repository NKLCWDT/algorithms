package jihye.thisiscodingtest.part03.Q42_entrance;

import java.util.Arrays;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int G = sc.nextInt();
        int P = sc.nextInt();
        //비행기가 도킹가능한 탑승구 정보
        int[] info = new int[P];
        for (int i = 0; i < P; i++) {
            info[i] = sc.nextInt();
        }
        //작은것 부터 큰 순서로 정렬
        Arrays.sort(info);

        //탑승구가 비어있는지 확인하기 위한 배열
        int[] entrance = new int[G + 1];

        int index = 0;//info의 index를 확인하는 용도
        int temp = 1;//entrance의 index를 확인하는 용도
        int answer =0;
        while (true) {
            //인덱스가 인포의 길이 보다 커지면 break
            if (index >= info.length) {
                break;
            }
            //entrance배열을 돌면서 비어있지 않으면 다음 인덱스로
            while (temp < entrance.length &&
                    entrance[temp] != 0 && temp < info[index]) {
                temp++;
            }
            //만약 entrance가 비어있을 시 1로 차지됨을 표기
            if(entrance[temp] == 0){
                entrance[temp] = 1;
                answer++;
            }else{//비어있지 않다면 temp가 비행기가 도킹할 수 있는 최대 공간이랑 비교 후 같다면 break
                if(temp == info[index]){
                    break;
                }
            }
            index++;
        }
        System.out.println(answer);
    }
}
