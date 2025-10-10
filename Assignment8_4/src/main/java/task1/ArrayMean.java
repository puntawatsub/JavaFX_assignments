package task1;

import java.util.Arrays;

public class ArrayMean {
    public static void main(String[] args) {
        int[] intArray = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        System.out.println(Arrays.stream(intArray).reduce(0, Integer::sum) / (double) intArray.length);
    }
}
