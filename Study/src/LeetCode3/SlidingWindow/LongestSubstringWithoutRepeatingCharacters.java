package LeetCode3.SlidingWindow;


import java.util.HashMap;
import java.util.Map;

public class LongestSubstringWithoutRepeatingCharacters {
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int left = 0, maxLen = 0;

        for (int right = 0; right < s.length(); right++) {
            char current = s.charAt(right);

            if (map.containsKey(current)) {
                // Move left pointer past the last occurrence of current char
                left = Math.max(left, map.get(current) + 1);
            }

            map.put(current, right); // Update index of current char
            maxLen = Math.max(maxLen, right - left + 1); // Update result
        }

        return maxLen;
    }

    public static void main(String[] args) {
        LongestSubstringWithoutRepeatingCharacters ls = new LongestSubstringWithoutRepeatingCharacters();
        System.out.println(ls.lengthOfLongestSubstring("abcabcbb")); // Output: 3
        System.out.println(ls.lengthOfLongestSubstring("bbbbb"));    // Output: 1
        System.out.println(ls.lengthOfLongestSubstring("pwwkew"));   // Output: 3
    }
}