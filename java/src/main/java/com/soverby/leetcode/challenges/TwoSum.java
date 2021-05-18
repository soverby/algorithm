package com.soverby.leetcode.challenges;

// https://leetcode.com/problems/two-sum/


import static java.lang.String.format;

// Given an array of integers nums and an integer target, return indices of the two numbers such
// that they add up to target.
//
// You may assume that each input would have exactly one solution, and you may not use the same element twice.
//
// You can return the answer in any order.
public class TwoSum {

    public static void main(String[] args) {
        TwoSum tsum = new TwoSum();
        tsum.solve();
    }

    public void solve() {
        int target = 4;
        int[] input = { 3, 4, 7, -3 };
        int[] result = twoSum(input, target);
        System.out.println(format("result: (%s, %s)", result[0], result[1]));
    }

    public int[] twoSum(int[] nums, int target) {
        int[] solution = new int[2];

        // For each element of the array
        // Calculate what value we would need so that element + X = target
        // Do we have X in the array somewhere?
        for(int i = 0; i < nums.length; i++) {
            int target_value = target - nums[i];
            int index_in_target = findInArray(nums, target_value, i);
            if(index_in_target > -1) {
                solution[0] = index_in_target;
                solution[1] = i;
                return solution;
            }
            System.out.println(format("i: %d, nums[i]: %d, target_value: %d", i, nums[i], target_value));

        }

        return solution;
    }

    public int findInArray(int[] nums, int target, int omit_idx) {
        for(int i = 0; i < nums.length; i++) {
            if(i != omit_idx && nums[i] == target) {
                return i;
            }
        }
        return -1;
    }
}
