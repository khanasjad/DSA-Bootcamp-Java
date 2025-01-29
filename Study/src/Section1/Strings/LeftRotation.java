package Section1.Strings;

public class LeftRotation {
    public static void main(String[] args) {
        String original = "abcdefgh";
        int positions = 3;

        String leftRotated = leftRotateString(original, positions);
        System.out.println("Original String: " + original);
        System.out.println("Left Rotated String: " + leftRotated);
    }

    private static String leftRotateString(String input, int positions) {
        int length = input.length();
        // Adjust the number of positions to prevent unnecessary rotations
        positions = positions % length;
        // Perform the left rotation using substring
        return input.substring(positions) + input.substring(0, positions);
    }
}

//public class RightRotation {
//    public static void main(String[] args) {
//        String original = "abcdefgh";
//        int positions = 3;
//
//        String rightRotated = rightRotateString(original, positions);
//        System.out.println("Original String: " + original);
//        System.out.println("Right Rotated String: " + rightRotated);
//    }
//
//    private static String rightRotateString(String input, int positions) {
//        int length = input.length();
//        // Adjust the number of positions to prevent unnecessary rotations
//        positions = positions % length;
//        // Perform the right rotation using substring
//        return input.substring(length - positions) + input.substring(0, length - positions);
//    }
//}