package Section1.Strings;

public class Subsequence {
    public static void main(String[] args) {
        String input = "abc";
        findAllSubsequences(input);
    }

    public static void findAllSubsequences(String input) {
        int n = input.length();

        // Number of subsequences = 2^n
        int totalSubsequences = (int) Math.pow(2, n);

        // Iterate through all possible subsequences
        for (int i = 1; i < totalSubsequences; i++) {
            StringBuilder subsequence = new StringBuilder();

            // Check each bit of the binary representation of i
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) != 0) {
                    // If the jth bit is set, append the character at index j to the subsequence
                    subsequence.append(input.charAt(j));
                }
            }

            // Print the subsequence
            System.out.println(subsequence);
        }
    }
}
