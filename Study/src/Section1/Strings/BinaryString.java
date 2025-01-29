package Section1.Strings;

public class BinaryString {
    public static void main(String[] args) {
        String binaryString1 = "1101";
        String binaryString2 = "1010";

        // Perform binary addition
        String sum = binaryAddition(binaryString1, binaryString2);
        System.out.println("Binary Addition: " + sum);

        // Perform binary multiplication
        String product = binaryMultiplication(binaryString1, binaryString2);
        System.out.println("Binary Multiplication: " + product);
    }

    private static String binaryAddition(String binary1, String binary2) {
        int num1 = Integer.parseInt(binary1, 2);
        int num2 = Integer.parseInt(binary2, 2);
        int sum = num1 + num2;
        return Integer.toBinaryString(sum);
    }

    private static String binaryMultiplication(String binary1, String binary2) {
        int num1 = Integer.parseInt(binary1, 2);
        int num2 = Integer.parseInt(binary2, 2);
        int product = num1 * num2;
        return Integer.toBinaryString(product);
    }
}
