package com.soverby.algorithms.trie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Trie {
    private Node<Character> rootNode = new Node<>();

    public void add(String word) {
        rootNode.add(word.toCharArray());
    }

    public Set<Character> nextChars(String wordPart) {
        return rootNode.nextChars(wordPart.toCharArray());
    }

    public static class Node<Character> {
        private Character character = null;
        private boolean isWordStop = false;
        private Map<Character, Node> nodeMap = new HashMap<>();

        public Node() {
            super();
        }

        public Node(Character character) {
            this.character = character;
        }

        public void add(char[] word) {
            if(word.length > 0) {
                Node node = null;

                if (!nodeMap.containsKey(word[0])) {
                    node = new Node(word[0]);
                } else {
                    node = nodeMap.get(word[0]);
                }

                add(Arrays.copyOfRange(word, 1, word.length));
            }
        }

        public Set<Character> nextChars(char[] wordPart) {
            if(wordPart.length > 1) {
                if (!nodeMap.containsKey(wordPart[0])) {
                    return new HashSet<>();
                } else {
                    return nextChars(Arrays.copyOfRange(wordPart, 1, wordPart.length));
                }
            }

            return nodeMap.keySet();
        }
    }


}
