package Section1.Strings;

public class Substring {
    public static void main(String[] args) {
        String mainString = "Hello\", \"Hello\", World!";
        String abc = mainString.replace("\"","");
        String substringToFind = "Hello";
        System.out.println("abc" + abc);
        // Find and count occurrences of the substring
        int count = countSubstringOccurrences(mainString, substringToFind);

        // Print the result
        System.out.println("Occurrences of '" + substringToFind + "': " + count);
    }

    public static int countSubstringOccurrences(String mainString, String substringToFind) {
        int count = 0;
        int index = mainString.indexOf(substringToFind);

        while (index != -1) {
            // Increment count and update the starting index for the next search
            count++;
            index = mainString.indexOf(substringToFind, index + substringToFind.length());
            mainString.indexOf(substringToFind,5);
        }

        return count;
    }
}
