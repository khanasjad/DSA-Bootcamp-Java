package LeetCode5.FibonnaciSequence;

public class ClimbingStairs {
    public static void main(String[] args) {
        System.out.println(climbStairs(4)); // Output: 5
    }

    private static int climbStairs(int i) {

        if (i == 1) return 1;
        if (i ==2) return 2;

        return(climbStairs(i-1)+climbStairs(i-2));
    }
}
