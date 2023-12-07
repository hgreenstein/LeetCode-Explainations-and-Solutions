# [1095. Find in Mountain Array](https://leetcode.com/problems/find-in-mountain-array/description/)
---
tags:
  - BinarySearch
  - sorted
  - O_logn
  - O_1space
  - Hard
  - CustomInterface
---
## Problem Statement: 

You may recall that an array `arr` is a **mountain array** if and only if:

- `arr.length >= 3`
- There exists some `i` with `0 < i < arr.length - 1` such that:
    - `arr[0] < arr[1] < ... < arr[i - 1] < arr[i]`
    - `arr[i] > arr[i + 1] > ... > arr[arr.length - 1]`

Given a mountain array `mountainArr`, return the **minimum** `index` such that `mountainArr.get(index) == target`. If such an `index` does not exist, return `-1`.

**You cannot access the mountain array directly.** You may only access the array using a `MountainArray` interface:

- `MountainArray.get(k)` returns the element of the array at index `k` (0-indexed).
- `MountainArray.length()` returns the length of the array.

Submissions making more than `100` calls to `MountainArray.get` will be judged _Wrong Answer_. Also, any solutions that attempt to circumvent the judge will result in disqualification.

**Example 1:**

**Input:** array = [1,2,3,4,5,3,1], target = 3
**Output:** 2
**Explanation:** 3 exists in the array, at index=2 and index=5. Return the minimum index, which is 2.

## Logic & Algorithms:

A linear scan here would work quite well but since it is sorted and of size up to 1000 and we can only make 100 calls, we can do better. The one algorithm we know that works faster for sorted arrays is [[704. Binary Search]]. If we knew the peak, we could easily run binary search on the left and right sorted portions of the array, but we don't know the peak. So that is what we will find first.

We will run three different binary searches on the array. The first will compare the middle element to the left and right elements to see what direction to go in to get towards the peak. Once we find the peak, we will first search for our element in the left sorted portion. The left sorted portion will contain the lower indexes and we want the first index if the element appears twice, since it is strictly increasing we don't need to worry about duplicates. If we don't find it in the left sorted portion, we search the right sorted portion for the element. If we find it we return the index else we return -1 as required. 

## Pseudo Code:

1. Initialize right and left pointers to the 2nd and 2nd from end indexes (peak won't be on the end)
2. While `left <= right`
	1. Calculate the middle element
	2. Get the value of the middle element and its left and right neighbors
	3. If they are strictly increase left to right, we are in the left sorted portion
		1. Set `left = middle + 1` to search towards the right
	4. If they are strictly decreasing left to right, we are in the right sorted portion
		1. Set `right = middle - 1` to search towards the left
	5. Else we have found the peak, break
3. Run a binary search on the left sorted portion for target indexes: [0, peak]
	1. If the element is found return the index
4. If we didn't find it in the left sorted portion, search the right sorted portion for target indexes: [peak, length - 1]
	1. If the element is found return the index
5. We didn't find the target element, return -1
## Time Complexity O($log(n)$) - Running 3 binary searches on the mountain array where each takes $O(log(n))$ time 
## Space Complexity O(1) - 3 pointers and some variables, all constant space 
## Java Code:

```java
/**
 * // This is MountainArray's API interface.
 * // You should not implement it, or speculate about its implementation
 * interface MountainArray {
 *     public int get(int index) {}
 *     public int length() {}
 * }
 */
 
class Solution {
    public int findInMountainArray(int target, MountainArray mountainArr) {
        int length = mountainArr.length(); 
        int left = 1;
        int right = length - 2;
        int middle = 0; 
        while(left <= right){
            middle = left + (right - left)/2;
            int leftElement = mountainArr.get(middle - 1);
            int middleElement = mountainArr.get(middle);
            int rightElement = mountainArr.get(middle + 1);
            if(leftElement < middleElement && middleElement < rightElement){
                left = middle + 1; 
            }
            else if(leftElement > middleElement && middleElement > rightElement){
                right = middle - 1;
            }
            else{
                break;
            }
        }
        int peak = middle;
        left = 0;
        right = peak;
        int middleElement = Integer.MAX_VALUE;
        while(left <= right){
            middle = left + (right - left)/2;
            middleElement = mountainArr.get(middle);
            if(target < middleElement){
                right = middle - 1;
            }
            else if(target > middleElement){
                left = middle + 1;
            }
            else{
                break;
            }
        }
        if(middleElement == target){
            return middle;
        }
        left = peak;
        right = length - 1;
        while(left <= right){
            middle = left + (right - left)/2;
            middleElement = mountainArr.get(middle);
            if(target > middleElement){
                right = middle - 1;
            }
            else if(target < middleElement){
                left = middle + 1;
            }
            else{
                break;
            }
        }
        if(middleElement == target){
            return middle;
        }
        return -1; 
    }
}
```
