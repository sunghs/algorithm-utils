package sunghs.algorithmutils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ArrayEx {

    /**
     * primitive 배열을 Box타입 Arraylist로 변환 (배열 -> arraylist)
     */
    @Test
    public void primitive_array_to_arrayList() {
        int[] arr = {1,2,3,4,5};
        ArrayList<Integer> list = new ArrayList<>(Arrays.stream(arr).boxed().collect(Collectors.toList()));

        list.forEach(o -> System.out.print(o + " "));
    }

    /**
     * arraylist 중복 제거
     */
    @Test
    public void distinct_arraylist() {
        List<String> list = List.of("1", "3", "9", "2", "3", "4", "1", "7", "0", "1");
        List<String> distinctList = list.stream().distinct().collect(Collectors.toList());

        list.forEach(o -> System.out.print(o + " "));
        System.out.println();
        distinctList.forEach(o -> System.out.print(o + " "));
    }
}
