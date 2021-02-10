package com.soverby.algorithms.heap;

import com.soverby.algorithms.heap.BinaryHeapIntMin;
import org.junit.Before;
import org.junit.Test;

import static java.lang.String.format;
import static org.assertj.core.api.Assertions.assertThat;

public class BinaryHeapIntMinTest {

    private final BinaryHeapIntMin bhim = new BinaryHeapIntMin();

    @Before
    public void init() {
        bhim.add(60);
        bhim.add(50);
        bhim.add(61);
        bhim.add(49);
        bhim.add(52);
        bhim.add(43);
    }

    @Test
    public void peek_test() {
        assertThat(bhim.peek()).isEqualTo(43);
        bhim.add(23);
        assertThat(bhim.peek()).isEqualTo(23);
    }

    @Test
    public void poll() {
        assertThat(bhim.poll()).isEqualTo(43);
        assertThat(bhim.poll()).isEqualTo(49);
        assertThat(bhim.poll()).isEqualTo(50);
        assertThat(bhim.poll()).isEqualTo(52);
        assertThat(bhim.poll()).isEqualTo(60);
        assertThat(bhim.poll()).isEqualTo(61);
    }


    @Test
    public void hasParent_when_0_then_false() {
        assertThat(bhim.hasParent.apply(0)).isFalse();
    }

    @Test
    public void getRightChildIndex_when_0_then_2() {
        assertThat(bhim.getRightChildIndex.apply(0)).isEqualTo(2);
    }

    @Test
    public void getRightChildIndex_when_2_then_6() {
        assertThat(bhim.getRightChildIndex.apply(2)).isEqualTo(6);
    }

    @Test
    public void getRightChildIndex_when_1_then_4() {
        assertThat(bhim.getRightChildIndex.apply(1)).isEqualTo(4);
    }

    @Test
    public void getLeftChildIndex_when_0_then_1() {
        assertThat(bhim.getLeftChildIndex.apply(0)).isEqualTo(1);
    }

    @Test
    public void getRightChildIndex_when_0_then_1() {
        assertThat(bhim.getRightChildIndex.apply(0)).isEqualTo(2);
    }

    @Test
    public void getLeftChildIndex_when_1_then_3() {
        assertThat(bhim.getLeftChildIndex.apply(0)).isEqualTo(1);
    }

    @Test
    public void getParentIndex_test() {
        assertThat(bhim.getParentIndex.apply(1)).isEqualTo(0);
        assertThat(bhim.getParentIndex.apply(2)).isEqualTo(0);
        assertThat(bhim.getParentIndex.apply(3)).isEqualTo(1);
        assertThat(bhim.getParentIndex.apply(4)).isEqualTo(1);
        assertThat(bhim.getParentIndex.apply(5)).isEqualTo(2);
        assertThat(bhim.getParentIndex.apply(6)).isEqualTo(2);
        assertThat(bhim.getParentIndex.apply(13)).isEqualTo(6);
        assertThat(bhim.getParentIndex.apply(14)).isEqualTo(6);
        assertThat(bhim.getParentIndex.apply(27)).isEqualTo(13);
        assertThat(bhim.getParentIndex.apply(28)).isEqualTo(13);
    }
}
