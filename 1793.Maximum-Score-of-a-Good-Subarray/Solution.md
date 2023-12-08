# [1793. Maximum Score of a Good Subarray](https://leetcode.com/problems/maximum-score-of-a-good-subarray/)
---
tags:
  - TwoPointer
  - O_n
  - O_1space
  - Hard
---
## Problem Statement:

You are given an array of integers `nums` **(0-indexed)** and an integer `k`.

The **score** of a subarray `(i, j)` is defined as `min(nums[i], nums[i+1], ..., nums[j]) * (j - i + 1)`. A **good** subarray is a subarray where `i <= k <= j`.

Return _the maximum possible **score** of a **good** subarray._

**Example 1:**

**Input:** nums = [1,4,3,7,4,5], k = 3
**Output:** 15
**Explanation:** The optimal subarray is (1, 5) with a score of min(4,3,7,4,5) * (5-1+1) = 3 * 5 = 15. 

**Example 2:**

**Input:** nums = [5,5,4,5,4,1,1,1], k = 0
**Output:** 20
**Explanation:** The optimal subarray is (0, 4) with a score of min(5,5,4,5,4) * (4-0+1) = 4 * 5 = 20.

## Logic & Algorithms:

The problems statement can be a bit confusing here but essentially we want the largest score where the score is the width of the subarray times the min value of the subarray and the subarray must include the element at index `k`, always guaranteed to be in bounds. We are also guaranteed that all elements are positive, as it prevents negative min values when expanding the subarray.

If we take the problem statement like that, we can see that a logical place to start is at index k and expand outwards from there one element at a time, calculating the score as we go and keeping track of the greatest score seen as the result. We can expand either right or left so we will have two pointers both initialized to k, one moving left and the other right.  

While either the left pointer or right pointer can be moved to expand the window, we get the elements at those positions, 0 for if one of them is out of bounds as every element is 1 or greater. If left is greater than right it hurts our score less to go left, and vice versa. After we choose a direction, we update the local min to the min of the previous local min and the current element we moved to. We then get the current score and update the overall max score if it is larger.

## Pseudo Code:

1. Initialize left and right pointers to index `k`
2. Initialize the global `maxScore` and `localMin` element both to the element at index `k`
3. While either left is greater than 0 or right is less than the length - 1 (We have an element to expand to)
	1. Get the left value as the left pointer - 1 or 0 if not valid
	2. Get the right value as the right pointer + 1 or 0 if not valid
	3. If left value is greater than right, it hurts our score less to expand left
		1. Decrement left by 1
		2. Update the local min to min of itself and left val
	4. If right value is greater than left, it hurts our score less to expand right
		1. Decrement right by 1
		2. Update the local min to min of itself and right val
	5. Calculate the current score as the `localMin * (right - left + 1)` and update max score if larger
4. Return max score

## Time Complexity O(n) - Calculating the score at every element once 
## Space Complexity O(1) - Only pointers and min and max vars needed 
## Java Code:

```java
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
```
