# [287. Find the Duplicate Number](https://leetcode.com/problems/find-the-duplicate-number/)

---
tags:
  - FloydsAlgorithm
  - linkedlist
  - cycleDetection
  - O_n
  - O_1space
  - Medium
---
## Problem Statement
Given an array of integers `nums` containing `n + 1` integers where each integer is in the range `[1, n]` inclusive.

There is only **one repeated number** in `nums`, return _this repeated number_.

You must solve the problem **without** modifying the array `nums` and uses only constant extra space.
### Example

Assuming an example:

**Input**: `nums = [3,1,3,4,2]`  
**Output**: `3`  
**Explanation**: Number 3 appears twice in the array.

### Analysis

- **Inputs**: An array of integers `nums` of size n+1, where each integer is between 1 and n inclusive.
- **Outputs**: An integer that represents the duplicate number in the array.
- **Constraints**: Only one number is repeated in the array.

## Logic & Key Explanation

The solution uses **Floyd's Tortoise and Hare (Cycle Detection Algorithm)**. This algorithm is used to detect loops in linked lists. The idea is to treat the array as a linked list where the value at each index is the next "node" in the list. Since there's a duplicate, there must be a cycle in this "list".

1. **Phase 1 - Detecting the Cycle**: Two pointers, a tortoise and a hare, move through the array. The tortoise moves one step at a time, and the hare moves two steps. Due to the cyclic nature of the sequence, they will eventually meet inside the cycle.
2. **Phase 2 - Finding the Start of the Cycle (Duplicate Number)**: After the first phase, we reinitialize one of the pointers to the start and then move both pointers one step at a time. The point where they meet again is the start of the cycle, which is our duplicate number. 
		Extra: There is a mathematical proof that can be done to show that the distance from the point the tortoise and the hare meet to the start of the cycle and the distance from the head of the graph to the start of the cycle is always guaranteed to be equal in all cases 

## Pseudo Code

1. Initialize `tortoise` and `hare` pointers. Move `tortoise` one step and `hare` two steps until they meet.
2. Reset `hare` pointer to the start.
3. Move `tortoise` and `hare` one step at a time until they meet again.
4. The position where they meet is the duplicate number.

### Time Complexity O(n) - 
Even though there are two while loops, each element is visited at most twice, making the overall time complexity linear.

### Space Complexity - 
We're using a constant amount of space as we're only utilizing two pointers and no other additional data structures.

## Java Code: 

```java
class Solution {
    public int findDuplicate(int[] nums) {
        int tortoise = nums[0];
        int hare = nums[nums[0]];
        while (tortoise != hare) {
            tortoise = nums[tortoise];
            hare = nums[nums[hare]];
        }
        hare = 0;
        while (tortoise != hare) {
            tortoise = nums[tortoise];
            hare = nums[hare];
        }
        return tortoise;
    }
}

```