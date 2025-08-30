package LeetCode4.KMPRabinKarp;

// ===== LeetCode 214: Shortest Palindrome =====
// PROBLEM: Given string s, add characters in front to make shortest palindrome

import java.util.*;

public class ShortestPalindrome {

    /*
    🎯 PROBLEM IN SIMPLE TERMS:

    You have a word like "aacecaaa"
    You can ONLY add letters to the FRONT
    Goal: Make the shortest possible palindrome

    Example: "aacecaaa" → "aaacecaaa" (add one 'a' at front)

    KEY INSIGHT: Find the longest part that's ALREADY a palindrome starting from beginning!
    */


    // ===== SOLUTION 2: KMP ALGORITHM (Efficient) =====
    public String shortestPalindrome_KMP(String s) {
        /*
        🧠 BRILLIANT INSIGHT:

        We want to find the longest prefix of s that is also a suffix of reverse(s)

        Example: s = "aacecaaa"
        reverse = "aaacecaa"

        We create: s + "#" + reverse(s) = "aacecaaa#aaacecaa"
        Then use KMP to find longest prefix that's also suffix!

        The '#' prevents overlap between original and reversed string
        */

        if (s == null || s.length() == 0) return s;

        String rev = new StringBuilder(s).reverse().toString();
        String combined = s + "#" + rev; // Use # as separator

        // Build KMP failure function (LPS array)
        int[] lps = buildLPS(combined);

        // The value at the end tells us longest matching prefix-suffix
        int overlap = lps[combined.length() - 1];

        // Add the non-overlapping part of reverse to front of original
        return rev.substring(0, s.length() - overlap) + s;
    }

    private int[] buildLPS(String pattern) {
        /*
        LPS = Longest Proper Prefix which is also Suffix

        Example: "ABABCABAB"
        LPS[8] = 4 because "ABAB" is both prefix and suffix
        */
        int n = pattern.length();
        int[] lps = new int[n];
        int len = 0; // Length of current matching prefix
        int i = 1;

        while (i < n) {
            if (pattern.charAt(i) == pattern.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len != 0) {
                    len = lps[len - 1]; // Backtrack using LPS
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }
        return lps;
    }

    // ===== SOLUTION 3: RABIN-KARP (Rolling Hash) =====
    public String shortestPalindrome_RabinKarp(String s) {
        /*
        🎲 RABIN-KARP APPROACH:

        Use rolling hash to quickly check if prefix matches suffix of reverse
        Rolling hash = like a "fingerprint" for strings that updates efficiently

        We check: s[0...i] == reverse(s)[n-1-i...n-1] for all i
        */

        if (s == null || s.length() == 0) return s;

        int n = s.length();
        int base = 29; // Prime number for hashing
        int mod = 1000000007;

        long prefixHash = 0, suffixHash = 0;
        long basePower = 1;
        int palindromeEnd = 0;

        // Calculate hash for both prefix and corresponding suffix of reverse
        for (int i = 0; i < n; i++) {
            char frontChar = s.charAt(i);
            char backChar = s.charAt(n - 1 - i);

            // Update prefix hash: add new character at end
            prefixHash = (prefixHash * base + (frontChar - 'a' + 1)) % mod;

            // Update suffix hash: add new character at beginning
            suffixHash = (suffixHash + (backChar - 'a' + 1) * basePower) % mod;

            // Update base power for next iteration
            basePower = (basePower * base) % mod;

            // If hashes match, we found a potential palindrome prefix
            if (prefixHash == suffixHash) {
                palindromeEnd = i;
            }
        }

        // Add the remaining part from reverse to make palindrome
        String toAdd = new StringBuilder(s.substring(palindromeEnd + 1)).reverse().toString();
        return toAdd + s;
    }


    // ===== TEST ALL SOLUTIONS =====
    public static void main(String[] args) {
        ShortestPalindrome sol = new ShortestPalindrome();

        // Test cases
        String[] testCases = {"aacecaaa", "abcd", "aba", ""};

        for (String test : testCases) {
           // System.out.println("KMP:         \"" + sol.shortestPalindrome_KMP(test) + "\"");
            System.out.println("Rabin-Karp:  \"" + sol.shortestPalindrome_RabinKarp(test) + "\"");
        }

        /*
        📊 EXPECTED OUTPUTS:

        "aacecaaa" → "aaacecaaa" (add one 'a')
        "abcd" → "dcbabcd" (add "dcb")
        "aba" → "aba" (already palindrome)
        "" → "" (empty string)
        */
    }
}

/*
🎯 ALGORITHM COMPARISON:

┌─────────────┬─────────────┬─────────────┬─────────────────────┐
│ Algorithm   │ Time        │ Space       │ Difficulty          │
├─────────────┼─────────────┼─────────────┼─────────────────────┤
│ Brute Force │ O(n³)       │ O(n)        │ Easy to understand  │
│ KMP         │ O(n)        │ O(n)        │ Medium              │
│ Rabin-Karp  │ O(n)        │ O(1)        │ Medium              │
│ Manacher's  │ O(n)        │ O(n)        │ Hard                │
└─────────────┴─────────────┴─────────────┴─────────────────────┘

🚀 FOR INTERVIEWS:
- **Start with brute force** to show understanding
- **Optimize to KMP** to show advanced knowledge
- **Explain clearly**: "We find longest palindrome prefix, then add missing part"

💡 KEY INSIGHT:
The problem is really asking: "What's the longest palindrome that starts at index 0?"
Once you find that, just add the remaining characters (reversed) to the front!
*/