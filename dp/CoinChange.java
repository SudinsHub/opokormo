public class CoinChange {
    public int coinChange(int[] coins, int amount) {
        int[][] memo = new int[coins.length+1][amount+1];
        // base : coins[0] -> out of bound -> haven't got solution (assume, i=i-1) 
        for(int j=0; j<=amount; j++){
            memo[0][j] = 100000;
        }
        // base : amount = 0 -> no coins need to be picked 
        for(int i=0; i<=coins.length; i++){
            memo[i][0] = 0;
        }
        for(int i=1; i<=coins.length; i++){
            for(int j=1; j<=amount; j++){
                int notTaken = memo[i-1][j];
                int taken = 100000;
                if(coins[i-1] <= j) taken = 1 + memo[i][j-coins[i-1]];
                memo[i][j] = Math.min(taken, notTaken);            }
            }
            // int res = h(coins, amount, 0, memo);
            return memo[coins.length][amount] >= 100000 ? -1 : memo[coins.length][amount];
        }
        public int coinChangeRec(int[] coins, int amount) {
            int[][] memo = new int[coins.length+1][amount+1];
            for(int i=0; i<=coins.length; i++){
                for(int j=0; j<=amount; j++){
                    memo[i][j] = -1;
                }
            }
            int res = h(coins, amount, 0, memo);
            return res >= 100000 ? -1 : res;
        }
        int h(int[] coins, int rest, int ind, int[][] memo){
        if(memo[ind][rest] != -1) return memo[ind][rest];
        // hoye gese
        if(rest == 0) return 0;
        
        // hobena
        if(ind == coins.length) return memo[ind][rest] = 100000;

        int notTaken = h(coins, rest, ind+1, memo);
        int taken = 100000;
        if(coins[ind] <= rest) taken = 1 + h(coins, rest-coins[ind], ind, memo);
        return memo[ind][rest] = Math.min(taken, notTaken);
    }
}