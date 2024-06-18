package org.example;

import java.util.*;

public class AdvancedGraphs {

    Map<String, PriorityQueue<String>> flights;
    LinkedList<String> path;
    public List<String> findItinerary(List<List<String>> tickets) {
        flights = new HashMap<>();
        path = new LinkedList<>();
        for(List<String> ticket: tickets){
            flights.putIfAbsent(ticket.get(0), new PriorityQueue<>());
            flights.get(ticket.get(0)).add(ticket.get(1));
        }
        dfs("JFK");
        return path;
    }
    public void dfs(String departure){
        PriorityQueue<String> arrivals = flights.get(departure);
        while(arrivals != null && !arrivals.isEmpty()){
            dfs(arrivals.poll());
        }
        path.addFirst(departure);
    }
    public int minCostConnectPoints(int[][] points) {
        int len = points.length;
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a,b) -> a[2] - b[2]);
        minHeap.offer(new int[]{0,0,0});

        boolean[] visited = new boolean[len];
        int result = 0;

        while(!minHeap.isEmpty()){
            int[] curr = minHeap.poll();
            int curIndex = curr[1] , curCost = curr[2];

            if(visited[curIndex]) continue;
            visited[curIndex] = true;
            result += curCost;
            for (int i = 0; i < len; i++) {
                if(!visited[i]){
                    int[] next = new int[]{curIndex, i, Math.abs(points[curIndex][0] - points[i][0]) + Math.abs(points[curIndex][1] - points[i][1])};
                    minHeap.offer(next);
                }
            }
        }
        return result;
    }
    public int networkDelayTime(int[][] times, int n, int k) {
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int[] edge : times) {
            graph.putIfAbsent(edge[0], new ArrayList<>());
            graph.get(edge[0]).add(new int[]{edge[1], edge[2]});
        }

        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        heap.offer(new int[]{0, k});

        Map<Integer, Integer> dist = new HashMap<>();

        while (!heap.isEmpty()) {
            int[] info = heap.poll();
            int d = info[0], node = info[1];
            if (dist.containsKey(node)) continue;
            dist.put(node, d);
            if (graph.containsKey(node))
                for (int[] edge : graph.get(node)) {
                    int nei = edge[0], d2 = edge[1];
                    if (!dist.containsKey(nei))
                        heap.offer(new int[]{d+d2, nei});
                }
        }

        if (dist.size() != n) return -1;

        int ans = 0;
        for (int cand : dist.values())
            ans = Math.max(ans, cand);
        return ans;
    }
}
