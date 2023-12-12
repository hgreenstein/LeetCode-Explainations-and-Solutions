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
