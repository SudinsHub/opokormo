import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

class Edge implements Comparable<Edge> {
    int node;
    // int src;
    int weight;
    
    public Edge( int node, int weight) {
        // this.src = src;
        this.node = node;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge that) {
        if(that.weight > this.weight) return 1;
        if(that.weight == this.weight) return 0;
        return -1; 
    }
}


public class Dijkstra2 {
    public int networkDelayTime(int[][] times, int n, int k) {
        int max = 0;

        List<List<Edge>> adj = new ArrayList<>(n+1);
        for (int i = 0; i <= n; i++) adj.add(new ArrayList<>());
        
        for (int i = 0; i < times.length; i++) {
            adj.get(times[i][0]).add(new Edge(times[i][1], times[i][2]));
        }

        
        PriorityQueue<Edge> Q = new PriorityQueue<>();
        boolean vis[] = new boolean[n+1];
        int weight[] = new int[n+1];
        Arrays.fill(weight, Integer.MAX_VALUE);
        weight[k] = 0;
        
        Q.add(new Edge(k,  1));
        // insertion -> elogv 
        // +
        // vlogv <- extract
        // e songkhok insertion, v songkhok deletion 

        while (!Q.isEmpty()) { 
            Edge e = Q.poll(); // log(v)
            vis[e.node] = true;
            if(weight[e.node] > max) max = weight[e.node];

            for (Edge p : adj.get(e.node)) { // E
                if(!vis[p.node]){
                    if(weight[p.node] > (p.weight + weight[e.node])){
                        weight[p.node] = p.weight + weight[e.node];
                        Q.add(new Edge(p.node, weight[p.node]));  // log(V) 
                    }
                }
            }
        }

        for (int i = 1; i < weight.length; i++) {
            if(weight[i] == Integer.MAX_VALUE) return -1;
        }
        return max;
    }
}
