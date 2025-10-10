package task2;

import java.util.ArrayList;
import java.util.List;

public class Transforming {
    public static void main(String[] args) {
        List<Integer> integerList = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));

        System.out.println(integerList.stream().filter(i -> i % 2 != 0).map(i -> i * 2).reduce(Integer::sum).get());
    }
}
