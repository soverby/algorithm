package com.soverby.algorithms.combinatorics;

import java.util.Arrays;

import static java.lang.String.format;

public class LexographicalCombinations {

    public static void main(String[] args) {
        LexographicalCombinations lc = new LexographicalCombinations();
        int[] a = {0, 1, 2, 3, 4, 5};

        lc.combo(a, 8);
        lc.combo(a, 9);
    }

    void combo(int[] a, int n) {
        System.out.println(next_combination(a, 8));
        StringBuffer sb = new StringBuffer();
        Arrays.stream(a).forEach(i -> sb.append(format("%d ", i)));
        System.out.println(sb.toString());
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-");
    }
    boolean next_combination(int[] a, int n) {
        int k = a.length;
        // iterate backwards through all the index positions from k to 0
        for (int i = k - 1; i >= 0; i--) {
            System.out.println(format("n: %d, k: %d:, i: %d", a[i], n, k, i));
            System.out.println(format("a[i] (%d) < (%d) n - k + i + 1", a[i], (n - k + i + 1)));
            // if the element at position i's value is less than
            // n - k + i + 1
            if (a[i] < n - k + i + 1) {
                System.out.println(" -- yes");
                a[i]++;
                for (int j = i + 1; j < k; j++)
                    a[j] = a[j - 1] + 1;
                return true;
            }
        }
        return false;
    }
}
