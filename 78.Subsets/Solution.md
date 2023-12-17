# [78. Subsets](https://leetcode.com/problems/subsets/)

---
tags:
  - Backtracking
  - Recursive
  - O_2pown
  - O_2pownspace
  - Medium
---
## Problem Statement: 

Given an integer array `nums` of **unique** elements, return _all possible_ 

The solution set **must not** contain duplicate subsets. Return the solution in **any order**.

**Example 1:**

**Input:** nums = [1,2,3]
**Output:** \[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]

**Example 2:**

**Input:** nums = [0]
**Output:** \[[],[0]]

## Logic & Algorithms:

![Subset Decision Tree Drawing](https://i.imgur.com/d9DeVaY.png)

This is the most simple backtracking problem. Essentially you have to brute force building the $2^n$ different subsets of any given set.  

This involves a simple decisions at every step to either include or not include the current element you are considering, for every element in the array. This gives 2 possible states of every number, that multiplies together as the array expands, thus $2^n$

However, it is important to keep track of the index i which is the index you are deciding to remove or add and increment it at each recursive call. This ensures that there are no duplicates because once you are deciding to include 2 or not, 1 will never be considered again. Hence, 1 will only appear first and we won't deal with repeats like [1, 2] and [2, 1].
## Pseudo Code:

1. Call the recursive helper function on the nums array, initial position of 0 to consider the first element, and a new arraylist to hold the subset being created
2. If we have included or not included all n elements in the array aka `i == nums.length` then we are at the final branch of the tree and the current subset is part of the solution 
3. Else
	1. We call the recursive function on the next element, this considers NOT including the current element at index i
	2. We then add the element at index i to the subset
	3. We call the recursive function on next element passing the subset containing this element, this is the scenario where we do include the current element at index i
	4. We then have to remove the last element (which is the element at index i) from the subset. This allows for backtracking, which undoes the last decision (of including the current element in the subset) so that the algorithm can explore other possible combinations.
## Time Complexity O($2^n$) - There are 2^n unique subsets for any array of length n 
## Space Complexity O($n * 2^n$) - We store 2^n subsets in the solution each of size up to n 
## Java Code:

```java
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
```
