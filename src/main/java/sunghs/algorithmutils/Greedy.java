package sunghs.algorithmutils;

import java.util.Arrays;
import java.util.Collections;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class Greedy {

    /**
     * primitive 배열을 Box 배열로 변경 int[] -> Integer[]
     *
     * @param arr int[]
     * @return Integer[]
     */
    public static Integer[] primitive_to_box(int[] arr) {
        return Arrays.stream(arr).boxed().toArray(Integer[]::new);
    }

    /**
     * 거스름돈 최대한 적게 반환
     *
     * @param coins 동전의 종류
     * @param money 거스름돈
     * @return 동전의 수
     */
    public static int money_coin_count(int[] coins, int money) {
        Integer[] coins2 = primitive_to_box(coins);
        Arrays.sort(coins2, Collections.reverseOrder());
        int result = 0;
        for (int coin : coins2) {
            while (money >= coin) {
                money = money - coin;
                result++;
                log.info("count " + coin);
            }
        }
        return result;
    }

    @Test
    public void test() {
        int[] coins = {500, 100, 50, 10};
        int money = 1620;
        int result = money_coin_count(coins, money);
        log.info("{}", result);
    }
}
