# [1582. Special Positions in a Binary Matrix](https://leetcode.com/problems/special-positions-in-a-binary-matrix/)

---
tags:
  - 2Dgrid
  - O_n2
  - O_n2space
  - Easy
---
## Problem Statement: 

Given an `m x n` binary matrix `mat`, return _the number of special positions in_ `mat`_._

A position `(i, j)` is called **special** if `mat[i][j] == 1` and all other elements in row `i` and column `j` are `0` (rows and columns are **0-indexed**).

**Example 1:**

![](https://assets.leetcode.com/uploads/2021/12/23/special1.jpg)

**Input:** mat = [[1,0,0],[0,0,1],[1,0,0]]
**Output:** 1
**Explanation:** (1, 2) is a special position because mat[1][2] == 1 and all other elements in row 1 and column 2 are 0.

**Example 2:**

![](https://assets.leetcode.com/uploads/2021/12/24/special-grid.jpg)

**Input:** mat = [[1,0,0],[0,1,0],[0,0,1]]
**Output:** 3
**Explanation:** (0, 0), (1, 1) and (2, 2) are special positions.

## Logic & Algorithms:

We can easily go through each row and column and sum all the element. In any row or column that satisfies our condition, the sum should be 1 because we are guaranteed to only have elements of 0 and 1.  

We can keep track of a different boolean matrix of values the same size of the original matrix. If any row or column has more than one 1, or only zeros we set the whole row or column to false. 

Thus, any position that is true at the end of checking every row and column, `could` have a special position but we aren't guaranteed the true values are also 1 values.

In the end, all we have to do is traverse the entire graph, and sum the positions that both are equal to 1 and are true in the Boolean matrix. For each position that satisfies these conditions, we can say it is equivalent to that it is a "special position" in this binary matrix.

## Pseudo Code:

1. Store the dimensions of the matrix in two variables `rows` and `cols`
2. Create a Boolean array of size rows * cols
3. For every row in the matrix
	1. If the row sum is not 1
		1. Set all the Boolean values in that same matrix row to false
4. For every column in the matrix
	1. If the col sum is not 1
		1. Set all the Boolean values in that same column to false
5. Initialize a result variable to 0
6. For every row in the matrix
	1. For every column in the matrix
		1. If the current position is equal to 1 and the current Boolean matrix position is true
			1. Add 1 to the result
7. Return the result
## Time Complexity O($m*n$) - 

Checking every square in the matrix 3 times each time O($m*n$) where `m = rows` and `n = columns`
## Space Complexity O($m*n$) - 

Storing a boolean matrix of size m * n 
## Python Code:

```python
class Solution:
    def numSpecial(self, mat: List[List[int]]) -> int:
        rows, cols = (len(mat), len(mat[0]))
        boolmat = [[True for _ in range(cols)] for _ in range(rows)]
             
        # Check rows
        for i in range(rows):
            if mat[i].count(1) != 1 or sum(mat[i]) != 1:
                boolmat[i] = [False] * cols

        # Check columns
        for j in range(cols):
            column = [mat[i][j] for i in range(rows)]
            if column.count(1) != 1 or sum(column) != 1:
                for row in boolmat:
                    row[j] = False

        # Count valid elements
        result = 0
        for i in range(rows):
            for j in range(cols):
                if mat[i][j] == 1 and boolmat[i][j]:
                    result += 1

        return result
```