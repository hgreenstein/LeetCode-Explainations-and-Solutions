// ### O(n) space solution, storing only the current and previous rows

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

// ### O($m*n$) space solution, storing the entire grid

class Solution2 {
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
