package LeetCode2.Greedy;
import java.util.*;

class HuffmanNode {
    char ch;
    int freq;
    HuffmanNode left, right;

    HuffmanNode(char ch, int freq) {
        this.ch = ch;
        this.freq = freq;
    }
}

class HuffmanComparator implements Comparator<HuffmanNode> {
    public int compare(HuffmanNode a, HuffmanNode b) {
        return a.freq - b.freq; // Min heap based on frequency
    }
}

public class HuffmanEncoding {

    public static Map<Character, String> buildHuffmanCodes(char[] chars, int[] freqs) {
        PriorityQueue<HuffmanNode> pq = new PriorityQueue<>(new HuffmanComparator());

        // Step 1: Put all characters into the min-heap
        for (int i = 0; i < chars.length; i++) {
            pq.add(new HuffmanNode(chars[i], freqs[i]));
        }

        // Step 2: Build Huffman Tree
        while (pq.size() > 1) {
            HuffmanNode left = pq.poll();
            HuffmanNode right = pq.poll();

            HuffmanNode combined = new HuffmanNode('-', left.freq + right.freq);
            combined.left = left;
            combined.right = right;

            pq.add(combined);
        }

        // Step 3: Traverse tree and assign codes
        HuffmanNode root = pq.poll();
        Map<Character, String> huffmanCodes = new HashMap<>();
        generateCodes(root, "", huffmanCodes);
        return huffmanCodes;
    }

    private static void generateCodes(HuffmanNode node, String code, Map<Character, String> map) {
        if (node == null) return;

        if (node.left == null && node.right == null) {
            map.put(node.ch, code);
        }

        generateCodes(node.left, code + "0", map);
        generateCodes(node.right, code + "1", map);
    }

    public static void main(String[] args) {
        char[] chars = {'A', 'B', 'C', 'D', 'E', 'F'};
        int[] freqs = {5, 9, 12, 13, 16, 45};

        Map<Character, String> codes = buildHuffmanCodes(chars, freqs);

        System.out.println("Huffman Codes:");
        for (char c : codes.keySet()) {
            System.out.println(c + ": " + codes.get(c));
        }
    }
}