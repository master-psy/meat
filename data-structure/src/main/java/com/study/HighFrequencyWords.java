package com.study;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 求数组前w个高频词
 *
 * @Desc
 */
public class HighFrequencyWords {
    /**
     * 求前w个高频词
     *
     * @param args
     */
    public static void main(String[] args) {
        int w = 4;
        int[] nums = new int[]{1, 1, 1, 1, 2, 3, 5, 4, 6, 75, 4, 8, 4, 55, 6};
        int[] toArray = Arrays.stream(nums)
                              .boxed()
                              .collect(Collectors.toMap(k -> k, v -> 1, Integer::sum))
                              .entrySet()
                              .stream()
                              .sorted((m1, m2) -> m2.getValue() - m1.getValue())
                              .limit(w)
                              .mapToInt(Map.Entry::getKey)
                              .toArray();
        System.out.println(Arrays.toString(toArray));

        String[] message = new String[]{"aaa", "aaa", "vvvv", "12545", "ffff", "ffff", "222"};
        String[] array = Arrays.stream(message)
                               .collect(Collectors.toMap(k -> k, v -> 1, Integer::sum))
                               .entrySet()
                               .stream()
                               .sorted((k1, k2) -> k2.getValue() - k1.getValue())
                               .limit(w)
                               .map(Map.Entry::getKey)
                               .toArray(String[]::new);
        System.out.println(Arrays.toString(array));
    }
}
