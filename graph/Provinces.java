// https://lightoj.com/problem/hit-the-light-switches

import java.util.*;

public class Provinces{

    public static void noOfProvinces(int ind, boolean[] vis, List<List<Integer>> adj){
        vis[ind]= true;
        for(int x : adj.get(ind)){
            if(!vis[x]) {
                noOfProvinces(x, vis, adj);
            }
        }
    }
    public static void topo(int ind, boolean[] vis, List<List<Integer>> adj, List<Integer> stack){
        vis[ind]= true;
        for(int x : adj.get(ind)){
            if(!vis[x]) {
                topo(x, vis, adj, stack);
            }
        }
        stack.add(ind);
    }
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int t = scn.nextInt();
        for (int i = 0; i < t; i++) {
            List<List<Integer>> adj = new ArrayList<>();
            List<Integer> stack = new ArrayList<>();
            int n = scn.nextInt();
            int m = scn.nextInt();

            boolean vis[] = new boolean[n+1];
            boolean vis2[] = new boolean[n+1];

            // preparing the graph
            for (int j = 0; j <= n; j++) adj.add(new ArrayList<>());
 
            for (int j = 0; j < m; j++) {
                int a = scn.nextInt();
                int b = scn.nextInt();
                adj.get(a).add(b);
            }
            // preparing topological order
            for (int j = 1; j <= n; j++){
                if(!vis[j]){
                    topo(j, vis, adj, stack); 
                } 
            }
            // counting no of provinces
            int count = 0;
            if(!stack.isEmpty()) {
                for(int j = stack.size()-1; j>=0; j--){
                    if(!vis2[stack.get(j)]){
                        noOfProvinces(stack.get(j), vis2, adj);
                        count++;
                    }
                }
            }
            System.out.println("Case " + (i+1) + ": " + count);
            
        }
        
        scn.close();
    }
}
