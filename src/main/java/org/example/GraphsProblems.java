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
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        List<List<Integer>> result = new ArrayList<>();
        if(heights == null || heights.length == 0 || heights[0].length == 0){
            return result;
        }
        int m = heights.length;
        int n = heights[0].length;
        boolean[][] pacific = new boolean[m][n];
        boolean[][] atlantic = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            dfsForAtlanticPacific(heights, pacific, Integer.MIN_VALUE, i, 0);
            dfsForAtlanticPacific(heights, atlantic, Integer.MIN_VALUE, i, n - 1);
        }
        for (int i = 0; i < n; i++) {
            dfsForAtlanticPacific(heights, pacific, Integer.MIN_VALUE, 0, i);
            dfsForAtlanticPacific(heights, atlantic, Integer.MIN_VALUE, m - 1, i);
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(pacific[i][j] && atlantic[i][j]){
                    result.add(Arrays.asList(i,j));
                }
            }
        }
        return result;
    }
    private void dfsForAtlanticPacific(int[][] heights, boolean[][] visited, int heigh, int x, int y){
        if(x < 0 || x >= heights.length || y < 0 || y >= heights[0].length || visited[x][y] || heights[x][y] < heigh){
            return;
        }
        visited[x][y] = true;
        dfsForAtlanticPacific(heights, visited, heights[x][y], x + 1, y);
        dfsForAtlanticPacific(heights, visited, heights[x][y], x - 1, y);
        dfsForAtlanticPacific(heights, visited, heights[x][y], x, y + 1);
        dfsForAtlanticPacific(heights, visited, heights[x][y], x, y - 1);
    }
    public void solve(char[][] board) {
        if(board == null || board.length == 0) return;
        int m = board.length;
        int n = board[0].length;

        for (int i = 0; i < m; i++) {
            if(board[i][0] == 'O' ) dfsForSolve(board,i,0);
            if(board[i][n-1] == 'O') dfsForSolve(board, i, n-1);
        }
        for (int j = 0; j < n; j++) {
            if(board[0][j] == 'O') dfsForSolve(board, 0, j);
            if(board[m-1][j] == 'O') dfsForSolve(board, m-1, j);
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(board[i][j] == 'A') board[i][j] = 'O';
                else if(board[i][j] == 'O') board[i][j] = 'X';
            }
        }
    }
    private void dfsForSolve(char[][] board, int i, int j){
        if(i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] != 'O'){
            return;
        }
        board[i][j] = 'A';
        dfsForSolve(board, i + 1, j);
        dfsForSolve(board, i - 1, j);
        dfsForSolve(board, i, j + 1);
        dfsForSolve(board, i, j - 1);
    }
}
