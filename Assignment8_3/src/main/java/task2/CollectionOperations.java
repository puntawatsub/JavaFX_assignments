package task2;

import java.util.ArrayList;
import java.util.List;

public class CollectionOperations {

    public static void main(String[] args) {
        List<Integer> integerList = new ArrayList<>(List.of(10, 5, 8, 20, 15, 3, 12));

        integerList.removeIf(i -> i % 2 == 0);
        integerList.replaceAll(i -> i * 2);

        int[] sum = new int[1];
        integerList.forEach(i -> sum[0] += i);

        System.out.println(sum[0]);
    }
}
