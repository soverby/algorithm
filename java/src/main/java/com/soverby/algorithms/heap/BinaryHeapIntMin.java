package com.soverby.algorithms.heap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.function.Function;

import static java.lang.String.format;

public class BinaryHeapIntMin {

    private static final Logger log = LogManager.getLogger(BinaryHeapIntMin.class);

    private int capacity = 10;
    private int size = 0;
    private int[] heap = new int[capacity];
    private int operationCount = 0;

    final Function<Integer, Integer> getLeftChildIndex = index -> index * 2 + 1;
    final Function<Integer, Integer> getRightChildIndex = index -> index * 2 + 2;
    final Function<Integer, Integer> getParentIndex = index -> index > 0 ? (index - 1) / 2 : -1;

    final Function<Integer, Boolean> hasLeftChild = index -> getLeftChildIndex.apply(index) < size;
    final Function<Integer, Boolean> hasRightChild = index -> getRightChildIndex.apply(index) < size;
    final Function<Integer, Boolean> hasParent = index -> getParentIndex.apply(index) >= 0;

    final Function<Integer, Integer> getLeftChild = index -> heap[getLeftChildIndex.apply(index)];
    final Function<Integer, Integer> getRightChild = index -> heap[getRightChildIndex.apply(index)];
    final Function<Integer, Integer> getParent = index -> heap[getParentIndex.apply(index)];

    public void resetOperationCount() {
        operationCount = 0;
    }

    public int getOperationCount() {
        return operationCount;
    }

    public void add(int value) {
        log.debug(format("add(%d)", value));
        operationCount++;
        ensureCapacity();
        heap[size] = value;
        size++;
        heapifyUp(size - 1);
    }

    public int peek() {
        if(size == 0) throw new IllegalStateException();
        return heap[0];
    }

    public int poll() {
        if(size == 0) throw new IllegalStateException();
        operationCount++;
        int min = heap[0];
        heap[0] = heap[size - 1];
        size--;
        heapifyDown(0);
        return min;
    }

    private void ensureCapacity() {
        if(size == capacity) {
            operationCount++;
            capacity *= 2;
            heap = Arrays.copyOf(heap, capacity);
        }
    }

    private void swap(int fromIndex, int toIndex) {
        log.debug(format("swap from %d to %d", fromIndex, toIndex));
        operationCount++;
        int tmp = heap[fromIndex];
        heap[fromIndex] = heap[toIndex];
        heap[toIndex] = tmp;
    }

    void heapifyUp(int index) {
        while(hasParent.apply(index) && heap[index] < heap[getParentIndex.apply(index)]) {
            operationCount++;
            swap(index, getParentIndex.apply(index));
            index = getParentIndex.apply(index);
        }
    }

    void heapifyDown(int index) {
        while(hasLeftChild.apply(index)) {

            operationCount++;
            int leastIndex = getLeftChild.apply(index) < heap[index] ? getLeftChildIndex.apply(index) : index;

            if(hasRightChild.apply(index)) {
                operationCount++;
                leastIndex = getRightChild.apply(index) < heap[leastIndex] ? getRightChildIndex.apply(index) : leastIndex;
            }

            if(leastIndex != index) {
                swap(leastIndex, index);
            }

            index++;
        }
    }

    public void printStack() {
        int index = 0;
        StringBuffer sb = new StringBuffer("- heap: ");

        while(index < size) {
            sb.append(heap[index] + ", ");
            index++;
        }

        log.info(sb.toString());
    }

}
