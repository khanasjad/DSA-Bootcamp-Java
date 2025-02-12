package LeetCode5.GameTheory;

public class NimGame {
        public static boolean canWinNim(int n) {
            return n % 4 != 0;
        }

        public static void main(String[] args) {
            System.out.println(canWinNim(4)); // Output: false
            System.out.println(canWinNim(5)); // Output: true
            System.out.println(canWinNim(8)); // Output: false
            System.out.println(canWinNim(9)); // Output: true
        }

}
