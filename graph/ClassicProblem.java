// https://codeforces.com/problemset/problem/464/E

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class ClassicProblem {
    static long fastPower(long a, long b, long MOD){ // (a^b)%MOD
        long res = 1;
        while (b>0) {
            if((b&1) != 0){ // power odd
                res = ((res%MOD)*(a%MOD)) % MOD;
                b--;
            } 
            a = ((a%MOD)*(a%MOD)) % MOD;
            b = b >> 1;
        }
        return res;
    }
    static int count = 0;
    static boolean f = false;
    static class Edge implements Comparable<Edge> {
        int node;
        // int src;
        long weight;
        boolean isPower;
        
        public Edge( int node, long weight, boolean isPower) {
            // this.src = src;
            this.node = node;
            this.weight = (isPower) ? fastPower(2, weight, 1000000007) : weight;
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
        if(i == -1){f = true; return;}
        count++;
        pathPrint(parent, parent[i]);
        if (f){ System.out.println(count); f = false;}
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
            adj.get(a).add(new Edge(b, c, true));
            adj.get(b).add(new Edge(a, c, true));
        }
        int start = sc.nextInt();
        int end = sc.nextInt();


        // dijkstra implementation
        PriorityQueue<Edge> Q = new PriorityQueue<>();
        boolean vis[] = new boolean[n+1];
        long weight[] = new long[n+1];
        int parent[] = new int[n+1];
        parent[start] = -1;
        Arrays.fill(weight, Long.MAX_VALUE);
        weight[start] = 0;
        
        Q.add(new Edge(start, 0, false));

        while (!Q.isEmpty()) {
            Edge e = Q.poll();
            if(vis[e.node]) continue;
            vis[e.node] = true;
            if(e.node == end) break;

            for (Edge p : adj.get(e.node)) {
                if (!vis[p.node]) {
                    long newWeight = (p.weight % 1000000007 + weight[e.node] % 1000000007) % 1000000007;; 
                    if (weight[p.node] > newWeight) {
                        weight[p.node] = newWeight;
                        parent[p.node] = e.node;
                        Q.add(new Edge(p.node, newWeight, false));
                    }
                }
            }
        }
        if(weight[end] == Long.MAX_VALUE){System.out.println(-1); return;}
        System.out.println(weight[end]);
        pathPrint(parent, end);
    }



}
