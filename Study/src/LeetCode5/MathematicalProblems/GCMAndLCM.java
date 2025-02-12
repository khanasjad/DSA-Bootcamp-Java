package LeetCode5.MathematicalProblems;

public class GCMAndLCM {

        public static int gcd(int a, int b) {
            return (b == 0) ? a : gcd(b, a % b);
        }

        public static int lcm(int a, int b) {
            return (a * b) / gcd(a, b);
        }

        public static void main(String[] args) {
            System.out.println(gcd(12, 18));
            System.out.println(lcm(12, 18)); // Output: 36
        }

}
