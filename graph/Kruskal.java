import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Edge implements Comparable<Edge> {
    int src, dist, weight;
    
    public Edge(int src, int dist, int weight) {
        this.src = src;
        this.dist = dist;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge that) {
        if(that.weight > this.weight) return -1;
        if(that.weight == this.weight) return 0;
        return 1; 
    }
}

public class Kruskal {
    static int parent[];
    static int size[];


    static int find_set(int v) { 
        if (v == parent[v])
            return v;
        return parent[v] = find_set(parent[v]);
    }

    static void make_set_size(int v) { // union by size
        parent[v] = v;
        size[v] = 1;
    }

    static void union_sets_size(int a, int b) { // union by size
        a = find_set(a);
        b = find_set(b);
        if (a != b) {
            if (size[a] < size[b]){
                int t = a;
                a = b;
                b = t;
            }
            parent[b] = a;
            size[a] += size[b];
            size[b] = size[a];
        }
    }

    static int spanningTree(int V, int E, List<List<int[]>> adj) {
        List<Edge> edges = new ArrayList<>();
        
        for (int i = 0; i < V; i++) {
            for (int j=0; j < adj.get(i).size(); j++) {
                int src = i;
                int dist = adj.get(i).get(j)[0];
                int weight = adj.get(i).get(j)[1];
                if(i<dist){
                    edges.add(new Edge(src, dist, weight));
                }
            }
        }

        Collections.sort(edges);

        parent = new int[1000];
        size = new int[1000];
        for (int i = 0; i < parent.length; i++) {parent[i] = i; size[i] = 1;}
        int sum = 0, edgesUsed = 0;
        for (Edge edge : edges) { 
            if (find_set(edge.src) != find_set(edge.dist)) {
                sum += edge.weight;
                union_sets_size(edge.src, edge.dist);
                edgesUsed++;
                if (edgesUsed == V - 1) break; // MST completed
            }
        }


        return sum;
    }
}