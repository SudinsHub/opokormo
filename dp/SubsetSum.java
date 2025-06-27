public class SubsetSum {
    static Boolean isSubsetSum(int arr[], int target) {
        int k = target;
        boolean[][] memo = new boolean[arr.length+1][k+1];
        // base cases
        for(int i=0; i<=arr.length; i++){
            memo[i][0] = true;
        }

        for(int i=1; i<=arr.length; i++){
            for(int j=1; j<=k; j++){
                boolean notTaken = memo[i - 1][j];
                boolean taken = false;
                if(arr[i-1] <= j){
                    
                    taken = memo[i - 1][j-arr[i-1]];
                }
                memo[i][j] = notTaken || taken;
    
            }
        }
        return memo[arr.length][k];
        
    }
    
    static boolean helper(int arr[], int ind, int target, int[][] memo){
        if(memo[ind][target] != -1) return memo[ind][target] ==1 ? true : false; 
        if(target == 0){
            memo[ind][target] = 1;
            return true;
        }
        if(ind == arr.length){
            memo[ind][target] = 0;
            return false;
        } 
        boolean notTaken = helper(arr, ind + 1, target, memo);
        boolean taken = false;
        if(arr[ind] <= target) taken = helper(arr, ind + 1, target-arr[ind], memo);
        memo[ind][target] = notTaken || taken ? 1 : 0;
        return notTaken || taken;
    }
}