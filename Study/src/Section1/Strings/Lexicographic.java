package Section1.Strings;

public class Lexicographic {
    public static void main(String[] args) {
        String initialString = "abc";
        System.out.println("Lexicographically Ordered Strings:");
        generateLexicographicStrings(initialString);
    }

    private static void generateLexicographicStrings(String str) {
        // Convert the string to a character array for easy manipulation
        char[] chars = str.toCharArray();
        int n = chars.length;

        // Sort the characters to get the lexicographically smallest permutation
        java.util.Arrays.sort(chars);

        while (true) {
            // Print the current permutation
            System.out.println(new String(chars));

            // Find the rightmost character which is smaller than its next character
            int i = n - 2;
            while (i >= 0 && chars[i] >= chars[i + 1]) {
                i--;
            }

            // If there is no such character, break the loop as we have reached the last permutation
            if (i < 0) {
                break;
            }

            // Find the smallest character on the right of chars[i] that is greater than chars[i]
            int j = n - 1;
            while (chars[j] <= chars[i]) {
                j--;
            }

            // Swap chars[i] and chars[j]
            char temp = chars[i];
            chars[i] = chars[j];
            chars[j] = temp;

            // Reverse the substring to the right of chars[i]
            reverse(chars, i + 1, n - 1);
        }
    }

    private static void reverse(char[] chars, int start, int end) {
        while (start < end) {
            char temp = chars[start];
            chars[start] = chars[end];
            chars[end] = temp;
            start++;
            end--;
        }
    }
}
