import java.util.ArrayList;

class Solution {
    // Function to return max value that can be put in knapsack of capacity.
    static int knapSack(int capacity, int val[], int wt[]) {
        // Initialize memoization table
        ArrayList<ArrayList<Integer>> memo = new ArrayList<>();

        // Fill the list with -1 using a loop
        for (int i = 0; i <= wt.length; i++) {
            ArrayList<Integer> row = new ArrayList<>();
            for (int j = 0; j <= capacity; j++) {
                row.add(-1);  // Initialize each cell with -1
            }
            memo.add(row);
        }

        return helper(capacity, val, wt, 0, memo);
    }

    static int helper(int capacity, int val[], int wt[], int ind, ArrayList<ArrayList<Integer>> memo) {

        // Base case: If we have considered all items
        if (ind == wt.length) return 0;

        // If this state has already been computed, return the result
        if (memo.get(ind).get(capacity) != -1) return memo.get(ind).get(capacity);

        // Recursive case: Consider both taking and not taking the current item
        int taken = 0;
        if (wt[ind] <= capacity) {
            taken = val[ind] + helper(capacity - wt[ind], val, wt, ind + 1, memo);
        }
        int notTaken = helper(capacity, val, wt, ind + 1, memo);

        // Store the result in the memoization table and return the maximum value
        memo.get(ind).set(capacity, Math.max(taken, notTaken));
        return memo.get(ind).get(capacity);
    }
    static int knapSackTabu(int capacity, int val[], int wt[]) {
        int[][] memo = new int[wt.length+1][capacity+1];
        for (int i = 0; i <= capacity; i++) {
            memo[0][i] = 0;  // No items, so no value at any capacity
        }

        for (int i = 1; i <= wt.length; i++) {
            for (int j = 0; j <= capacity; j++) {
                int taken = 0;
                if (wt[i-1] <= capacity && j - wt[i-1] >= 0) {
                    taken = val[i-1] + memo[i-1][j - wt[i-1]];
                }
                int notTaken = memo[i-1][j];
                memo[i][j] = Math.max(taken, notTaken);
            }
        }

        return memo[wt.length][capacity];
    }

}