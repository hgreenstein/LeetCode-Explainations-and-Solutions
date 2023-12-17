class Solution {
    public List<List<Integer>> solution = new ArrayList<>();
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        recursiveSubsets(0, nums, new ArrayList<>());
        return new ArrayList<List<Integer>>(solution); 

    }
    public void recursiveSubsets(int iterations, int[] nums, List<Integer> currentSubset){
        if(iterations == nums.length){
            solution.add(new ArrayList<>(currentSubset));
            return;
        }
        currentSubset.add(nums[iterations]);
        recursiveSubsets(iterations + 1, nums, currentSubset);
        currentSubset.remove(currentSubset.size() - 1);
        while(iterations + 1 < nums.length && nums[iterations + 1] == nums[iterations]){
            iterations++;
        }
        recursiveSubsets(iterations + 1, nums, currentSubset); 
    }
}
