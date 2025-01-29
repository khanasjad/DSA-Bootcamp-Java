package Section1.Strings;

public class NaivepatternSearch {
    public static void main(String[] args) {
        String text = "ABABDABACDABABCABAB";
        String pattern = "ABABCABAB";

        searchPattern(text, pattern);
    }

    private static void searchPattern(String text, String pattern) {
        int textLength = text.length();
        int patternLength = pattern.length();

        for (int i = 0; i <= textLength - patternLength; i++) {
            int j;

            // Check for pattern match at current position
            for (j = 0; j < patternLength; j++) {
                if (text.charAt(i + j) != pattern.charAt(j)) {
                    break;
                }
            }

            // If the inner loop reached the end, a pattern match is found
            if (j == patternLength) {
                System.out.println("Pattern found at index " + i);
            }
        }
    }
}
