public class LCS {
    public int longestCommonSubsequenceRec(String text1, String text2) {
        int[][] memo = new int[1001][1001];
        // Fill the array with -1
        for (int i = 0; i < 1001; i++) {
            for (int j = 0; j < 1001; j++) {
                memo[i][j] = -1;
            }
        }
        return helper(memo, text1, text2, text1.length()-1, text2.length()-1);
    }
    public int longestCommonSubsequence(String text1, String text2) {
        int[][] memo = new int[text1.length()+1][text2.length()+1];
        
        // base case
        for (int i = 0; i < text1.length()+1; i++) {
            memo[i][0] = 0;
        }
        for (int i = 0; i < text2.length()+1; i++) {
            memo[0][i] = 0;
        }
        // tabulation
        for (int i = 1; i < text1.length()+1; i++) {
            for (int j = 1; j < text2.length()+1; j++) {
                if(text1.charAt(i-1) == text2.charAt(j-1)) memo[i][j] = 1 + memo[i-1][j-1];
                else memo[i][j] = Math.max(memo[i-1][j], memo[i][j-1]);
            }
        }
        
        return memo[text1.length()][text2.length()];
    }
    public int helper(int memo[][], String text1, String text2, int ind1, int ind2) {
        if(ind1 < 0 || ind2 < 0) return 0;
        if(memo[ind1][ind2] != -1) return memo[ind1][ind2];
        if(text1.charAt(ind1) == text2.charAt(ind2)) 
            return memo[ind1][ind2] = 1 + helper(memo, text1, text2, ind1-1, ind2-1);
        return memo[ind1][ind2] = Math.max(helper(memo, text1, text2, ind1-1, ind2), helper(memo, text1, text2, ind1, ind2-1));
    }
}