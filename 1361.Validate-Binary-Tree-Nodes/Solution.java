class Solution {
    public boolean validateBinaryTreeNodes(int n, int[] leftChild, int[] rightChild) {
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> notRoot = new HashSet<>();
        for (int i = 0; i < leftChild.length; i++) {
            int left = leftChild[i];
            int right = rightChild[i];
            if (left != -1) {
                notRoot.add(left);
            }
            if (right != -1) {
                notRoot.add(right);
            }
        }
        if (!(notRoot.size() == (n - 1))) {
            return false;
        }
        int root = 0;
        for (int i = 0; i < n; i++) {
            if (!notRoot.contains(i)) {
                queue.offer(i);
                break;
            }
        }
        while (!queue.isEmpty()) {
            int currentNode = queue.poll();
            if (visited.contains(currentNode)) {
                return false;
            }
            visited.add(currentNode);
            if (leftChild[currentNode] != -1) {
                queue.offer(leftChild[currentNode]);
            }
            if (rightChild[currentNode] != -1) {
                queue.offer(rightChild[currentNode]);
            }
        }
        return visited.size() == n;
    }
}
