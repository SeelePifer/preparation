package org.example;

import java.util.*;

public class HeapProblems {
    class KthLargest {

        private PriorityQueue<Integer> queue;
        private int k;
        public KthLargest(int k, int[] nums) {
            this.k = k;
            queue = new PriorityQueue<>(k);
            for(int num : nums){
                add(num);
            }
        }

        public int add(int val) {
            queue.offer(val);
            if(queue.size() > k){
                queue.poll();
            }
            return queue.peek();
        }
    }
    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.reverseOrder());
        for(int stone : stones){
            queue.offer(stone);
        }
        while(queue.size() > 1){
            int stone1 = queue.poll();
            int stone2 = queue.poll();
            if(stone1 != stone2){
                queue.offer(stone1 - stone2);
            }
        }
        return queue.isEmpty() ? 0 : queue.poll();
    }
    public int[][] kClosest(int[][] points, int k) {
        PriorityQueue<int[]> queue = new PriorityQueue<>(k, (p1, p2) -> (p2[0] * p2[0] + p2[1] * p2[1]) - (p1[0] * p1[0] + p1[1] * p1[1]));
        for(int [] point : points){
            queue.offer(point);
            if(queue.size() > k){
                queue.poll();
            }
        }
        int[][] result = new int[k][2];
        while(k > 0){
            result[--k] = queue.poll();
        }
        return result;
    }

    public class Twitter {
        private static int timeStamp = 0;

        private Map<Integer, User> userMap;

        private class Tweet {
            public int id;
            public int time;
            public Tweet next;

            public Tweet(int id) {
                this.id = id;
                time = timeStamp++;
                next = null;
            }
        }

        public class User {
            public int id;
            public Set<Integer> followed;
            public Tweet tweetHead;

            public User(int id) {
                this.id = id;
                followed = new HashSet<>();
                follow(id); // first follow itself
                tweetHead = null;
            }

            public void follow(int id) {
                followed.add(id);
            }

            public void unfollow(int id) {
                followed.remove(id);
            }

            public void post(int id) {
                Tweet t = new Tweet(id);
                t.next = tweetHead;
                tweetHead = t;
            }
        }

        public Twitter() {
            userMap = new HashMap<>();
        }

        public void postTweet(int userId, int tweetId) {
            if (!userMap.containsKey(userId)) {
                User u = new User(userId);
                userMap.put(userId, u);
            }
            userMap.get(userId).post(tweetId);

        }

        public List<Integer> getNewsFeed(int userId) {
            List<Integer> res = new LinkedList<>();

            if (!userMap.containsKey(userId)) return res;

            PriorityQueue<Tweet> q = new PriorityQueue<>((t1, t2) -> (t2.time - t1.time));

            Set<Integer> users = userMap.get(userId).followed;
            for (int user : users) {
                Tweet t = userMap.get(user).tweetHead;
                if (t != null) {
                    q.add(t);
                }
            }

            int n = 0;
            while (!q.isEmpty() && n < 10) {
                Tweet t = q.poll();
                res.add(t.id);
                n++;
                if (t.next != null) {
                    q.add(t.next);
                }
            }

            return res;
        }

        public void follow(int followerId, int followeeId) {
            if (!userMap.containsKey(followerId)) {
                User u = new User(followerId);
                userMap.put(followerId, u);
            }
            if (!userMap.containsKey(followeeId)) {
                User u = new User(followeeId);
                userMap.put(followeeId, u);
            }
            userMap.get(followerId).follow(followeeId);
        }

        public void unfollow(int followerId, int followeeId) {
            if (!userMap.containsKey(followerId) || followerId == followeeId)
                return;
            userMap.get(followerId).unfollow(followeeId);
        }
    }
}
