package org.example;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    public List<String> findWords(char[][] board, String[] words) {
        Trie trie = new Trie();
        for(String word: words){
            trie.insert(word);
        }

        Set<String> result = new HashSet<>();
        int m = board.length;
        int n = board[0].length;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dfs(board, visited, "",i,j, trie, result);
            }
        }
        return new ArrayList<>(result);
    }
    private void dfs(char[][] board, boolean[][] visited, String str, int x, int y, Trie trie, Set<String> result){
        if(x < 0 || x >= board.length || y < 0 || y >= board[0].length) return;
        if(visited[x][y]) return;

        str += board[x][y];
        if(!trie.startsWith(str)) return;
        if(trie.search(str)){
            result.add(str);
        }
        visited[x][y] = true;
        dfs(board, visited, str, x + 1, y, trie, result);
        dfs(board, visited, str, x - 1, y, trie, result);
        dfs(board, visited, str, x, y + 1, trie, result);
        dfs(board, visited, str, x, y - 1, trie, result);
        visited[x][y] = false;
    }
}
