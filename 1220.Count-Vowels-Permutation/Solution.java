// ## 1D O(1) space optimized solution
import java.util.*;
class Solution {
    public int countVowelPermutation(int n) {
        int MOD = (int)Math.pow(10, 9) + 7;
        // for each length
        int[] prevLength = new int[5];
        Arrays.fill(prevLength, 1); //initially every vowel has one combination, itself
        
        for(int i = 1; i < n; i++) { 
            //This loop is the current length - 1 where when i = 1 we are dealing with strings of length 2
            //Deal with the possibility of each vowel where a = 0, e = 1, i = 2, o = 3, u = 4
            int[] nextLength = new int[5];  // temporary array to hold the new counts
            
            //a case: could've had e, i, or u previously, note we have to nested mod here as the numbers get very large 
            nextLength[0] += ((prevLength[1] + prevLength[2]) % MOD + prevLength[4]) % MOD; 
            //e case: could've had a or i previously
            nextLength[1] += (prevLength[0] + prevLength[2]) % MOD; 
            //i case: could've had e or o previously            
            nextLength[2] += (prevLength[1] + prevLength[3]) % MOD; 
            //o case: could've had i previously
            nextLength[3] += (prevLength[2]) % MOD; 
            //u case: could've had i or o previously
            nextLength[4] += (prevLength[2] + prevLength[3]) % MOD;
            
            // assign the nextLength to the prevLength
            prevLength = nextLength;
        }
        
        int result = 0;
        //We now loop through all the combinations of length n ending at any vowel and add them
        for (int finalLength : prevLength) {
            result = (result + finalLength) % MOD;
        }
        return result;
    }
}

// ## 2D O(5n) = O(n) space optimized solution

class Solution {
    public int countVowelPermutation(int n) {
        int MOD = (int)Math.pow(10, 9) + 7;
        // for each length
        int[][] dp = new int[n][5];
        Arrays.fill(dp[0], 1); //initially every vowel has one combination, itself
        for(int i = 1; i < n; i++) { 
            //This loop is the current length - 1 where when i = 1 we are dealing with strings of length 2
            //Deal with the possibility of each vowel where a = 0, e = 1, i = 2, o = 3, u = 4
            dp[i][0] += ((dp[i - 1][1] + dp[i - 1][2]) % MOD + dp[i - 1][4]) % MOD; //a case: could've had e, i, or u previously
            dp[i][1] += (dp[i - 1][0] + dp[i - 1][2]) % MOD; //e case: could've had a or i previously
            dp[i][2] += (dp[i - 1][1] + dp[i - 1][3]) % MOD; //i case: could've had i or o previously
            dp[i][3] += (dp[i - 1][2]) % MOD; //o case: could've had i previously
            dp[i][4] += (dp[i - 1][2] + dp[i - 1][3]) % MOD; //u case: could've had i or o previously
        }
        int result = 0;
        //We now loop through all the combinations of length n ending at any vowel and add them
        for (int finalLength : dp[n - 1]) {
            result = (result + finalLength) % MOD;
        }
        return result;
    }
}
