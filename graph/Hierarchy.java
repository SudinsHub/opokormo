// MST for  directed graph
// could not solve with known algorithms 
// attempting with dijkstra wasn't wise decision
// https://codeforces.com/contest/17/problem/B

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;


public class Hierarchy{
    static class Edge implements Comparable<Edge> {
        int dist, weight;
        // int src;
        public Edge(int dist, int weight) {
            // this.src = src;
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


    public static void main(String arg[]){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] qual = new int[n+1];
        boolean[] dhuktese = new boolean[n+1];
        boolean[] vis = new boolean[n+1];
        List<List<Edge>> edges = new ArrayList<>(n+1);
        PriorityQueue<Edge> q  = new PriorityQueue<>(1000000);
        int src = 1;

        for (int i = 0; i <= n; i++) {
            edges.add(new ArrayList<>());
        }
        for (int i = 1; i <= n; i++) {
            int x = sc.nextInt();
            qual[i] = x;
        }

        int m = sc.nextInt();
        for (int i = 0; i < m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();

            if(qual[a] > qual[b]){
                edges.get(a).add(new Edge(b, c));
                dhuktese[b] = true;
            }
        }

        boolean gotSrc = false;
        for (int i = 1; i < dhuktese.length; i++) {
            if(!dhuktese[i] && gotSrc){
                System.out.println("-1");
                return;
            }
            else if(!dhuktese[i]){
                src = i;
                gotSrc = true;
            }
        }

//tried with prim's
        
        // int cost = 0;
        q.add(new Edge(src, 0));
        // for (int p = 0; p < n; p++) {
        //     Edge e = q.poll(); 
        //     while(vis[e.dist]) e = q.poll();
        //     vis[e.dist] = true;
        //     cost+=e.weight;
    
        //     for (Edge edge : edges.get(e.dist)) {
        //         if(vis[edge.dist]) continue;
        //         q.add(edge);
        //     }
        // }

// tried with dijkstra
        int weight[] = new int[n+1];
        Arrays.fill(weight, Integer.MAX_VALUE);
        weight[src] = 0;
        int max = 0;

        for (int i = 0; i < n; i++) {
            Edge e = q.poll();
            while(vis[e.dist]) e = q.poll();
            vis[e.dist] = true;
            // System.out.println(e.dist);
            max += e.weight;

            for (Edge p : edges.get(e.dist)) {
                if(!vis[p.dist]){
                    if(weight[p.dist] > (p.weight + weight[e.dist])){
                        weight[p.dist] = p.weight + weight[e.dist];
                        q.add(p);
                    }
                }
            }
        }
        System.out.println(max);
        sc.close();
    }
}