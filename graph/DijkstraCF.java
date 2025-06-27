// https://codeforces.com/problemset/problem/449/B

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class DijkstraCF {

    static class Edge implements Comparable<Edge> {
        int node;
        // int src;
        long weight;
        
        public Edge( int node, long weight) {
            // this.src = src;
            this.node = node;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge that) {
            if(that.weight > this.weight) return -1;
            if(that.weight == this.weight){
                return 0;        
            }
            return 1; 
        }
    }

    static class FastReader {
        BufferedReader br;
        StringTokenizer st;
        
        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }
        
        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }
        
        int nextInt() {
            return Integer.parseInt(next());
        }
    }

    static void pathPrint(int parent[], int i){
        if(i == -1) return;
        pathPrint(parent, parent[i]);
        System.out.print(i + " ");
    }
    public static void main(String[] args) {
        FastReader sc = new FastReader();
        int n, m;
        n = sc.nextInt();
        m = sc.nextInt();
        List<List<Edge>> adj = new ArrayList<>(n+1);
        for (int i = 0; i <= n; i++) {adj.add(new ArrayList<>());}

        for (int i = 0; i < m; i++){
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();
            adj.get(a).add(new Edge(b, c));
            adj.get(b).add(new Edge(a, c));
        }


        // dijkstra implementation
        PriorityQueue<Edge> Q = new PriorityQueue<>();
        boolean vis[] = new boolean[n+1];
        long weight[] = new long[n+1];
        int parent[] = new int[n+1];
        parent[1] = -1;
        Arrays.fill(weight, Long.MAX_VALUE);
        weight[1] = 0;
        
        Q.add(new Edge(1, 0));

        while (!Q.isEmpty()) {
            Edge e = Q.poll();
            if(vis[e.node]) continue;
            vis[e.node] = true;

            for (Edge p : adj.get(e.node)) {
                if(!vis[p.node]){
                    if(weight[p.node] > (p.weight + weight[e.node])){
                        weight[p.node] = p.weight + weight[e.node];
                        parent[p.node] = e.node;
                        Q.add(new Edge(p.node, weight[p.node])); 
                    }
                }
            }
        }
        if(weight[n] == Long.MAX_VALUE){System.out.println(-1); return;}
        pathPrint(parent, n);
    }


}
