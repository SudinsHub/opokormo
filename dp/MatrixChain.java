public class MatrixChain {
    static int matrixMultiplication(int arr[]) {
        // code here
        int[][] memo = new int[arr.length+1][arr.length+1];
        for(int i=0; i<=arr.length; i++){
            for(int j=0; j<=arr.length; j++){
                memo[i][j] = -1;
            }
        }
        return helper(arr, 1, arr.length-1, memo);
    }
    
    static int helper(int arr[], int start, int end, int[][] memo){
        if(memo[start][end] != -1) return memo[start][end];
        if(start == end ) return memo[start][end] = 0;
        int res = 1000000000;
        for(int i=start; i<end; i++){
            int sum = (arr[start-1]*arr[i]*arr[end]) + helper(arr, start, i, memo) + helper(arr, i+1, end, memo);
            res = Math.min(res, sum);
        }     
        return memo[start][end] = res;
    }
}