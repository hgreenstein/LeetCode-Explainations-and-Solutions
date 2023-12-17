class Solution {
    public List<List<Integer>> solution = new ArrayList<>(); 
    public List<List<Integer>> subsets(int[] nums) {
        helperFunction(0, nums, new ArrayList<>());
        return solution; 

    }
    public void helperFunction(int i, int[] nums, List<Integer> subset){
        if(i == nums.length){
            solution.add(new ArrayList<>(subset)); 
        }
        else{
            helperFunction(i + 1, nums, subset);
            subset.add(nums[i]);
            helperFunction(i + 1, nums, subset);
            subset.remove(subset.size() - 1); 
        }
    }
}
