package com.soverby.algorithms.trie;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;

public class Test {

    private Logger log = LogManager.getLogger(Test.class);

    public static void main(String[] args) {
        Test test = new Test();
        String word = "this is a word";
        test.add(word.toCharArray());
    }

    private void add(char[] word) {
        if(word.length > 0) {
            log.info(" processing char {}", word[0]);
            add(Arrays.copyOfRange(word, 1, word.length));
        }
    }
}
