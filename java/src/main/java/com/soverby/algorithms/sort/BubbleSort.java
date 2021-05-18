package com.soverby.algorithms.sort;

import java.lang.reflect.Array;
import java.util.Comparator;
import java.util.stream.IntStream;

public class BubbleSort {

    public static void main(String[] args) {
        Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        };

        Integer[] array = new Integer[10];
        int value = 10;
        for(int i = 0; i < array.length; i++) {
            array[i] = value;
            value--;
        }

        Integer[] sorted = BubbleSort.sort(array, comparator);
        System.out.println(" here ");
    }

    public static <E> E[] sort(E[] array, Comparator<E> comparator) {
        int lastIndex = array.length - 1;

        while(lastIndex > 0) {
            for(int i = 0; i < lastIndex; i++) {
                if(comparator.compare(array[i], array[i + 1]) > 0) {
                    E tmp = array[i + 1];
                    array[i + 1] = array[i];
                    array[i] = tmp;
                }
            }

            lastIndex--;
        }

        return array;
    }


}
