// ### Time optimized O(n) approach

class Solution {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>(); 
        for(int i = 0; i < nums.length; i++){
            int currentNum = nums[i];
            if(map.containsKey(target - currentNum)){
                int [] toReturn = {i, map.get(target - currentNum)};
                return toReturn;
            }
            map.put(currentNum, i); 
        }
        return new int[0];
    }
}

// ### Brute Force O(n^2) approach

class Solution2 {
    public int[] twoSum(int[] nums, int target) {
        for(int i=0; i < nums.length; i++){
            for(int j=0; j < nums.length; j++){
                if(i != j){
                    if(nums[i] + nums[j] == target){
                        int[] answer = {i, j};
                        return answer;
                    }
                }
            }
        }
        return new int[0];
    }
}
