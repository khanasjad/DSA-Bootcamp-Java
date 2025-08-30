package LeetCode4.KMPRabinKarp;

// ===== SUBSTRING SEARCH: KMP vs RABIN-KARP =====
// PROBLEM: Find if pattern exists in text, and where

import java.util.*;

public class SubstringSearch {

    /*
    🎯 THE PROBLEM IN SIMPLE TERMS:

    Text: "ABABCABABA"
    Pattern: "ABABA"
    Question: Does pattern exist in text? Where?
    Answer: YES, at index 5!

    Like: "Find the word 'cat' in the sentence 'The cat is sleeping'"
    */

    // ===== SOLUTION 1: BRUTE FORCE (for comparison) =====
    public int bruteForceSearch(String text, String pattern) {
        /*
        🐌 BRUTE FORCE APPROACH:
        Try every possible starting position in text
        For each position, check if pattern matches character by character
        */

        System.out.println("🐌 BRUTE FORCE SEARCH:");
        System.out.println("Text: \"" + text + "\"");
        System.out.println("Pattern: \"" + pattern + "\"");
        System.out.println();

        int n = text.length();
        int m = pattern.length();

        for (int i = 0; i <= n - m; i++) {
            System.out.print("Checking position " + i + ": ");
            boolean match = true;

            for (int j = 0; j < m; j++) {
                if (text.charAt(i + j) != pattern.charAt(j)) {
                    match = false;
                    break;
                }
            }

            if (match) {
                System.out.println("✅ FOUND at index " + i);
                return i;
            } else {
                System.out.println("❌ No match");
            }
        }

        System.out.println("Not found!");
        return -1;
    }

    // ===== SOLUTION 2: RABIN-KARP (Rolling Hash) =====
    public int rabinKarpSearch(String text, String pattern) {
        /*
        🎲 RABIN-KARP IDEA:
        Instead of comparing characters, compare "fingerprints" (hashes)

        Like: Instead of reading entire ID card, just check ID number
        Much faster to compare numbers than strings!

        ROLLING HASH = Update fingerprint efficiently when sliding window
        */

        System.out.println("🎲 RABIN-KARP SEARCH:");
        System.out.println("Text: \"" + text + "\"");
        System.out.println("Pattern: \"" + pattern + "\"");
        System.out.println();

        int n = text.length();
        int m = pattern.length();

        if (m > n) return -1;

        int base = 29;
        int mod = 1000000007;

        // Step 1: Calculate hash of pattern
        long patternHash = 0;
        long basePowerM = 1; // base^(m-1) for removing leftmost character

        for (int i = 0; i < m; i++) {
            patternHash = (patternHash * base + (pattern.charAt(i) - 'a' + 1)) % mod;
            if (i < m - 1) {
                basePowerM = (basePowerM * base) % mod;
            }
        }

        System.out.println("🔑 Pattern hash: " + patternHash);
        System.out.println();

        // Step 2: Rolling hash through text
        long textHash = 0;

        for (int i = 0; i < n; i++) {
            // Add new character to hash (extend window to right)
            textHash = (textHash * base + (text.charAt(i) - 'a' + 1)) % mod;

            // If window is larger than pattern, remove leftmost character
            if (i >= m) {
                char leftChar = text.charAt(i - m);
                textHash = (textHash - (leftChar - 'a' + 1) * basePowerM % mod + mod) % mod;
            }

            // Check if we have a full window and hashes match
            if (i >= m - 1) {
                int windowStart = i - m + 1;
                String currentWindow = text.substring(windowStart, i + 1);

                System.out.println("Position " + windowStart + ": window=\"" + currentWindow +
                        "\", hash=" + textHash);

                if (textHash == patternHash) {
                    // Hash match! Verify with actual string comparison
                    if (text.substring(windowStart, i + 1).equals(pattern)) {
                        System.out.println("✅ FOUND at index " + windowStart);
                        return windowStart;
                    } else {
                        System.out.println("❌ Hash collision (rare!)");
                    }
                }
            }
        }

        System.out.println("Not found!");
        return -1;
    }

    // ===== SOLUTION 3: KMP ALGORITHM =====
    public int KMPSearch(String text, String pattern) {
        /*
        🧠 KMP GENIUS IDEA:
        When mismatch happens, don't go back to beginning!
        Use previous partial matches to skip ahead smartly

        Like: If you're looking for "ABABA" and found "ABA" then mismatch,
        you know the next "AB" might be start of pattern, so skip there!
        */

        System.out.println("🧠 KMP SEARCH:");
        System.out.println("Text: \"" + text + "\"");
        System.out.println("Pattern: \"" + pattern + "\"");
        System.out.println();

        if (pattern.length() == 0) return 0;

        // Step 1: Build LPS (Longest Proper Prefix which is also Suffix) array
        int[] lps = buildLPS(pattern);
        System.out.println("📊 LPS array for pattern: " + Arrays.toString(lps));
        System.out.println();

        int i = 0; // Index for text
        int j = 0; // Index for pattern

        while (i < text.length()) {
            System.out.println("Comparing text[" + i + "]='" + text.charAt(i) +
                    "' with pattern[" + j + "]='" + pattern.charAt(j) + "'");

            if (text.charAt(i) == pattern.charAt(j)) {
                System.out.println("✅ Match! Moving both pointers");
                i++;
                j++;
            }

            if (j == pattern.length()) {
                System.out.println("🎉 PATTERN FOUND at index " + (i - j));
                return i - j;
            } else if (i < text.length() && text.charAt(i) != pattern.charAt(j)) {
                if (j != 0) {
                    System.out.println("❌ Mismatch! Using LPS to skip: j=" + j + " → j=" + lps[j-1]);
                    j = lps[j - 1]; // Smart skip using LPS
                } else {
                    System.out.println("❌ Mismatch at start, moving text pointer");
                    i++;
                }
            }
            System.out.println();
        }

        System.out.println("Not found!");
        return -1;
    }

    private int[] buildLPS(String pattern) {
        /*
        🔧 BUILD LPS ARRAY:

        LPS[i] = length of longest proper prefix of pattern[0...i]
                 which is also suffix of pattern[0...i]

        Example: pattern = "ABABA"
        LPS = [0, 0, 1, 2, 3]

        Why LPS[4] = 3?
        In "ABABA", the prefix "ABA" (3 chars) equals suffix "ABA"
        */

        int m = pattern.length();
        int[] lps = new int[m];
        int len = 0; // Length of current matching prefix
        int i = 1;

        System.out.println("🔧 Building LPS array for: \"" + pattern + "\"");

        while (i < m) {
            System.out.println("Comparing pattern[" + i + "]='" + pattern.charAt(i) +
                    "' with pattern[" + len + "]='" + pattern.charAt(len) + "'");

            if (pattern.charAt(i) == pattern.charAt(len)) {
                len++;
                lps[i] = len;
                System.out.println("✅ Match! lps[" + i + "] = " + len);
                i++;
            } else {
                if (len != 0) {
                    len = lps[len - 1];
                    System.out.println("❌ Mismatch! Backtrack: len = " + len);
                } else {
                    lps[i] = 0;
                    System.out.println("❌ No match, lps[" + i + "] = 0");
                    i++;
                }
            }
        }

        System.out.println("📊 Final LPS: " + Arrays.toString(lps));
        return lps;
    }

    // ===== COMPARISON AND TESTING =====
    public static void main(String[] args) {
        SubstringSearch searcher = new SubstringSearch();

        String text = "ABABCABABA";
        String pattern = "ABABA";

        System.out.println("🔍 SUBSTRING SEARCH COMPARISON");
        System.out.println("=".repeat(50));
        System.out.println("Text: \"" + text + "\"");
        System.out.println("Pattern: \"" + pattern + "\"");
        System.out.println("=".repeat(50));
        System.out.println();

        // Test all three approaches
        System.out.println("1️⃣ BRUTE FORCE:");
        searcher.bruteForceSearch(text, pattern);
        System.out.println("\n" + "=".repeat(50) + "\n");

        System.out.println("2️⃣ RABIN-KARP:");
        searcher.rabinKarpSearch(text, pattern);
        System.out.println("\n" + "=".repeat(50) + "\n");

        System.out.println("3️⃣ KMP:");
        searcher.KMPSearch(text, pattern);

        // Summary
        System.out.println("\n🏆 ALGORITHM COMPARISON:");
        System.out.println("┌─────────────┬─────────────┬─────────────┬─────────────────┐");
        System.out.println("│ Algorithm   │ Time Avg   │ Time Worst  │ Space           │");
        System.out.println("├─────────────┼─────────────┼─────────────┼─────────────────┤");
        System.out.println("│ Brute Force │ O(n*m)      │ O(n*m)      │ O(1)            │");
        System.out.println("│ Rabin-Karp  │ O(n+m)      │ O(n*m)      │ O(1)            │");
        System.out.println("│ KMP         │ O(n+m)      │ O(n+m)      │ O(m)            │");
        System.out.println("└─────────────┴─────────────┴─────────────┴─────────────────┘");
    }
}

/*
🎯 WHEN TO USE WHICH ALGORITHM?

🐌 BRUTE FORCE:
✅ Good for: Short patterns, simple implementation
❌ Bad for: Long patterns, lots of repetition
💭 Think: "Check every possible spot, one by one"

🎲 RABIN-KARP:
✅ Good for: Multiple pattern search, average case performance
❌ Bad for: Lots of hash collisions, worst case scenarios
💭 Think: "Compare fingerprints instead of actual strings"

🧠 KMP:
✅ Good for: Guaranteed O(n+m) time, patterns with repetition
❌ Bad for: Complex to implement, needs extra space
💭 Think: "Learn from partial matches, never go backwards"

🚀 FOR INTERVIEWS:
- Start with brute force (show you understand the problem)
- Then optimize to KMP or Rabin-Karp (show advanced knowledge)
- Explain trade-offs clearly

🔥 COMMON LEETCODE PROBLEMS:
- LeetCode 28: Find the Index of the First Occurrence in a String
- LeetCode 214: Shortest Palindrome (uses KMP trick)
- LeetCode 1392: Longest Happy Prefix (direct KMP application)

💡 KEY INSIGHT:
Both algorithms avoid the "restart from scratch" problem of brute force:
- KMP: Uses pattern analysis to skip intelligently
- Rabin-Karp: Uses rolling hash to compare quickly
*/