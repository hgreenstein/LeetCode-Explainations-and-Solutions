# [341. Flatten Nested List Iterator](https://leetcode.com/problems/flatten-nested-list-iterator/description/)
---
tags:
  - ObjectCreation
  - CustomInterface
  - Stack
  - Queue
  - DFS
  - O_n
  - O_nspace
  - Medium
---
## Problem Statement: 

You are given a nested list of integers `nestedList`. Each element is either an integer or a list whose elements may also be integers or other lists. Implement an iterator to flatten it.

Implement the `NestedIterator` class:

- `NestedIterator(List<NestedInteger> nestedList)` Initializes the iterator with the nested list `nestedList`.
- `int next()` Returns the next integer in the nested list.
- `boolean hasNext()` Returns `true` if there are still some integers in the nested list and `false` otherwise.

Your code will be tested with the following pseudocode:

initialize iterator with nestedList
res = []
while iterator.hasNext()
    append iterator.next() to the end of res
return res

If `res` matches the expected flattened list, then your code will be judged as correct.

**Example 1:**

**Input:** nestedList = \[[1,1],2,[1,1]]
**Output:** [1,1,2,1,1]
**Explanation:** By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,1,2,1,1].

## Logic & Algorithms:

We can either choose to get the next integer whenever it is requested, or we can first un-nest all the integers at the beginning and store it for later. I found the latter to be easier and thus, we first do a #DFS on the nested list unwrapping all integers in order then add them to a queue. 

### Stack/Deque 
To maintain the correct order of elements during the un-nesting, we use a stack that is initialized to the original list. If we pop an element that is still a list, we add them back on to the stack in reverse order. Consider the case of the list [1, [2, 3]], we first add 1 to the queue then encounter the list of [2, 3]. If we pushed them onto the stack in the order 2, 3 we would pop them off in the order 3, 2 which is not correct. If we first reverse the list and push in the order 3, 2 we will pop off in the correct original order 2, 3. 

The only reason we use a Deque instead of a stack is because we can pass the list into the constructor when it is initialized, then use the push and pop operations as expected.

## Pseudo Code:

1. Initialize a new Queue that is empty
2. Initialize a stack that is a deque of the nested list in the original order
	1. In this case, the first element will be at the front and first to pop
3. While the stack is not empty
	1. Get the top element of the stack
	2. If the top element is an integer 
		1. Add the int value to the end of the queue
	3. Else
		1. Get the list of `nestedInteger`s
		2. Reverse the list
		3. Add all `nestedInteger` objects to the stack in this reversed order
4. For the `next()` function
	1. Return the next element on the queue -> `queue.poll()`
5. For the `hasNext()` function
	1. Return if the queue is not empty -> `!queue.isEmpty()`
## Time Complexity O(n) - Up to 2n iterations of the list 
Where 1 is unwrapping the list containing each int n and another is adding the int value to the queue
## Space Complexity O(n) - Storing n numbers in the queue and stack
## Java Code: 

```java
/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return empty list if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
public class NestedIterator implements Iterator<Integer> {
    Queue<Integer> queue = new LinkedList<>();

    public NestedIterator(List<NestedInteger> nestedList) {
        Deque<NestedInteger> stack = new ArrayDeque<>(nestedList);
        while(!stack.isEmpty()){
            NestedInteger nextNestedInt = stack.pop();
            if(nextNestedInt.isInteger()){
                queue.offer(nextNestedInt.getInteger());
            }
            else{
                List<NestedInteger> doubleNestedList = nextNestedInt.getList();
                for(int i = doubleNestedList.size() - 1; i >= 0; i--){
                    stack.push(doubleNestedList.get(i));
                }
            }
        }
    }

    @Override
    public Integer next() {
        return queue.poll(); 
    }

    @Override
    public boolean hasNext() {
        return !queue.isEmpty();
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */
```
