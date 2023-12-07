# [210. Course Schedule II](https://leetcode.com/problems/course-schedule-ii/description/)
---
tags:
  - Topological_Sort
  - Graph
  - DFS
  - O_n2
  - O_n2space
  - Medium
  - HashMap
  - HashSet
---
## Problem Statement: 

There are a total of `numCourses` courses you have to take, labeled from `0` to `numCourses - 1`. You are given an array `prerequisites` where `prerequisites[i] = [ai, bi]` indicates that you **must** take course `bi` first if you want to take course `ai`.

- For example, the pair `[0, 1]`, indicates that to take course `0` you have to first take course `1`.

Return _the ordering of courses you should take to finish all courses_. If there are many valid answers, return **any** of them. If it is impossible to finish all courses, return **an empty array**.

**Example 2:**

**Input:** `numCourses` = 4, prerequisites = \[[1,0],[2,0],[3,1],[3,2]]
**Output:** [0,2,1,3]
**Explanation:** There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0.
So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3].

## Logic & Algorithms:

### First step: Adjacency Map

We use the #HashMap 's built in `computeIfAbsent()` function to turn the prerequisites 2D array into a adjacency map where each node/course has a list of all nodes/courses that are prerequisites to take that course. We simply loop through all prereqs making this map. 

### Topological Sort: 

#### Defintion: 
A topological sort is a way of arranging all the nodes in a graph such that for every pair of nodes (a, b) such that b is a prerequisite, b comes before course a in the arrangement. This is only possible in a ==D.A.G.== or directed acyclic graph which contains no cycles. That is we can not have a a prerequisite to b and b as a prerequisite to a or any more complicated variation that creates a loop with no valid starting course. If we detect any cycle, we can short circuit and know there is no valid way to complete all the courses. 

#### Implementation:

In practice this is almost always used in a DFS. Since we are not guaranteed to have a completely contacted graph, that is we could have two or more parts where `A -> B` and `C -> D` but `{A,B}` and `{C,D}` have no edges between them. Therefore we have to call this DFS on every unique course as we don't know which courses to start at. 

This DFS will return a boolean result, either true that there is a way to complete this course and all its prerequisites or false, there is a cycle and thus no valid way to complete this course and therefore all courses. We have two sets, a cycle set and a result set. 

This creates three states for any given node. 

1. The first is `unvisited` or a course we don't know if we can complete yet, which is the starting state of all courses. 

2. The second is `visiting` this means we are currently checking the prerequisites for that course. In our implementation, we keep a Cycle HashSet that holds all visiting nodes so we can check if a node is in that set in constant time. If we ever visit a node while already `visiting`,  we have detected a cycle and have no valid course order, return false. 

3. The third state is `visited` meaning we can complete that course and all of its prerequisite courses. This is our result HashSet, because once we know a course is completed, we add it to the result as the next course in the order knowing that all prerequisites are already in the result HashSet because all their DFS results were `true`.  

The process at every node goes something like this. First check if we are currently already visiting that course/have a cycle. If we do, stop and return false. Otherwise check if we already completed this course in our results list, then no further work required return true. Otherwise we have never visited this course and have to check all it's requirements.
1. Add the current node to the cycle set
2. For every prerequisite of the current course
	1. If we can't complete just one of the prerequisites, return false for the whole course path
3. If all returned true, we have taken all prereqs and can now take the current course
	1. Remove the current course from the cycle set
	2. Add the current course to the result set (take it as next course)

## Pseudo Code:

1.  Initialize an `adjacencyMap` and two HashSets where the result like is a `LinkedHashSet` that maintains added order
2. Convert the prerequisite array to an `adjacencyMap` where every course has a list of its prerequisite courses in the map
3. For every course 
	1. Call DFS on the course, if any return false, return an empty list, no valid course order
	2. In the DFS:
		1. First check if we are currently already visiting that course/have a cycle. If we do, stop and return false. 
		2. Otherwise check if we already completed this course in our results list, then no further work required return true. 
		3. Otherwise we have never visited this course and have to check all it's requirements.
			1. Add the current node to the cycle set
			2. For every prerequisite of the current course
				1. If we can't complete just one of the prerequisites, return false for the whole course path
			3. If all returned true, we have taken all prerequisites and can now take the current course
				1. Remove the current course from the cycle set (done `visiting`)
				2. Add the current course to the result set (take it as next course) mark `visited`
4. Add all elements of the LinkedHashSet to a result array and return it
## Time Complexity O($n^2$) - DFS O(V + E) where E can be up to $n^2$
## Space Complexity O( $n^2$) - Adjacency Map has n entries up to length `n-1` for valid or `n` for cycle
## Java Code:

```java
class Solution {
    Map<Integer, List<Integer>> adjacencyList = new HashMap<>(); 
    Set<Integer> result = new LinkedHashSet<>();
    Set<Integer> cycle = new HashSet<>();
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        for(int[] prereq: prerequisites){
            adjacencyList.computeIfAbsent(prereq[0], key -> new ArrayList<>()).add(prereq[1]); 
        }
        for(int i = 0; i < numCourses; i++){
            if(!dfs(i)){
                return new int[0];
            }
        }
        int[] toReturn = new int[result.size()];
        int i = 0;
        for(Integer j: result){
            toReturn[i] = j;
            i++; 
        }
        return toReturn; 
    }
    public boolean dfs(int course){
        if(cycle.contains(course)){
            return false;
        }
        if(result.contains(course)){
            return true;
        }
        cycle.add(course);
        if(adjacencyList.containsKey(course)){
            for(Integer i : adjacencyList.get(course)){
                if(!dfs(i)){
                    return false;
                }
            }
        }
        cycle.remove(course);
        result.add(course);
        return true; 
    }
}
```
