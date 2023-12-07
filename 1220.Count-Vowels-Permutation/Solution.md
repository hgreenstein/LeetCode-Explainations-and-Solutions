# [1220. Count Vowel Permuatations](https://leetcode.com/problems/count-vowels-permutation/description/)
---
tags:
  - 1D_DP
  - 2D_DP
  - O_n
  - O_1space
  - Hard
---
## Problem Statement: 

Given an integer `n`, your task is to count how many strings of length `n` can be formed under the following rules:

- Each character is a lower case vowel (`'a'`, `'e'`, `'i'`, `'o'`, `'u'`)
- Each vowel `'a'` may only be followed by an `'e'`.
- Each vowel `'e'` may only be followed by an `'a'` or an `'i'`.
- Each vowel `'i'` **may not** be followed by another `'i'`.
- Each vowel `'o'` may only be followed by an `'i'` or a `'u'`.
- Each vowel `'u'` may only be followed by an `'a'.`

Since the answer may be too large, return it modulo `10^9 + 7.`

**Example 1:**

**Input:** n = 1
**Output:** 5
**Explanation:** All possible strings are: "a", "e", "i" , "o" and "u".

**Example 2:**

**Input:** n = 2
**Output:** 10
**Explanation:** All possible strings are: =="ae", "ea", "ei", "ia", "ie", "io", "iu", "oi", "ou" and "ua"==.

**Example 3:** 

**Input:** n = 5
**Output:** 68

## Logic & Algorithms:
### DP Approach

We solved this using a 1D DP approach that originates from a 2D DP approach. Originally dp[i][j] = number of strings of length i that ends with the j-th vowel. We then make an optimization to only store the previous length's number of strings because we don't need to look any further back than 1 character based on the rules.

#### Base Case:

The first thing here to realize when trying to solve this problem is what is the base case? How would I solve this problem for just length 1? Well every vowel can be proceeded by nothing, therefore for every string of length 1 ending at any vowel, there is 1 combination. Thus, we will initialize the first row of our DP to [1, 1, 1, 1, 1] and if we added them all up to get all the strings that have length 1, we would get 5 as expected.

### Iterative Case:

For each additional vowel, the tricky part comes from figuring out the prior relationships from the rules given. The rules tell you what can come after each vowel, but we need to determine given the current vowel is `x`, what could the previous vowel have been?

The easiest way to figure this out is from example 2 where all pairs of 2 vowels are listed, when we look at it we get a relationship like this:

- If the current vowel is 'a', the previous vowel could've been: ['e', 'i', 'u']
- If the current vowel is 'e', the previous vowel could've been: ['a', 'i']
- If the current vowel is 'i', the previous vowel could've been: ['e', 'o']
- If the current vowel is 'o', the previous vowel could've been: ['i']
- If the current vowel is 'u', the previous vowel could've been: ['i', 'o']

Now all we need to do is translate this into code such that we go through all 5 possible vowels and sum all the previous length of the vowels that could've preceeded it as the current length.
## Pseudo Code:

1. Initialize the previous row with all 5 1s
2. For every length from 1 to n - 1
	1. Initialize a temp next array
	2. If the current vowel is 'a', add the previous permutations of: ['e', 'i', 'u']
	3. If the current vowel is 'e', add the previous permutations of: ['a', 'i']
	4. If the current vowel is 'i', add the previous permutations of: ['e', 'o']
	5. If the current vowel is 'o', add the previous permutations of:['i']
	6. If the current vowel is 'u', add the previous permutations of: ['i', 'o']
3. Sum the final length array for every element and return the result
## Time Complexity O($n$) - where we do 5 constant time operations at every possible length i from 2 to n
## Space Complexity O(1) - 

Originally, stored all previous lengths making this a O(n) space solution (See 2nd code below) but we can optimize to store only the previous length's row so this reduces to constant space, storing only two arrays of length 5 at a time.
## Java Code:

## 1D O(1) space optimized solution

```java
class Solution {
    public int countVowelPermutation(int n) {
        int MOD = (int)Math.pow(10, 9) + 7;
        // for each length
        int[] prevLength = new int[5];
        Arrays.fill(prevLength, 1); //initially every vowel has one combination, itself
        
        for(int i = 1; i < n; i++) { 
            //This loop is the current length - 1 where when i = 1 we are dealing with strings of length 2
            //Deal with the possibility of each vowel where a = 0, e = 1, i = 2, o = 3, u = 4
            int[] nextLength = new int[5];  // temporary array to hold the new counts
            
            //a case: could've had e, i, or u previously, note we have to nested mod here as the numbers get very large 
            nextLength[0] += ((prevLength[1] + prevLength[2]) % MOD + prevLength[4]) % MOD; 
            //e case: could've had a or i previously
            nextLength[1] += (prevLength[0] + prevLength[2]) % MOD; 
            //i case: could've had e or o previously            
            nextLength[2] += (prevLength[1] + prevLength[3]) % MOD; 
            //o case: could've had i previously
            nextLength[3] += (prevLength[2]) % MOD; 
            //u case: could've had i or o previously
            nextLength[4] += (prevLength[2] + prevLength[3]) % MOD;
            
            // assign the nextLength to the prevLength
            prevLength = nextLength;
        }
        
        int result = 0;
        //We now loop through all the combinations of length n ending at any vowel and add them
        for (int finalLength : prevLength) {
            result = (result + finalLength) % MOD;
        }
        return result;
    }
}
```

## 2D O(5n) = O(n) space optimized solution

```java
class Solution {
    public int countVowelPermutation(int n) {
        int MOD = (int)Math.pow(10, 9) + 7;
        // for each length
        int[][] dp = new int[n][5];
        Arrays.fill(dp[0], 1); //initially every vowel has one combination, itself
        for(int i = 1; i < n; i++) { 
            //This loop is the current length - 1 where when i = 1 we are dealing with strings of length 2
            //Deal with the possibility of each vowel where a = 0, e = 1, i = 2, o = 3, u = 4
            dp[i][0] += ((dp[i - 1][1] + dp[i - 1][2]) % MOD + dp[i - 1][4]) % MOD; //a case: could've had e, i, or u previously
            dp[i][1] += (dp[i - 1][0] + dp[i - 1][2]) % MOD; //e case: could've had a or i previously
            dp[i][2] += (dp[i - 1][1] + dp[i - 1][3]) % MOD; //i case: could've had i or o previously
            dp[i][3] += (dp[i - 1][2]) % MOD; //o case: could've had i previously
            dp[i][4] += (dp[i - 1][2] + dp[i - 1][3]) % MOD; //u case: could've had i or o previously
        }
        int result = 0;
        //We now loop through all the combinations of length n ending at any vowel and add them
        for (int finalLength : dp[n - 1]) {
            result = (result + finalLength) % MOD;
        }
        return result;
    }
}
```
