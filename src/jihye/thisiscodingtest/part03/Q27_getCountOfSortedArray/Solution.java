package jihye.thisiscodingtest.part03.Q27_getCountOfSortedArray;

import java.util.Arrays;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int x = sc.nextInt();
        sc.nextLine();
        int num[] = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int first = searchFirst(num, 0, num.length, x);
        int last = searchLast(num, 0, num.length, x);
        if (first == -1 && last == -1) {
            System.out.println(-1);
        } else {
            System.out.println(last - first + 1);
        }
    }

    public static int searchFirst(int[] num, int start, int end, int target) {
        //시작점이 항상 끝보다 작을 수 있게
        if (start > end) {
            return -1;
        }
        int mid = (start + end) / 2;
        if (mid > num.length - 1) {
            return -1;
        }
        //target 값을 가지는 원소중 가장 왼쪽
        //mid 가 0이면 제일 첫번째 값이 target값이라는 뜻
        if ((mid == 0 || target > num[mid - 1]) && num[mid] == target) {
            return mid;
        } else if (num[mid] >= target) {//만약 num[mid]값이 타겟보다 크거나 젤 왼쪽값이 아닐시에
            return searchFirst(num, start, mid - 1, target);
        } else {//num[mid]값이 타겟보다 작은경우
            return searchFirst(num, mid + 1, end, target);
        }
    }

    public static int searchLast(int[] num, int start, int end, int target) {
        //시작점이 항상 끝보다 작을 수 있게
        if (start > end) {
            return -1;
        }
        int mid = (start + end) / 2;
        if (mid > num.length - 1) {
            return -1;
        }
        //target 값을 가지는 원소중 가장 오른쪽
        //mid 가 num.length이면 제일 마지막 값이 target값이라는 뜻
        if ((mid == num.length - 1 || target < num[mid + 1]) && num[mid] == target) {
            return mid;
        } else if (num[mid] > target) {//만약 num[mid]값이 타겟보다 크거나 젤 왼쪽값이 아닐시에
            return searchLast(num, start, mid - 1, target);
        } else {//num[mid]값이 타겟보다 작은경우
            return searchLast(num, mid + 1, end, target);
        }
    }


}
