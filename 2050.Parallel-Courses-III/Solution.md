# [2050. Parallel Courses III](https://leetcode.com/problems/parallel-courses-iii/description/)
---
tags:
  - 1D_DP
  - DFS
  - Graph
  - HashMap
  - O_n2
  - O_n2space
  - Hard
  - Recursive
---
## Problem Statement: 

You are given an integer `n`, which indicates that there are `n` courses labeled from `1` to `n`. You are also given a 2D integer array `relations` where `relations[j] = [prevCoursej, nextCoursej]` denotes that course `prevCoursej` has to be completed **before** course `nextCoursej` (prerequisite relationship). Furthermore, you are given a **0-indexed** integer array `time` where `time[i]` denotes how many **months** it takes to complete the `(i+1)th` course.

You must find the **minimum** number of months needed to complete all the courses following these rules:

- You may start taking a course at **any time** if the prerequisites are met.
- **Any number of courses** can be taken at the **same time**.

Return _the **minimum** number of months needed to complete all the courses_.

**Note:** The test cases are generated such that it is possible to complete every course (i.e., the graph is a directed acyclic graph).


**![](https://assets.leetcode.com/uploads/2021/10/07/ex2.png)**

**Input:** n = 5, relations = \[[1,5],[2,5],[3,5],[3,4],[4,5]], time = [1,2,3,4,5]
**Output:** 12
**Explanation:** The figure above represents the given graph and the time required to complete each course.
You can start courses 1, 2, and 3 at month 0.
You can complete them after 1, 2, and 3 months respectively.
Course 4 can be taken only after course 3 is completed, i.e., after 3 months. It is completed after 3 + 4 = 7 months.
Course 5 can be taken only after courses 1, 2, 3, and 4 have been completed, i.e., after max(1,2,3,7) = 7 months.
Thus, the minimum time needed to complete all the courses is 7 + 5 = 12 months.
## Logic & Algorithms:

This problem essentially reduces to the longest path in the graph where the distance is the time cost of the nodes on both sides of the edge added together. Or, it could be thought of that every leaf node points to a null node with an edge of length time and you don't finish until you reach a null node. We start with a 2D array of edges, which we convert into an adjacency map of `<node, list of neighbor nodes>` which is a very standard for a graph problem like this one. Although this is not officially a topological sort problem, it shares a lot of similarity with those types of problems like [[210. Course Schedule II]] because it involves the need to process nodes (courses) in a specific order based on their dependencies (prerequisites). The topological sort ensures that nodes are processed in a sequence such that no node is processed before its dependencies, which is crucial for determining the start time of each course in this problem. 

## DP/Memoization
Either way, we are guaranteed that there is no cycles but not that all nodes are connected in one graph, therefore we have to run the search starting at every node. However, once we have calculated the longest course path from a given course, we shouldn't have to calculate it again and potentially search a good portion of the graph multiple times. Therefore, we should store that result in a dp table of index course and entry value of the longest required path of courses from that course. 

## Pseudo Code:

1. Create a dp/memoization array that stores the minimum time needed to complete each course while also taking all prerequisites. 
	1. Fill it with all -1 values to symbolized an unexplored course
2. Create an adjacency HashMap of `<edges/courses, list of neighbors/prereq courses>` and add all prereqs
3. Call DFS on every single course from 1 to n as we could have unconnected portions of the graph
	1. **Base Case:** If we've already gotten the result for the current course, return it
			1. We know that every course has an edge in a way since each has it's own time requirement, so we don't have base case for no edges but rather if we've already explored all paths from this node and found the longest time needed, we shouldn't have to do it again, so return the result already computed
	1. **Recursive case:** DFS all neighbors
		1.  Keep track of the max path found from the current course, originally set to 0
		2. For all neighbors of the current course / prereqs
			1. Set the max DFS = to the max of itself and the DFS call on the neighbor
		3. Set the memo array of the current course to the max DFS plus the time to complete the current course itself
		4. Set the global longest path found equal to the max of itself and the current longest path
		5. Return the longest path found from the current course
4. Return the longest path found from the DFS
## Time Complexity O($n^2$) - DFS is O(V + E) where E could be almost $n^2$  in worst case  
## Space Complexity O($n^2$) - Adjacency map, storing up to $n^2$ edges  
## Java Code:

```java
class Solution {
    int longestPath = 0;
    public int minimumTime(int n, int[][] relations, int[] time) {
        int[] minTime = new int[n];
        Arrays.fill(minTime, -1);
        Map<Integer, List<Integer>> adjacencyMap = new HashMap<>();
        for(int[] relation : relations){
            adjacencyMap.computeIfAbsent(relation[0], key -> new ArrayList<>()).add(relation[1]);
        }
        for(int i = 1; i <= n; i++){
            dfs(adjacencyMap, time, minTime, i);
        }
        return longestPath; 
    }
    public int dfs(Map<Integer, List<Integer>> adjacencyMap, int[] time, int[] minTime, int currentCourse){
        if(minTime[currentCourse - 1] != -1){
            return minTime[currentCourse - 1];
        }
        int maxDfs = 0; 
        if(adjacencyMap.containsKey(currentCourse)){
            for(Integer course : adjacencyMap.get(currentCourse)){
                maxDfs = Math.max(maxDfs, dfs(adjacencyMap, time, minTime, course));
            }
        }
        minTime[currentCourse - 1] = maxDfs + time[currentCourse - 1];
        longestPath = Math.max(longestPath, minTime[currentCourse - 1]);
        return minTime[currentCourse - 1];
    }
}
```
