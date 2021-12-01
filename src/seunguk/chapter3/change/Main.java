package seunguk.chapter3.change;

public class Main {
    public static void main(String[] args) {
        int n = 1260;
        int count = 0;

        int[] coin_types = {500, 100, 50, 10};

        for (int coin_type : coin_types) {
            count += n / coin_type;
            n %= coin_type;
        }

        System.out.println(count);
    }
}
