package LeetCode5.MathematicalProblems;

import java.util.ArrayList;
import java.util.List;

public class CombinationSum {

    public static void main(String[] args) {
        int[] candidates = {2,3,6,7};
        int target = 7;
        System.out.println(combinationSum(candidates, target));
    }

    private static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        backtracking(candidates,target,0,new ArrayList<Integer>(),result);
       return result;
    }

    private static void backtracking(int[] candidates, int target, int index, ArrayList<Integer> currentList, List<List<Integer>> result) {

        if(target == 0){
            result.add(new ArrayList<>(currentList));
            return;
        }

        for(int i = index;i<candidates.length; i++){

            if(target< candidates[i]) continue;

            currentList.add(candidates[i]);
            backtracking(candidates,target -candidates[i],i,currentList,result);
            currentList.remove(currentList.size()-1);
        }
    }

}
