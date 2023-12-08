class Solution {
    public int maximumScore(int[] nums, int k) {
        int left = k;
        int right = k;
        int maxScore = nums[k];
        int localMin = nums[k];
        while(left >= 1 || right < nums.length - 1){ //We can expand the array
            int leftVal = left > 0 ? nums[left - 1] : 0;
            int rightVal = right < nums.length - 1 ? nums[right + 1] : 0;
            if(leftVal > rightVal){
                left--;
                localMin = Math.min(leftVal, localMin);
            }
            else{
                right++;
                localMin = Math.min(rightVal, localMin);
            }
            maxScore = Math.max(maxScore, (right - left + 1) * localMin);
        }
        return maxScore; 
    }
}

