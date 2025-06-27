// https://www.hackerearth.com/practice/algorithms/graphs/strongly-connected-components/practice-problems/algorithm/a-walk-to-remember-qualifier2/
// directed cycle detection

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Remember {
    static class Edge{
        int node;
        // int src;
       
        public Edge( int node) {
            // this.src = src;
            this.node = node;
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

    static boolean dfs(List<List<Edge>> adj, int ind, int[] color, boolean isCycle[]){
        boolean flag = false;
        boolean flag2 = false;
        
        color[ind] = 1;
        for (Edge e : adj.get(ind)) {
            if(color[e.node] == 0){
                if(dfs(adj, e.node, color, isCycle)) flag = true;
            }
            else if(color[e.node] == 1){
                isCycle[e.node] = true; 
                flag = true;
            }
        }
        color[ind] = 2;
        if(isCycle[ind]) flag2 = true;
        if(flag) isCycle[ind] = true;
        return flag2 ? false : flag;
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
            adj.get(a).add(new Edge(b));
        }
        
        int[] color = new int[n+1];
        boolean isCycle[] = new boolean[n+1];
        for (int i = 1; i <= n; i++) {
            if(color[i] == 0) dfs(adj, i, color, isCycle); 
        }
        
        for (int i = 1; i <= n; i++) {
            System.out.print((isCycle[i] ? 1 : 0) + " ");
        }

    }
}
