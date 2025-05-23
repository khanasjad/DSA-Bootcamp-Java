package LeetCode1.BinarySearch;

public class SearchRotatedSortedArray {

    public static void main(String[] args) {
        int[] nums = {4, 5, 6, 7, 0, 1, 2};
        int target = 0;
        System.out.println("Target found at index: " + searchDigits(nums, target));
    }

    private static int searchDigits(int[] nums, int target) {

        int left = 0;
        int right = nums.length -1;

        while (left<=right){
         int mid = left +(right-left)/2;
           if(target == nums[mid]){
               return mid;
           }
         if(nums[left]<=nums[mid]) {
             if (nums[left] <= target && target < nums[mid]) {
                 right = mid - 1;
             } else {
                 left = mid + 1;
             }
         }else{
             if (nums[mid]<target && target <=nums[right]){
                 left =mid+1;
             }else{
                 right = mid-1;
             }
         }

        }
       return -1;
    }
}
