package org.example;

public class TriesProblem {
    class Trie {
        private Node root;

        private class Node{
            private Node[] children;
            private boolean isEndOfWord;

            public Node(){
                children = new Node[26];
                isEndOfWord = false;
            }
        }
        public Trie() {
            root = new Node();
        }

        public void insert(String word) {
            Node node = root;
            for( char c: word.toCharArray()){
                if(node.children[c - 'a'] == null){
                    node.children[c - 'a'] = new Node();
                }
                node = node.children[c - 'a'];
            }
            node.isEndOfWord = true;
        }

        public boolean search(String word) {
            Node node = root;
            for( char c: word.toCharArray()){
                node = node.children[c - 'a'];
                if(node == null){
                    return false;
                }
            }
            return node.isEndOfWord;
        }

        public boolean startsWith(String prefix) {
            Node node = root;
            for( char c: prefix.toCharArray()){
                node = node.children[c - 'a'];
                if(node == null){
                    return false;
                }
            }
            return true;
        }
    }
    public class WordDictionary {
        private Node root;

        private class Node {
            private Node[] children;
            private boolean isEndOfWord;

            public Node() {
                children = new Node[26];
                isEndOfWord = false;
            }
        }

        public WordDictionary() {
            root = new Node();
        }

        public void addWord(String word) {
            Node node = root;
            for (char c : word.toCharArray()) {
                if (node.children[c - 'a'] == null) {
                    node.children[c - 'a'] = new Node();
                }
                node = node.children[c - 'a'];
            }
            node.isEndOfWord = true;
        }

        public boolean search(String word) {
            return searchInNode(word, root);
        }

        private boolean searchInNode(String word, Node node) {
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (c == '.') {
                    for (Node child : node.children) {
                        if (child != null && searchInNode(word.substring(i + 1), child)) {
                            return true;
                        }
                    }
                    return false;
                } else {
                    node = node.children[c - 'a'];
                    if (node == null) {
                        return false;
                    }
                }
            }
            return node.isEndOfWord;
        }
    }
}
