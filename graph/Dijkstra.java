import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

class Edge implements Comparable<Edge> {
    int node;
    // int src;
    double weight;
    
    public Edge( int node, double weight) {
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

public class Dijkstra {
    public double maxProbability(int n, int[][] edges, double[] succProb, int start_node, int end_node) {
        List<List<Edge>> adj = new ArrayList<>(n);
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        
        for (int i = 0; i < succProb.length; i++) {
            adj.get(edges[i][0]).add(new Edge(edges[i][1], succProb[i]));
        }

        
        PriorityQueue<Edge> Q = new PriorityQueue<>();
        boolean vis[] = new boolean[n];
        double weight[] = new double[n];
        Arrays.fill(weight, -1.0);
        weight[start_node] = 1.0;
        
        Q.add(new Edge(start_node,  1.0));

        while (!vis[end_node] && !Q.isEmpty()) {
            Edge e = Q.poll();
            vis[e.node] = true;

            for (Edge p : adj.get(e.node)) {
                if(!vis[p.node])
                if(weight[p.node] < (p.weight * Math.abs(weight[e.node]))){
                    weight[p.node] = p.weight * Math.abs(weight[e.node]);
                    Q.add(new Edge(p.node, weight[p.node]));
                }
            }
        }

        return (weight[end_node] == -1.0)? 0.0 : weight[end_node];
    }
    
}