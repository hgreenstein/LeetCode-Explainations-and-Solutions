# LeetCode-Explainations-and-Solutions
This repository contains various Leetcode problems that highlight valuable algorithms and data structures

## Language:

All code is provided in Java and my own personal Pseduo Code allowing for easy translation of these solutions to any target language.

## Tags:

These notes originally come from an [Obsidian](https://obsidian.md/) notebook and may contain internal links and tags such as [[]] or the "#" symbol or the tags at the top of every document. If you add these to your own obsidian notebook, problems will link together in the graph based on similar data structures, algorithms, time complexity, space complexity, difficulty, and more. 

## Summaries:
| Problem | Summary | Link To Full Solution | Tags |
| ------- | ------- | -------------------- | ---- |
| [1095. Find in Mountain Array](https://leetcode.com/problems/find-in-mountain-array/description/) | Run three different Binary Searches. The first one will find the peak of the mountain array, the second will search the left sorted portion for the target, the third the right sorted portion for the target. | [Markdown Solution](/1095.Find-in-Mountain-Array/Solution.md) | BinarySearch, sorted, O_logn, O_1space, Hard, CustomInterface |
| [1220. Count Vowel Permuatations](https://leetcode.com/problems/count-vowels-permutation/description/) | Initialize the base case as 1 way to start which each vowel. For each length greater than 1, apply the rules in reverse saying if the current vowel is `x`, add all the combinations of what the previous vowel could've been. Return all combinations of the final length added together | [Markdown Solution](1220.Count-Vowels-Permutation/Solution.md) | 1D_DP, 2D_DP, O_n, O_1space, Hard |
| [210. Course Schedule II](https://leetcode.com/problems/course-schedule-ii/description/) | Create adjacency map from prerequisite list. Then run Topological Sort, DFS on every course, assuming you get true from all prerequisites and have taken them, take the current course in the result list. Keep cycle set for visiting, any cycle means false no valid path. | [Markdown Solution](210.Course-Schedule-II/Solution.md) | Topological_Sort, Graph, DFS, O_n2, O_n2space, Medium, HashMap, HashSet |
| [2642. Design Graph With Shortest Path Calculator](https://leetcode.com/problems/design-graph-with-shortest-path-calculator/description/) | Store the graph as an adjacency map, when called for the shortest path, run Dijkstra's shortest path algorithm between the two nodes, explained in solution. | [Markdown Solution](2642.Design-Graph-With-Shortest-Path-Calculator/Solution.md) | Dijkstra, Graph, ObjectCreation, customComparator, Heap, HashMap, O_n2, O_n2space, Hard, Greedy |
| [779. K-th Symbol in Grammar](https://leetcode.com/problems/k-th-symbol-in-grammar/description/) | Run a directed DFS the height of the graph making a decision to go right or left at each node, inverting the value when going right. | [Markdown Solution](779.Kth-Symbol-in-Grammar/Solution.md) | DFS, traversal, TwoPointer, O_n, O_1space, Medium, BinarySearch |
| [2050. Parallel Courses III](https://leetcode.com/problems/parallel-courses-iii/description/) | Find the longest continuous path in the graph formed by the prereq edges and adding the time to take each course at every node. DFS at every course and store the result in a DP table of index node. | [Markdown Solution](2050.Parallel-Courses-III/Solution.md) | 1D_DP, DFS, Graph, HashMap, O_n2, O_n2space, Hard, Recursive |
| [1361. Validate Binary Tree Nodes](https://leetcode.com/problems/validate-binary-tree-nodes/) | First find the node that is not the child of any other node, that is the root. Then run BFS/DFS on the tree starting at the root, ensure no cycles or nodes with multiple parents with a visited set. Return that the visited set is size `n` | [Markdown Solution](1361.Validate-Binary-Tree-Nodes/Solution.md) | BinaryTree, BFS, HashSet, Queue, cycleDetection, O_n, O_nspace, Medium |
| [1793. Maximum Score of a Good Subarray](https://leetcode.com/problems/maximum-score-of-a-good-subarray/) | Start left and right pointers at element k, while you can expand to a new element, expand to whichever is greater/will hurt the score least (same condition). Calculate local score and keep track of global max score to return. | [Markdown Solution](1793.Maximum-Score-of-a-Good-Subarray/Solution.md) | TwoPointer, O_n, O_1space, Hard |

