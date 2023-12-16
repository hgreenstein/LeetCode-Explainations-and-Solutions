# [3. Longest Substring Without Repeating Characters](https://leetcode.com/problems/longest-substring-without-repeating-characters/)

---
tags:
  - SlidingWindow
  - HashSet
  - O_n
  - O_nspace
  - Medium
---
## Problem Statement: 

Given a string s, find the length of the longest 
substring without repeating characters. 

Example 1:

Input: s = "abcabcbb"
Output: 3
Explanation: The answer is "abc", with the length of 3.
Example 2:

Input: s = "bbbbb"
Output: 1
Explanation: The answer is "b", with the length of 1.
Example 3:

Input: s = "pwwkew"
Output: 3
Explanation: The answer is "wke", with the length of 3.
Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.

## Logic & Algorithms:

The sliding window uses two pointers to find the longest consecutive substring. We use a set to figure out when the right index would add a letter already in the substring and increment the left pointer until until we no longer have that repeating character. We keep track of the longest length window we have see so far as we parse the string and return it at the end.

## Visualization:

![[sliding_window_adjusted_v2 (1).gif]]

## Pseudo Code:

1. Initialize a HashSet to contain the chars of the window
2. Initialize left and right pointers and a greatest so far result variable
3. While the right variable is less than the length of the string
	1. Try to add the char at the right pointer
	2. If the window doesn't contain that char we can add it to the window
		1. Add it the the HashSet 
		2. Update the greatest length window so far
		3. Move the right pointer to the next character
	3. Else the window already contains the new char at `right`
		1. Remove the char at the left pointer from the HashSet and move the left pointer in one
4. Then try the while loop will run again and try to see if we can add the right character again now that we've removed the left character
## Time Complexity O(n) - Going through every char in the string at most twice  
## Space Complexity O(n) - Storing n characters in the HashSet 
## Java Code:

```java
class Solution {
    public int lengthOfLongestSubstring(String s) {
        HashSet<Character> chars = new HashSet<>();
        int greatest = 0;
        int left = 0;
        int right = 0;
        while(right < s.length()){
            char c = s.charAt(right);
            if(!chars.contains(c)){
                chars.add(c);
                greatest = Math.max(greatest, (right - left + 1));
                right++;
            }
            else{
                char leftChar = s.charAt(left);
                chars.remove(leftChar);
                left++; 
            }
        }
        return greatest; 
    }
}
```



