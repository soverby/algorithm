package com.soverby.algorithms.heap;

import org.junit.Test;

import static com.soverby.algorithms.heap.BinaryHeapMax.parentIndexFunction;
import static org.assertj.core.api.Assertions.assertThat;

public class BinaryHeapMaxTest {

    @Test
    public void parentIndexFunction_givenZero_thenNegOne() {
        assertThat(parentIndexFunction.apply(0)).isEqualTo(-1);
    }

    @Test
    public void parentIndexFunction_givenOne_thenZero() {
        assertThat(parentIndexFunction.apply(1)).isEqualTo(0);
    }

    @Test
    public void parentIndexFunction_givenTwo_thenZero() {
        assertThat(parentIndexFunction.apply(2)).isEqualTo(0);
    }

    @Test
    public void parentIndexFunction_givenThree_thenOne() {
        assertThat(parentIndexFunction.apply(3)).isEqualTo(1);
    }

    @Test
    public void parentIndexFunction_givenFour_thenOne() {
        assertThat(parentIndexFunction.apply(4)).isEqualTo(1);
    }

    @Test
    public void parentIndexFunction_givenFive_thenTwo() {
        assertThat(parentIndexFunction.apply(5)).isEqualTo(2);
    }

    @Test
    public void parentIndexFunction_givenSix_thenTwo() {
        assertThat(parentIndexFunction.apply(6)).isEqualTo(2);
    }
}
