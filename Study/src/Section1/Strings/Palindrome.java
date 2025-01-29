package Section1.Strings;

public class Palindrome {
    public static void main(String[] args) {
        String palindromeString = "madam";

        if (isPalindrome(palindromeString)) {
            System.out.println(palindromeString + " is a palindrome.");
        } else {
            System.out.println(palindromeString + " is not a palindrome.");
        }
    }

    private static boolean isPalindrome(String str) {
        // Remove non-alphanumeric characters and convert to lowercase for case-insensitive comparison
        String cleanStr = str.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();

        int left = 0;
        int right = cleanStr.length() - 1;

        while (left < right) {
            if (cleanStr.charAt(left) != cleanStr.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }

        return true;
    }

}
