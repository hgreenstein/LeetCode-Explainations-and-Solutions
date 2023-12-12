// ### Basic DP Approach with O($m*n$) memory:

class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        int[][] dp = new int[text1.length() + 1][text2.length() + 1];
        for(int i = 0; i < text1.length(); i++){
            for(int j = 0; j < text2.length(); j++){
                if(text1.charAt(i) == text2.charAt(j)){
                    dp[i + 1][j + 1] = dp[i][j] + 1;
                }
                else{
                    dp[i + 1][j + 1] = Math.max(dp[i][j + 1], dp[i + 1][j]);
                }
            }
        }
        return dp[text1.length()][text2.length()]; 
    }
}

// ### Memory Optimized Approach Space: O(n)
class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        int[] previousDp = new int[text2.length() + 1];
        int[] dp = new int[text2.length() + 1]; 
        for(int i = 0; i < text1.length(); i++){
            dp = new int[text2.length() + 1]; 
            for(int j = 0; j < text2.length(); j++){
                if(text1.charAt(i) == text2.charAt(j)){
                    dp[j + 1] = previousDp[j] + 1;
                }
                else{
                    dp[j + 1] = Math.max(previousDp[j + 1], dp[j]);
                }
            }
            previousDp = dp; 
        }
        return previousDp[text2.length()]; 
    }
}
