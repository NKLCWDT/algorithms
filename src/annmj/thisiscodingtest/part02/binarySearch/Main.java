package annmj.thisiscodingtest.part02.binarySearch;

public class Main {

    static int binarySearch(int[] A, int L, int R, int X) {
        /*
         * A[L...R] 에서 X 미만의 수(X 보다 작은 수) 중 제일 오른쪽 인덱스를 return 하는 함수
         * 그런게 없다면 L - 1 을 return 한다.
         */
        int result  = L -1;
        while (L <= R) {
            int mid = (L + R) / 2;
            if (A[mid] < X) {
                result = mid;
                L = mid + 1;
            } else if (A[mid] >= X) {
                R = mid - 1;
            }
        }
        return result;
    }
}
