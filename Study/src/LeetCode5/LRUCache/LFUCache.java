package LeetCode5.LRUCache;


import java.util.*;

class LFUCache {
    private int capacity, minFreq;
    private Map<Integer, Node> cache; // Key → Node
    private Map<Integer, LinkedHashSet<Integer>> freqMap; // Frequency → Set of Keys

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.minFreq = 0;
        this.cache = new HashMap<>();
        this.freqMap = new HashMap<>();
    }

    public int get(int key) {
        if (!cache.containsKey(key)) return -1; // Not found

        Node node = cache.get(key);
        updateFrequency(node);
        return node.value;
    }

    public void put(int key, int value) {
        if (capacity == 0) return;

        if (cache.containsKey(key)) {
            Node node = cache.get(key);
            node.value = value;
            updateFrequency(node);
        } else {
            if (cache.size() >= capacity) {
                evictLFU();
            }

            Node newNode = new Node(key, value);
            cache.put(key, newNode);
            minFreq = 1;
            freqMap.computeIfAbsent(1, k -> new LinkedHashSet<>()).add(key);
        }
    }

    private void updateFrequency(Node node) {
        int oldFreq = node.freq;
        int newFreq = oldFreq + 1;
        node.freq = newFreq;

        freqMap.get(oldFreq).remove(node.key);
        if (freqMap.get(oldFreq).isEmpty()) {
            freqMap.remove(oldFreq);
            if (minFreq == oldFreq) minFreq++;
        }

        freqMap.computeIfAbsent(newFreq, k -> new LinkedHashSet<>()).add(node.key);
    }

    private void evictLFU() {
        int lfuKey = freqMap.get(minFreq).iterator().next();
        freqMap.get(minFreq).remove(lfuKey);
        if (freqMap.get(minFreq).isEmpty()) freqMap.remove(minFreq);
        cache.remove(lfuKey);
    }

    public static void main(String[] args) {
        LFUCache cache = new LFUCache(2);

        cache.put(1, 10);
        cache.put(2, 20);
        System.out.println(cache.get(1)); // Output: 10
        cache.put(3, 30); // Removes least frequently used key (2)
        System.out.println(cache.get(2)); // Output: -1 (not found)
        cache.put(4, 40); // Removes least frequently used key (3)
        System.out.println(cache.get(1)); // Output: 10
        System.out.println(cache.get(3)); // Output: -1 (not found)
        System.out.println(cache.get(4)); // Output: 40
    }
}
