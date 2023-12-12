# [1361. Validate Binary Tree Nodes](https://leetcode.com/problems/validate-binary-tree-nodes/)
---
tags:
  - BinaryTree
  - BFS
  - HashSet
  - Queue
  - cycleDetection
  - O_n
  - O_nspace
  - Medium
---
## Problem Statement: 

You have `n` binary tree nodes numbered from `0` to `n - 1` where node `i` has two children `leftChild[i]` and `rightChild[i]`, return `true` if and only if **all** the given nodes form **exactly one** valid binary tree.

If node `i` has no left child then `leftChild[i]` will equal `-1`, similarly for the right child.

Note that the nodes have no values and that we only use the node numbers in this problem.

**Example 1:**

![](https://assets.leetcode.com/uploads/2019/08/23/1503_ex1.png)

**Input:** n = 4, leftChild = [1,-1,3,-1], rightChild = [2,-1,-1,-1]
**Output:** true

**Example 2:**

![](https://assets.leetcode.com/uploads/2019/08/23/1503_ex2.png)

**Input:** n = 4, leftChild = [1,-1,3,-1], rightChild = [2,3,-1,-1]
**Output:** false

## Logic & Algorithms:

There are two main steps to this problem and both run in linear time. It is important to remember that -1 is not a node in this tree and is equivalent to `NULL` for a treeNode

### Finding the Root

We first have to find the root of the tree. The root of the tree has an important property, it is not the child of any other node. Since we have a list of all the left and right children in the tree, we add all the valid children to a HashSet. We can then go from 0 to `n -1`, all the nodes in the tree, and the first node not in the HashSet should be our root. We can also short-circuit the whole code at this point, if we have more or less than `n-1` children in the tree, we can automatically return false as there is either some cycle (for children = `n`) or some disconnected leaf(s) node(s) ( for `children < n - 1`).   

### Cycle Detection and Connected Binary Tree Verification 

Once we have found the root, it is a matter of running BFS/DFS on the whole tree to make sure it is all connected, and maintaining a visited set to check for cycles. We start by offering the root to the queue (for BFS), then while the queue is not empty we visit the first node in the queue and add it's children to the queue. If we ever visit a node, we have ever visited before, we return false as there is either a cycle or like example 2, a node has multiple parents. 

Once we finish our BFS from the root, we still need to ensure we visited every node. That is done by simply returning that our visited set is of length `n` meaning we visited all `n` nodes in the tree and we already know that tree is a valid binary tree. 

## Pseudo Code:

1. Intialize the visited set, integer queue, and set of all nodes that are not the root called `notRoot` as empty
2. For every pair of children(left and right children are of same length)
	1. Get the current left and right child of node `i` and set them equal to `left` and `right`
	2. If `left` is valid
		1. Add the `left` child to the `notRoot` set
	3. If `right` is valid
		1. Add the `right` child to the `notRoot` set
3. If we have fewer or more than the number of nodes we expect as children in the tree ($n-1$) then short circuit and return false
4. Initialize the root to 0
5. For each node `i` from 0 to `n`
	1. If the set doesn't contain `i`, it must be the root
		1. Offer `i` to the heap to start the traversal
		2.  Break out of the while loop, unnecessary checking further we can guarantee the rest are also children
6.  While the queue is not empty
	1. Get the `currentNode` as the top node of the queue
	2. If we have already visited this child, 
		1. There is either a cycle or more than one parent for the node, return false, the tree is invalid
	3. Add `currentNode` to the visited set
	2. If the left child is valid
		1. Add the left child to the `notRoot` set
	3. If right child is valid
		1. Add the right child to the `notRoot` set
7. Return if we have visited all nodes in the tree, in other words that the tree is fully connected


## Time Complexity O(n) - Three O(n) loops, two for root checking and one for DFS 
*Where O(DFS) = O(V + E) but we know that a binary tree has n - 1 edges hence still O(n)*
## Space Complexity O(n) - Size of Visited Set and notRoot Set always, Queue in worst case 
## Java Code:

```java
class Solution {
    public boolean validateBinaryTreeNodes(int n, int[] leftChild, int[] rightChild) {
        Set<Integer> visited = new HashSet<>(); 
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> notRoot = new HashSet<>();
        for(int i = 0; i < leftChild.length; i++){
            int left = leftChild[i];
            int right = rightChild[i];
            if(left != -1){
                notRoot.add(left);
            }
            if(right != -1){
                notRoot.add(right);
            }
        }
        if(!(notRoot.size() == (n - 1))){
            return false; 
        }
        int root = 0;
        for(int i = 0; i < n; i++){
            if(!notRoot.contains(i)){
                queue.offer(i);
                break; 
            }
        }
        while(!queue.isEmpty()){
            int currentNode = queue.poll();
            if(visited.contains(currentNode)){
                return false;
            }
            visited.add(currentNode);
            if(leftChild[currentNode] != -1){
                queue.offer(leftChild[currentNode]);
            }
            if(rightChild[currentNode] != -1){
                queue.offer(rightChild[currentNode]);
            }
        }
        return visited.size() == n; 
    }
}
```
