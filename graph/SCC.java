// https://www.hackerearth.com/practice/algorithms/graphs/strongly-connected-components/practice-problems/algorithm/benny-and-some-magic/

import java.util.*;

class weightedEdge {
    int dist;
    int weight;

    weightedEdge(int dist, int weight){
        this.dist = dist;
        this.weight = weight;
    }
    
    int getDist(){
        return this.dist;
    }
    
    int getWeight(){
        return this.weight;
    }
}

public class SCC{

    static int maxDiff = 0;

    public static void weightedDFS(int ind, boolean[] vis, List<List<weightedEdge>> adj, int max, int min){
        vis[ind]= true;
        
        // (no outgoing edges)
        if(adj.get(ind).isEmpty() && (max - min > maxDiff)) {
            maxDiff = max - min;
        } else {
            for(weightedEdge x : adj.get(ind)){
                if(!vis[x.getDist()]) {
                    int newMin = Math.min(min, x.getWeight());
                    int newMax = Math.max(max, x.getWeight());
                    weightedDFS(x.getDist(), vis, adj, newMax, newMin);
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        List<List<weightedEdge>> adj = new ArrayList<>();
        int n = scn.nextInt();
        int m = scn.nextInt();

        // Prepare the graph
        for (int j = 0; j <= n; j++) adj.add(new ArrayList<>());
        for (int j = 0; j < m; j++) {
            int a = scn.nextInt();
            int b = scn.nextInt();
            int c = scn.nextInt();
            weightedEdge we = new weightedEdge(b, c);
            adj.get(a).add(we);
        }

        // Calculate maxDiff
        for (int i = 1; i <= n; i++) {
            boolean vis[] = new boolean[n + 1];
            weightedDFS(i, vis, adj, Integer.MIN_VALUE, Integer.MAX_VALUE);
        }
        
        // Print the result
        System.out.println(maxDiff);
        
        scn.close();
    }
}
