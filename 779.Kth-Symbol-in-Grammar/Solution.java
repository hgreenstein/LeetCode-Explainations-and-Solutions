// DFS Tree Traversal O(n) time
class Solution {
    public int kthGrammar(int n, int k) {
        int currentValue = 0;
        int left = 1;
        int right = (int)Math.pow(2, n - 1);
        for(int i = 1; i < n; i++){
            int mid = left + (right - left) / 2;
            if(k <= mid){
                right = mid;
            }
            else{
                currentValue = currentValue == 0 ? 1 : 0;
                left = mid + 1; 
            }
        }
        return currentValue;
    }
}

 // Simulation Code O(n k) time ==***Too Slow***==
class Solution2 {
    public int kthGrammar(int n, int k) {
        int[] previousRow = new int[]{0};
        for(int i = 2; i <= n; i++){
            int[] currentRow = new int[previousRow.length * 2];
            for(int j = 0; j < previousRow.length; j++){
                int previousInt = previousRow[j];       
                int firstIndex = j * 2;
                int secondIndex = j * 2 + 1; 
                switch(previousInt){
                    case 0:
                        currentRow[firstIndex] = 0;
                        currentRow[secondIndex] = 1;
                        break;
                    case 1:
                        currentRow[firstIndex] = 1;
                        currentRow[secondIndex] = 0;
                        break;
                }
            }
            previousRow = currentRow;
        }
        return previousRow[k - 1];
    }
}

