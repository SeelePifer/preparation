package org.example;

import java.util.*;

public class LinkedListProblems {
    public class ListNode {
     int val;
     ListNode next;

     ListNode() {}
     ListNode(int val) { this.val = val; }
     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode current = head;
        while(current != null){
            ListNode next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        return prev;
    }
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode();
        ListNode current = dummy;

        while (list1 != null && list2 != null){
            if(list1.val < list2.val){
                current.next = list1;
                list1 = list1.next;
            }else{
                current.next = list2;
                list2 = list2.next;
            }
            current = current.next;
        }

        if(list1 != null){
            current.next = list1;
        }else{
            current.next = list2;
        }
        return dummy.next;
    }
    public void reorderList(ListNode head) {
        if (head == null || head.next == null) {
            return;
        }

        //Find the middle of the linked list
        ListNode slow = head;
        ListNode fast = head;
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        //Reverse the second half of the linked list
        ListNode prev = null;
        ListNode current = slow;
        ListNode tmp = null;
        while(current != null){
            tmp = current.next;
            current.next = prev;
            prev = current;
            current = tmp;
        }
        //Merge the first half and reversed second half
        ListNode first = head;
        ListNode second = prev;
        while(second.next != null){
            tmp = first.next;
            first.next = second;
            first = tmp;

            tmp = second.next;
            second.next = first;
            second = tmp;
        }
    }
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode slow = dummy;
        ListNode fast = dummy;

        for (int i = 1; i <= n+1; i++) {
            slow = slow.next;
        }

        while(slow != null){
            slow = slow.next;
            fast = fast.next;
        }
        fast.next = fast.next.next;
        return dummy.next;
    }
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode();
        ListNode current = dummy;
        int carry = 0;
        while (l1 != null || l2!= null || carry != 0){
            int sum = carry;
            if(l1 != null){
                sum += l1.val;
                l1 = l1.next;
            }
            if (l2 != null){
                sum += l2.val;
                l2 = l2.next;
            }
            carry = sum / 10;
            current.next = new ListNode(sum % 10);
            current = current.next;
        }
        return dummy.next;
    }
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head.next;

        while (slow != fast) {
            if (fast == null || fast.next == null) {
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return true;
    }
    public int findDuplicate(int[] nums) {
        int slow = nums[0];
        int fast = nums[0];
        do{
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while(slow != fast);

        slow = nums[0];
        while (slow != fast){
            slow = nums[slow];
            fast = nums[fast];
        }
        return fast;
    }

    class LRUCache {
        class Node{
            int key;
            int value;
            Node prev;
            Node next;
        }
        private void addNode(Node node) {
            node.prev = head;
            node.next = head.next;

            head.next.prev = node;
            head.next = node;
        }
        private void removeNode(Node node){
            Node prev = node.prev;
            Node next = node.next;

            prev.next = next;
            next.prev = prev;
        }
        private Node popTail(){
            Node res = tail.prev;
            removeNode(res);
            return res;
        }
        private Map<Integer, Node> cache = new HashMap<>();
        private int size;
        private int capacity;
        private Node head, tail;

        private void moveToHead(Node node){
            removeNode(node);
            addNode(node);
        }

        public LRUCache(int capacity) {
            this.size = 0;
            this.capacity = capacity;
            head = new Node();
            tail = new Node();
            head.next = tail;
            tail.prev = head;
        }

        public int get(int key) {
            Node node = cache.get(key);
            if (node == null){
                return -1;
            }
            moveToHead(node);
            return node.value;
        }

        public void put(int key, int value) {
            Node node = cache.get(key);
            if(node == null){
                Node newNode = new Node();
                newNode.key = key;
                newNode.value = value;

                cache.put(key, newNode);
                addNode(newNode);

                ++size;
                if(size > capacity){
                    Node tailt = popTail();
                    cache.remove(tailt.key);
                    --size;
                }
            }else{
                node.value = value;
                moveToHead(node);
            }
        }
    }
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> queue = new PriorityQueue<>(Comparator.comparing(node -> node.val));
        for (ListNode node: lists){
            if(node != null){
                queue.add(node);
            }
        }
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        while(!queue.isEmpty()){
            ListNode node = queue.poll();
            current.next = node;
            current = current.next;
            if(node.next != null){
                queue.add(node.next);
            }
        }
        return dummy.next;
    }
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        Stack<ListNode> stack = new Stack<>();
        while(head != null){
            stack.push(head);
            head = head.next;

            if(stack.size() == k){
                ListNode next = null;
                while(!stack.isEmpty()){
                    if(next == null){
                        next = stack.pop();
                        prev.next = next;
                    }else{
                        next.next = stack.pop();
                        next = next.next;
                    }
                }
                next.next = head;
                prev = next;
            }
        }
        return dummy.next;
    }


}
