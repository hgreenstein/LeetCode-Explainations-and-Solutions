# [1143. Longest Common Subsequence](https://leetcode.com/problems/longest-common-subsequence/)

---
tags:
  - 2D_DP
  - Subsequence
  - O_mxn
  - O_mxnspace
  - Medium
---
## Problem Statement: 

Given two strings `text1` and `text2`, return _the length of their longest **common subsequence**._ If there is no **common subsequence**, return `0`.

A **subsequence** of a string is a new string generated from the original string with some characters (can be none) deleted without changing the relative order of the remaining characters.

- For example, `"ace"` is a subsequence of `"abcde"`.

A **common subsequence** of two strings is a subsequence that is common to both strings.

**Example 1:**

**Input:** text1 = "abcde", text2 = "ace" 
**Output:** 3  
**Explanation:** The longest common subsequence is "ace" and its length is 3.

**Example 2:**

**Input:** text1 = "abc", text2 = "abc"
**Output:** 3
**Explanation:** The longest common subsequence is "abc" and its length is 3.

**Example 3:**

**Input:** text1 = "abc", text2 = "def"
**Output:** 0
**Explanation:** There is no such common subsequence, so the result is 0.
## Logic & Algorithms:

At every pair of characters in the two input strings `text1` and `text2`, we have one of two decision paths based on the condition of those characters

### Condition 1: Characters are equal:

If the characters at `text1(i) == ` then we can increase the current subsequence by 1. The previous subsequence, if it exists, is at the dp array of `(i - 1, j - 1)`. Add one to the length of the length of the subsequence of all the elements to the left of both characters.

### Condition 2: Characters are not equal

If the characters are not equal we then are split between two decisions. We can either exclude the character at `text1(i)` or the element at `text2(j)`. We want to take the maximum of these two options. Therefore we logically take the maximum of `dp[i - 1][j], dp[i][j-1]` and put it in the current position.

In order to deal with the first characters and ensure that the array will not be out of bounds, we make the dp size 1 larger than necessary and start at 1, 1 therefore the elements at `(0,0), (1, 0), and (0, 1)` are all initialized to 0 correctly. 
## Pseudo Code:

1. Initialize the `dp` array the size of `text1 + 1` x `text2 + 1`
2. For every character in both strings
	1. If the characters are equal:
		1. `dp[i + 1][j + 1] = dp[i][j] + 1`
	2. Else if the characters are not equal
		1. `dp[i + 1][j + 1] = Math.max(dp[i][j + 1], dp[i + 1][j])`
## Time Complexity O($m*n$) - Comparing every character in string length m to every character in string length n 
## Space Complexity O($m*n$)/O(n) - If we store every element of the dp array it will be size $m * n$ but we really only need to store the previous row (See below) 
## Java Code:

### Basic DP Approach with O($m*n$) memory:

```java
class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        int[][] dp = new int[text1.length() + 1][text2.length() + 1];
        for(int i = 0; i < text1.length(); i++){
            for(int j = 0; j < text2.length(); j++){
                if(text1.charAt(i) == text2.charAt(j)){
                    dp[i + 1][j + 1] = dp[i][j] + 1;
                }
                else{
                    dp[i + 1][j + 1] = Math.max(dp[i][j + 1], dp[i + 1][j]);
                }
            }
        }
        return dp[text1.length()][text2.length()]; 
    }
}
```
### Memory Optimized Approach Space: O(n)
```java
class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        int[] previousDp = new int[text2.length() + 1];
        int[] dp = new int[text2.length() + 1]; 
        for(int i = 0; i < text1.length(); i++){
            dp = new int[text2.length() + 1]; 
            for(int j = 0; j < text2.length(); j++){
                if(text1.charAt(i) == text2.charAt(j)){
                    dp[j + 1] = previousDp[j] + 1;
                }
                else{
                    dp[j + 1] = Math.max(previousDp[j + 1], dp[j]);
                }
            }
            previousDp = dp; 
        }
        return previousDp[text2.length()]; 
    }
}
```