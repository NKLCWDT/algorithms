package seohae.thiscodingtest.part04.chapter3.S01_change;

public class Main {
    public static void main(String[] args) {
        int n = 1260;
        int[] coinTypes = {500, 100, 50, 10};

        int cnt = 0;

        /* 거스름돈 계산 */
        for (int coinType : coinTypes) {
            /* 나눈 몫이 해당 코인의 사용 개수 */
            cnt += (n / coinType);

            /* 잔여금액 계산 */
            n = n % coinType;
        }

        System.out.println(cnt);
    }
}
