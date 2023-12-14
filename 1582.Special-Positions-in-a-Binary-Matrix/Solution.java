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
        int result = 0;
        for(int row = 0; row < rows; row++){
            for(int col = 0; col < cols; col++){
                if(rowSum[row] == 1 && colSum[col] == 1 && mat[row][col] == 1){
                    result++;
                }
            }
        }
        return result; 
    }
}
