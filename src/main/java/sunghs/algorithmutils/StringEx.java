package sunghs.algorithmutils;

import java.util.Arrays;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class StringEx {

    /**
     * 문자열 오름차순 정렬, 아스키코드 기준, 0-9A-Za-z
     * @param s 정렬 안 된 문자열
     * @return 정렬 된 문자열
     */
    public static String asc(String s) {
        char[] arr = s.toCharArray();
        Arrays.sort(arr);
        return new String(arr);
    }

    /**
     * 문자열 내림차순 정렬, z-aZ-A9-0
     * @param s 정렬 안 된 문자열
     * @return 정렬 된 문자열
     */
    public static String desc(String s) {
        char[] arr = s.toCharArray();
        Arrays.sort(arr);
        return new StringBuffer(new String(arr)).reverse().toString();
    }

    public static String distinct(String s) {
        String[] arr = s.split("");
        return Arrays.stream(arr).distinct().collect(Collectors.joining());
    }

    /**
     * 문자열 자르기
     * "abcde".substring(1,3) = bc
     * @param s 문자열
     * @param x 시작위치 (포함)
     * @param y 종료위치 (포함안됨)
     * @return 잘린문자열
     */
    public static String sub1(String s, int x, int y) {
        return s.substring(x, y);
    }

    @Test
    public void test() {
        String s = "anxd5g3m0D1AqzK8";
        log.info("asc : {}", asc(s));
        log.info("desc : {}", desc(s));

        s = "ssssdddccvvvvd";
        log.info("distinct : {}", distinct(s));

        log.info(sub1("abcde", 1, 2));
        log.info("abcde".substring(1));
    }
}
