# [90. Subsets II](https://leetcode.com/problems/subsets-ii/)

---
tags:
  - Backtracking
  - Recursive
  - Sort
  - O_2pown
  - O_2pownspace
  - Medium
---
## Problem Statement: 

Given an integer array `nums` that may contain duplicates, return _all possible_ 

_subsets_

 _(the power set)_.

The solution set **must not** contain duplicate subsets. Return the solution in **any order**.

**Example 1:**

**Input:** nums = [1,2,2]
**Output:** \[[],[1],[1,2],[1,2,2],[2],[2,2]]

## Logic & Algorithms:

This problem is almost identical to [[78. Subsets]] [(GitHub Link)](78.Subsets/Solution.md)with one minor difference. That is that we have to deal with duplicates. It is not hard to do so. 

We simply have to remember that we are making two choices, all the combinations including the element and all the combinations not including the element. When we do the latter, we want to skip over **ALL** the occurrences of that number, which is done with a simple while loop. 

Since we are not guaranteed the array will be in sorted order, we first sort it to ensure duplicates are side by side.

Once we consider all possibilities that **include** the current element, we move our pointer to the next unique number to then consider all possibilities without the any instance of the current element. If there is not another unique number in the `nums` array, then we have finished. 

## Pseudo Code:

1. Sort the array of numbers so duplicates are next to each other
2. Call the recursive function on initial positon 0, the nums array, and a new ArrayList
3. **Base Case:** If we have considered adding and removing `nums.length` elements combined, we have a valid combination, add it to the result
4. **Recursive Case:** 
	1. First add the number at `i` and consider all combinations including that number by making another recursive call and incrementing `i`
	2. Then remove the element at `i` to consider all combinations without it
	3. Since we want combinations without the element *valued* at i, we want to skip over all indexes that contain that value
	4. While we have a next number to check and it is the same as the number at `i`
		1. Increment i by i
	5. Call the recursive function on `i + 1` 
## Time Complexity O($n * 2^n$) - $2^n$ combinations of an array of length n each up to length n 
## Space Complexity O($n * 2^n$) - The `solution` list: In the worst case, it will have $2^n$ subsets, and each subset can have up to n elements
## Java Code:
```java
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
```

