package jihye.thisiscodingtest.part03.Q01_adventureGuild;

import java.util.Arrays;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int arr[] = new int[N];

        //값을 받는다.
        for(int i=0; i < N; i ++){
            arr[i] = sc.nextInt();
        }

        //정렬
        Arrays.sort(arr);

        int groupPeople = 0;
        int group = 0;

        for(int i=0; i < arr.length;i++){
            groupPeople++;
            if(groupPeople >= arr[i]){//만약 그룹안에 사람수가 공포도 이상이면
                group++;//그룹수 증가
                groupPeople=0;//새로운 그룹위해 사람수 초기화
            }
        }
        System.out.println(group);
    }
}

//전체 사람수에서 계속 겁이 많은 사람이 필요하는 사람수를 빼는식으로 접근했음

//        int groupCount = 0;//그룹의 수
//        int peopleCount = arr.length;//사람의 수
//        int curr = arr.length-1;//현재


//        //그룹people은 남은 사람중 가장 겁 많은 사람이 필요로하는 사람 수
//        int groupPeople = arr[peopleCount-1];
//        while(true){
//            peopleCount -= arr[curr];//전체 사람에서 가장 겁이 많은 필요하는 사람의 수를 뺀다.
//            if(peopleCount <= 0){
//                break;
//            }else{
//                groupCount++;
//                groupPeople = arr[peopleCount-1];
//            }
//            while(peopleCount < groupPeople){
//                peopleCount--;
//                if(peopleCount < 0){
//                    break;
//                }
//                groupPeople+= arr[peopleCount];
//            }if(peopleCount >= groupPeople){
//                groupCount++;
//            }else{
//
//            }
//        } System.out.println(groupCount);

