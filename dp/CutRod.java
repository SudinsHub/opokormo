public class CutRod {
    public int cutRod(int[] price) {
        // code here
        int memo[][] = new int[price.length][price.length+1];
        for (int i = 0; i < price.length; i++) {
            for (int j = 0; j <= price.length; j++) {
                memo[i][j] = -1;
            }
        }
        return helper(price, 0, price.length, memo);
    }
    
    static int helper(int[] price, int ind, int len, int[][] memo){
        if (len == 0) return 0;
        if(ind == price.length) return 0;
        if (memo[ind][len] != -1) return memo[ind][len];

        int taken = 0;
        if (len >= ind + 1) {
            taken = price[ind] + helper(price, ind, len - (ind + 1), memo);
        }
        int notTaken = helper(price, ind + 1, len, memo);

        return memo[ind][len] = Math.max(taken, notTaken);
    }

    public int cutRodTabu(int[] price) {
        int memo[][] = new int[price.length+1][price.length+1];
        for (int i = 0; i < price.length; i++) memo[0][i] = 0;
        
        for (int i = 1; i <= price.length; i++) {
            memo[i][0] = 0;
            for (int j = 1; j <= price.length; j++) {
                int taken = 0;
                if (j >= i) {
                    taken = price[i-1] +  memo[i][j - i];
                }
                int notTaken = memo[i - 1][j];
                memo[i][j] = Math.max(taken, notTaken);
            }
        }
        return memo[price.length][price.length];
    } 
}
