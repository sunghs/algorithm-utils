package sunghs.algorithmutils;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class GCD {

    /**
     * 최대공약수, 유클리드 호제법 recursive
     *
     * @param a 구할 수 1
     * @param b 구할 수 2
     * @return 최대 공약수
     */
    public static int gcd(int a, int b) {
        if (a == b) {
            return b;
        }

        int first = Math.max(a, b); // a > b ? a : b
        int second = Math.min(a, b);

        if (first % second == 0) {
            return second;
        } else { // 유클리드 호제법
            return gcd(second, first % second);
        }
    }

    @Test
    public void test() {
        int a = 888;
        int b = 220;
        log.info("gcd : {}", gcd(a, b));
    }
}
