# [1. Two Sum](https://leetcode.com/problems/two-sum/)

---
tags:
  - HashMap
  - O_n
  - O_nspace
  - Easy
---

## Problem Statement: 

Given an integer array `nums` and an integer `target`, return indices of the two numbers such that they add up to `target`. You may assume that each input would have **exactly one solution**, and you may not use the same element twice. You can return the answer in any order.  

Input: Array of nums, ex = [2,7,11,15] and target = 9 
Output: Array of indices, ex = [0,1] since nums[0] + nums[1] = 2 + 7 = 9

## Logic & Algorithms:

### Starting with Brute Force

The basic brute force approach seen [here](#2.) and is very straightforward. For every element in the `nums` array, we compare it to every other element in the `nums` array that is different and see if they sum up to the target. This takes $O(n^2)$ space because we have `n` elements to compare to `n-1`. 

### Optimization With Hashing

To do better than the brute force approach, we need to keep track of all the numbers we have seem so far and not have to look back constantly. We can use a HashSet which will tell us in constant time whether or not we have seen a particular number yet. 

The question is, if we still have to check every number in the HashSet then we've barely done better than we started. In fact, we only need to check for the current digit's "complement" to add to the target at every step. 

If the target is 10 and the current number is 4, then we only need to check if we've seen a 6. However, since the problem requires us to to know the indexes of the previous occurrence of 6, we should instead use a HashMap to store two values. The key being the number itself that we have seen, and the value being the index of that number. 

If we can't find the current number's complement in the map, we simply add the current number and the current index to the map and check the next one. By only checking each number once, and given that a lookup in a HashMap is constant time, this brings our time complexity down to $O(n)$ 
## Pseudo Code:

1. Initialize an empty HashMap storing integers
2. For every number in the `nums` array
	1. Store the current number in a variable called `currentNum`
	2. If the HashMap contains the complement of `currentNum` equal to `target - currentNum`
		1. Create a new integer array of the current index and the index of the complement stored as the value in the map
		2. Return the array as the answer
	3. Put the `currentNum, currentIndex`  into the HashMap
3. Return an empty array, no answer found

### Time Complexity O(n) - 

Parsing the entire nums array once of length `n`.

### Space Complexity O(n) - 

Storing up to `n` elements in the HashMap in the worst case.

## Java Code:

### 1. 
### Time optimized  O(n) approach

```java
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
```

### 2.
### Brute Force  O($n^2$) approach

```java
class Solution {
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
```

## Python Code (Optimized):

```python
class Solution:
    def twoSum(self, nums: List[int], target: int) -> List[int]:
        map = {}  

        for i, n in enumerate(nums):
            difference = target - n
            if difference in prevMap:
                return [map[difference], i]
            map[n] = i
```
