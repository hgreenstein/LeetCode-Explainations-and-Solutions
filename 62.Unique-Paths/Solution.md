# [62. Unique Paths](https://leetcode.com/problems/unique-paths/)

---
tags:
  - 2D_DP
  - 2Dgrid
  - O_mxn
  - O_mxnspace
  - Medium
---
## Problem Statement: 

There is a robot on an `m x n` grid. The robot is initially located at the **top-left corner** (i.e., `grid[0][0]`). The robot tries to move to the **bottom-right corner** (i.e., `grid[m - 1][n - 1]`). The robot can only move either down or right at any point in time.

Given the two integers `m` and `n`, return _the number of possible unique paths that the robot can take to reach the bottom-right corner_.

The test cases are generated so that the answer will be less than or equal to `2 * 10^9`.

**Example 1:**

![](https://assets.leetcode.com/uploads/2018/10/22/robot_maze.png)

**Input:** m = 3, n = 7
**Output:** 28

**Example 2:**

**Input:** m = 3, n = 2
**Output:** 3
**Explanation:** From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
1. Right -> Down -> Down
2. Down -> Down -> Right
3. Down -> Right -> Down

## Logic & Algorithms:

### Dynamic Programming Logic

We know that for the starting position there is one way to get there. Likewise there is only one way to take a single step down or single step right. 

However, the interesting case comes when we get to the cell diagonally right from the start or coordinates (1, 1) assuming starting at (0, 0). 

The number of ways we can get to this position, is the number of ways we get get there where the last decision was making a right turn, stored in (0, 1) or the space to the left of us, plus the number of ways to get there adding the last decision of moving down, stored at (1, 0) or one above us. 

If we sum those two together at every position, we will find the total ways to get to (m, n) in that spot. We iterate top down, row by row left to right so that we guarantee we have computed everything to the left and above the current position we are updating.

### Memory Optimization

If we store the entire grid for dynamic programming, that would take O($m*n$) space. However, we can see that the only important row to look at for each calculation is the current row, and the row above the current row. 
## Pseudo Code:

1. Initialize previous row and current row to empty of length `n`
4. Set the `currentRow[0]` equal to 1, there is 1 way to get to the starting square
5. For every row in the grid `i` from 0 to m
	1. For every column in the grid `j` from 0 to n
		1. Increase the current position by the ways to get to the square above
		2. If we are past square index 0 of the current row
			1. Increase the current position by the ways to get to the square to the left
	2. `previousRow = currentRow`
	3. Set `currentRow` to a new empty array length n
6. Return the ways to get to the target position stored in the `previousRow` at index `n-1`
## Time Complexity O($m*n$) - 
Calculating the ways to get to each cell in the $m*n$ grid once 
## Space Complexity O($n$) - 

We only ever hold the current row of length `n` and previous row of length `n` yielding a space complexity of O($2n$) = O($n$)
## Java Code:

### O(n) space solution, storing only the current and previous rows

```java
class Solution {
    public int uniquePaths(int m, int n) {
        int[] previousRow = new int[n];
        int[] currentRow = new int[n];
        currentRow[0] = 1;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(i > 0){
                    currentRow[j] += previousRow[j];
                }
                if(j > 0){
                    currentRow[j] += currentRow[j - 1];
                }
            }
            previousRow = currentRow;
            currentRow = new int[n]; 
        }
        return previousRow[n - 1];
    }
}
```
### O($m*n$) space solution, storing the entire grid

```java
class Solution {
    public int uniquePaths(int m, int n) {
        int[][] grid = new int[m][n];
        grid[0][0] = 1;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(i > 0){
                    grid[i][j] += grid[i-1][j];
                }
                if(j > 0){
                    grid[i][j] += grid[i][j-1]; 
                }
            }
        }
        return grid[m - 1][n-1];
    }
}
```