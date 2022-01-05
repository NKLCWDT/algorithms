//package jihye.thisiscodingtest.part03.Q13_chicken;
//
//import java.util.ArrayList;
//import java.util.Scanner;
//
//public class Solution {
//    /*
//    치킨거리: 집과 가장 가까운 치킨집 사이의 거리
//    도시의 치킨거리: 모든 집의 치킨 거리의 합
//    0: 빈칸
//    1: 집
//    2: 치킨집
//    집(2,1)과
//    치킨집(1,2) 의 거리: |2-1| + |1-2| = 2
//    치킨집(5,5) 의 거리: |2-5| + |1-5| = 7
//    ==> 치킨 거리는 2
//
//    치킨집 중에 M개의 가장 많은 수입을 낼 수 있는 치킨집을 제외하고 폐쇄하고
//    도시의 치킨 거리가 가장 작을 수 있는 거리
//     */
//
//    static ArrayList<int[]> chickens;
//    static ArrayList<int[]> houses;
//    static boolean[] open;
//    static int N;
//    static int M;
//    static int answer;
//
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        N = sc.nextInt();
//        M = sc.nextInt();
//
//        chickens = new ArrayList<>();
//        houses = new ArrayList<>();
//
//        //치킨집의 좌표를 저장후에
//        //집의 좌표를 저장후에
//        //둘이 따로 나중에 비교해서 가장 작은 값을 구한다
//        int[] coordinate;
//        for (int i = 0; i < N; i++) {
//            for (int j = 0; j < N; j++) {
//                int curr = sc.nextInt();
//                coordinate = new int[2];
//                coordinate[0] = i;
//                coordinate[1] = j;
//                if (curr == 1) {
//                    houses.add(coordinate);
//                }
//                if (curr == 2) {
//                    chickens.add(coordinate);
//                }
//            }
//        }
//        answer = Integer.MAX_VALUE;
//        open = new boolean[chickens.size()];
//        combination(0, 0);
//        System.out.println(answer);
//    }
//
//    public static void combination(int index, int count) {
//        if (count == M) {//M개로 이루어진 조합이 되었을 때
//            int distance = 0;
//            for (int[] house : houses) {
//                int minDist = Integer.MAX_VALUE;
//                for (int i = 0; i < open.length; i++) {
//                    if (open[i]) {
//                        //현재 있는 집에서 치킨집까지의 거리
//                        int currDist = Math.abs(house[0] - chickens.get(i)[0]) + Math.abs(house[1] - chickens.get(i)[1]);
//                        //집에서 최소한의 거리가 걸리는 치킨집까지의 거리를 찾기 위해
//                        minDist = Math.min(minDist, currDist);
//                    }
//                }
//                //최소한의 거리를 도시의 치킨 거리를 구하기 위해 더해준다.
//                distance += minDist;
//            }
//            //조합들 중에서도 최소한의 거리를 찾아야 하므로
//            answer = Math.min(answer, distance);
//        }
//
//
//        //백트래킹으로 조합을 만들어 준다.
//        //M개로 이루어진 치킨집의 조합
//        for (int i = index; i < open.length; i++) {
//            open[i] = true;
//            combination(i + 1, count + 1);
//            open[i] = false;
//        }
//    }
//}
