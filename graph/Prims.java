import java.util.PriorityQueue;

class PQElement implements Comparable<PQElement>{
    int node, weight;

    public PQElement(int node, int weight) {
        this.node = node;
        this.weight = weight;
    }

    @Override
    public int compareTo(PQElement that) {
        if(that.weight < this.weight) return 1; // that ke age jete dibe
        return -1;
    }
    
}


public class Prims {
    int cost;
    boolean[] vis;
    PriorityQueue<PQElement> q;


    public int minCostConnectPoints(int[][] points) {
        cost = 0;
        vis = new boolean[1001];
        q = new PriorityQueue<>(1000000);    
        q.add(new PQElement(0, 0));
        for (int p = 0; p < points.length; p++) {
            PQElement e = q.poll(); 
            while(vis[e.node]) e = q.poll();
            vis[e.node] = true;
            cost+=e.weight;
    
            for(int i=0; i<points.length; i++){
                if(vis[i] || e.node == i) continue;
                int w = Math.abs(points[i][0]- points[e.node][0]) + Math.abs(points[i][1]- points[e.node][1]);
                q.add(new PQElement(i, w));
            }
        }
        return cost;
    }
}
