package Section1.Strings;

public class KMPPatternSearch {
    public static void main(String[] args) {
        String text = "ABABDABACDABABCABAB";
        String pattern = "ABABCABAB";

        int index = searchPattern(text, pattern);

        if (index != -1) {
            System.out.println("Pattern found at index " + index);
        } else {
            System.out.println("Pattern not found in the text.");
        }
    }

    private static int searchPattern(String text, String pattern) {
        int[] lps = computeLPSArray(pattern);
        int textLength = text.length();
        int patternLength = pattern.length();

        int i = 0;  // index for text
        int j = 0;  // index for pattern

        while (i < textLength) {
            if (pattern.charAt(j) == text.charAt(i)) {
                i++;
                j++;
            }

            if (j == patternLength) {
                // Pattern found at index i - j
                return i - j;
            } else if (i < textLength && pattern.charAt(j) != text.charAt(i)) {
                if (j != 0) {
                    j = lps[j - 1];
                } else {
                    i++;
                }
            }
        }

        return -1;  // Pattern not found
    }

    private static int[] computeLPSArray(String pattern) {
        int patternLength = pattern.length();
        int[] lps = new int[patternLength];
        int len = 0;
        int i = 1;

        while (i < patternLength) {
            if (pattern.charAt(i) == pattern.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len != 0) {
                    len = lps[len - 1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }

        return lps;
    }
}
