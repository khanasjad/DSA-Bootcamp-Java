package LeetCode3.BitManipulation;



public class PowerOfTwo {

    public boolean isPowerOfTwo(int n) {
        if (n <= 0) {
            return false; // Negative numbers and zero are NOT powers of two
        }

        // Main check using bit manipulation
        return (n & (n - 1)) == 0;
    }

    public static void main(String[] args) {
        PowerOfTwo solver = new PowerOfTwo();

        System.out.println(solver.isPowerOfTwo(1));   // true
        System.out.println(solver.isPowerOfTwo(16));  // true
        System.out.println(solver.isPowerOfTwo(18));  // false
    }
}
