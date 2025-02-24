package LeetCode5.LRUCache;


import java.util.*;

class LRUCache {
    private int capacity;
    private Map<Integer, Node> cache;
    private Node head, tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();

        // Dummy head & tail to simplify add/remove operations
        head = new Node(0, 0);
        tail = new Node(0, 0);
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        if (!cache.containsKey(key)) return -1; // Not found

        Node node = cache.get(key);
        moveToHead(node); // Mark as most recently used
        return node.value;
    }

    public void put(int key, int value) {
        if (cache.containsKey(key)) {
            // Update value and move to the front
            Node node = cache.get(key);
            node.value = value;
            moveToHead(node);
        } else {
            if (cache.size() >= capacity) {
                // Remove least recently used (tail.prev)
                Node lru = removeTail();
                cache.remove(lru.key);
            }

            // Insert new node
            Node newNode = new Node(key, value);
            cache.put(key, newNode);
            addToHead(newNode);
        }
    }

    private void moveToHead(Node node) {
        removeNode(node);
        addToHead(node);
    }

    private void addToHead(Node node) {
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }

    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private Node removeTail() {
        Node lru = tail.prev;
        removeNode(lru);
        return lru;
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);

        cache.put(1, 10);
        cache.put(2, 20);
        System.out.println(cache.get(1)); // Output: 10
        cache.put(3, 30); // Removes key 2
        System.out.println(cache.get(2)); // Output: -1 (not found)
        cache.put(4, 40); // Removes key 1
        System.out.println(cache.get(1)); // Output: -1 (not found)
        System.out.println(cache.get(3)); // Output: 30
        System.out.println(cache.get(4)); // Output: 40
    }
}
