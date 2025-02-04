package LeetCode1.SlidingWindows;

import java.util.HashSet;

public class LongestSubstringWithoutRepeatingCharacters {

    public static void main(String[] args) {
        System.out.println(checkLength("abcabcbb"));
    }

    public static int checkLength (String s){

        int left = 0;
        int maxLength = 0;
        HashSet<Character> hashSet = new HashSet<>();
         for (int right = 0;right< s.length();right++){

             if(hashSet.contains(s.charAt(right))){
                 hashSet.remove(s.charAt(right));
                 left ++;
             }

             hashSet.add(s.charAt(right));

             maxLength = Math.max(maxLength,right-left+1);

         }

        return maxLength;

    }
}
