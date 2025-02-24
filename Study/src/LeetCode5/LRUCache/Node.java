package LeetCode5.LRUCache;

class Node {
    int key, value,freq;
    Node prev, next;

    public Node(int key, int value) {
        this.key = key;
        this.value = value;
        this.freq = 1;

    }
}