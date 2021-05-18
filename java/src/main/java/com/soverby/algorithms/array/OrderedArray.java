package com.soverby.algorithms.array;

public class OrderedArray {

    private long[] innerArray;
    private int pointer = 0;

    public OrderedArray(int initialCapacity) {
        innerArray = new long[initialCapacity];
    }

    public int find(long key) {
        int start = 0;
        int end = innerArray.length - 1;
        int mid = -1;

        while(start <= end) {
            mid = (int) Math.floor(start + (end - start) / 2);

            if(innerArray[mid] == key) {
                return mid;
            }

            if(innerArray[mid] < key) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        return mid;
    }

    // Temp - must insert ordered right now and respect capacity
    public void insert(long l) {
        innerArray[pointer] = l;
        pointer++;
    }

    public void delete(long l) {

    }
}
