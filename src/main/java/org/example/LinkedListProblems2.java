package org.example;

import java.util.HashMap;
import java.util.Map;

public class LinkedListProblems2 {
    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
    public Node copyRandomList(Node head) {
        if( head == null){
            return null;
        }

        Map<Node, Node> map = new HashMap<>();
        Node node = head;

        //Make a copy of all the nodes
        while(node != null){
            map.put(node, new Node(node.val));
            node = node.next;
        }
        //Assign next and random pointers
        node = head;
        while(node != null){
            map.get(node).next = map.get(node.next);
            map.get(node).random = map.get(node.random);
            node = node.next;
        }
        return map.get(head);
    }
}
