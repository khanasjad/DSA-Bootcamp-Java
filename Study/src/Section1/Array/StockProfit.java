package Section1.Array;

import java.util.HashMap;

public class StockProfit {

    public static void main(String[] args){
        int[] nums1 = new int[] {3,2,3};


        majorityElement(nums1);
    }

    public static int majorityElement(int[] nums) {
        HashMap<Integer,Integer> hashMap = new HashMap<>();

        for(int i = 0;i<nums.length ;i++){
            if(hashMap.containsKey(nums[i])){
                hashMap.put(nums[i],hashMap.get(nums[i])+1);
                if(hashMap.get(nums[i]) > nums.length/2){
                    System.out.println(nums[i]);
                    return nums[i];
                }}
                else {
                    hashMap.put(nums[i],1);
                }





        }
        return 0;
    }
    }
