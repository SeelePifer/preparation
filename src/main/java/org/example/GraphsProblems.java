package org.example;

import java.util.*;

public class GraphsProblems {

    public int numIslands(char[][] grid) {
        if(grid == null || grid.length == 0){
            return 0;
        }
        int numOfIslands = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if(grid[i][j] == '1'){
                    numOfIslands += dfs(grid, i, j);
                }
            }
        }
        return numOfIslands;
    }
    private int dfs(char[][] grid , int i, int j){
        if(i < 0 || i >= grid.length || j < 0 || j>= grid[i].length|| grid[i][j] == '0'){
            return 0;
        }
        grid[i][j] = '0';
        dfs(grid, i + 1, j);
        dfs(grid, i - 1, j);
        dfs(grid, i, j + 1);
        dfs(grid, i, j-1);
        return 1;
    }
    public int maxAreaOfIsland(int[][] grid) {
        if(grid == null || grid.length == 0){
            return 0;
        }
        int maxArea = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if(grid[i][j] == 1){
                    maxArea = Math.max(maxArea, dfsForMaxArea(grid, i, j));
                }
            }

        }
        return maxArea;
    }
    private int dfsForMaxArea(int[][] grid, int i, int j){
        if(i < 0 || i >= grid.length || j < 0 || j >= grid[i].length || grid[i][j] == 0){
            return 0;
        }
        grid[i][j] = 0;
        int area = 1;
        area += dfsForMaxArea(grid, i +1 , j);
        area += dfsForMaxArea(grid, i -1 , j);
        area += dfsForMaxArea(grid, i , j + 1);
        area += dfsForMaxArea(grid, i , j - 1);

        return area;
    }
    class Node {
        public int val;
        public List<Node> neighbors;
        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }
    public Node cloneGraph(Node node) {
        if(node == null){
            return null;
        }
        HashMap<Node, Node> visited = new HashMap<>();
        return clone(node, visited);
    }
    private Node clone(Node node, HashMap<Node, Node> visited){
        if(node == null){
            return null;
        }
        if (visited.containsKey(node)){
            return visited.get(node);
        }
        Node cloneNode = new Node(node.val, new ArrayList<>());
        visited.put(node, cloneNode);

        for(Node neighbor : node.neighbors){
            cloneNode.neighbors.add(clone(neighbor, visited));
        }
        return cloneNode;
    }
    public int orangesRotting(int[][] grid) {
        int[] dx = {-1,0,1,0};
        int[]dy = {0,1,0,-1};
        int fresh = 0;
        Queue<int[]> queue = new LinkedList<>();

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] == 1){
                    fresh++;
                }
                else if ( grid[i][j] == 2){
                    queue.offer(new int[]{i,j});
                }
            }
        }
        if (fresh == 0){
            return 0;
        }
        int time = -1;
        while(!queue.isEmpty()){
            time++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] point = queue.poll();
                for (int j = 0; j < 4; j++) {
                    int newX = point[0] + dx[j];
                    int newY = point[1] + dy[j];
                    if(newX >= 0 && newY >= 0 && newX < grid.length && newY < grid[0].length && grid[newX][newY] == 1){
                        grid[newX][newY] = 2;
                        queue.offer(new int[]{newX, newY});
                        fresh--;
                    }
                }
            }
        }
        if( fresh > 0){
            return -1;
        }
        return time;
    }
}
