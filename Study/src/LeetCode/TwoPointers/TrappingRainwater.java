package LeetCode.TwoPointers;

public class TrappingRainwater {
    public static void main(String[] args) {
        int[] height = {0,1,0,2,1,0,1,3,2,1,2,1};
        System.out.println(trap(height)); // Output: 6
    }
    public static int trap(int[] height){

        int left = 0;
        int right = height.length-1;
        int leftMax = 0;
        int rightMax = 0;
        int totalWater= 0;

        while (left<right){
            if (height[left]<height[right]) {
                if (leftMax <= height[left]) {
                    leftMax = height[left];

                } else {
                    totalWater += leftMax - height[left];
                }
                left++;
            }else {

                if (rightMax < height[right]) {
                    rightMax = height[right];

                } else {
                    totalWater += rightMax - height[right];
                }
                right--;
            }


        }

       return totalWater;
    }
}
