package com.soverby.algorithms.heap;

import java.lang.reflect.Array;
import java.util.function.Function;

public class BinaryHeapMax<T> {

    private Object[] heap;
    private int pointer = 0;

    public final static Function<Integer, Integer> parentIndexFunction =
            index -> index == 0 ? -1 : (index / 2) - 1 + (index % 2 == 0 ? 0 : 1);

    public final static Function<Integer, Integer> leftNodeIndexFunction =
            index -> index * 2 + 1;

    public final static Function<Integer, Integer> rightNodeIndexFunction =
            index -> index * 2 + 2;


    public BinaryHeapMax() {
        this.heap = new Object[100];
    }

    public BinaryHeapMax(int initialSize) {
        this.heap = new Object[initialSize];
    }

    // 0 - 1, 2
    // 1 - 3, 4
    // 2 - 5, 6
    // left node = n * 2 + 1, right node = n * 2 + 2
    // parent node = ceil(n/2) - 1

    public void add(T object) {
        heap[pointer] = object;
        pointer++;
        if(pointer >= 2) {
            maxHeapify(parentIndexFunction.apply(pointer - 1));
        }

    }

    public T peek() {
        return (T) heap[0];
    }

    public T poll() {
        return (T) heap[0];
    }

    private void maxHeapify(int index) {
        T parent_node = (T) this.heap[index];
        int leftIndex = this.leftNodeIndexFunction.apply(index);
        int rightIndex = this.rightNodeIndexFunction.apply(index);

//        if(leftIndex < this.pointer && rightIndex < this.pointer) {
//            this.heap[index]
//        }
    }
}
