class Solution {
    public int numSpecial(int[][] mat) {
        int rows = mat.length, cols = mat[0].length;
        
        int[] colSum = new int[cols];
        int[] rowSum = new int[rows];
        for(int row = 0; row < rows; row++){
            for(int col = 0; col < cols; col++){
                rowSum[row] += mat[row][col];
                colSum[col] += mat[row][col];
            }
        }
        boolean[] rowCountOne = new boolean[rows];
        boolean[] colCountOne = new boolean[cols];
        for(int i = 0; i < Math.max(rows, cols); i++){
            if(i < rows && rowSum[i] == 1){
                rowCountOne[i] = true;
            }
            if(i < cols && colSum[i] == 1){
                colCountOne[i] = true;
            }
        }
        int result = 0;
        for(int row = 0; row < rows; row++){
            for(int col = 0; col < cols; col++){
                if(rowCountOne[row] && colCountOne[col] && mat[row][col] == 1){
                    result++;
                }
            }
        }
        return result; 
    }
}
