// https://codeforces.com/problemset/problem/598/D
// Four dimensional bfs
// stored summaiton of pictures of a single room through key

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Museum {
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

    static class Pair {
        int a, b;
        public Pair(int a, int b) {
            this.a = a;
            this.b = b;
        }    
    }
    
    static void dfs(Pair p, Pair k){
        vis[p.a][p.b] = true;
        key[p.a][p.b] = k;
        sum += freq[p.a][p.b];

        if(grf[p.a+1][p.b] && !vis[p.a+1][p.b]){
            dfs(new Pair(p.a+1, p.b), k);
        }
        if(grf[p.a-1][p.b] && !vis[p.a-1][p.b]){
            dfs(new Pair(p.a-1, p.b), k);
            
        }
        if(grf[p.a][p.b+1] && !vis[p.a][p.b+1]){
            dfs(new Pair(p.a, p.b+1), k);
        }
        if(grf[p.a][p.b-1] && !vis[p.a][p.b-1]){
            dfs(new Pair(p.a, p.b-1), k);
        }

    }
    
    
    static boolean grf[][];
    static int freq[][];
    static boolean[][] vis;
    static Pair[][] key;
    static int sum;
    public static void main(String[] args) {
        FastReader sc = new FastReader();
        // Scanner sc = new Scanner(System.in);
        int n  = sc.nextInt();
        int m  = sc.nextInt();
        int k  = sc.nextInt();
        grf = new boolean[n+1][m+1];
        freq = new int[n+2][m+2];
        vis = new boolean[n+1][m+1];
        key = new Pair[n+1][m+1];
        for (int i = 1; i <= n; i++) {
            String a = sc.next();
            for (int j = 1; j <= m; j++) {
                if(a.charAt(j-1) == '.') grf[i][j] = true;
                else {
                    freq[i+1][j]++;  
                    freq[i][j+1]++;  
                    freq[i-1][j]++;  
                    freq[i][j-1]++;
                }  
            }    
        }

        for (int i = 0; i < k; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            if(!vis[x][y]){
                Queue<Pair> q = new LinkedList<>();
                vis[x][y] = true;
                q.add(new Pair(x, y));
                sum = 0;
                while (!q.isEmpty()) {
                    Pair p = q.poll();
                    key[p.a][p.b] = new Pair(x, y);
                    sum += freq[p.a][p.b];
                    if(grf[p.a+1][p.b] && !vis[p.a+1][p.b]){
                            vis[p.a+1][p.b] = true;
                            q.add(new Pair(p.a+1, p.b)); 
                    }
                    if(grf[p.a-1][p.b] && !vis[p.a-1][p.b]){
                            vis[p.a-1][p.b] = true;
                            q.add(new Pair(p.a-1, p.b)); 
                    }
                    if(grf[p.a][p.b+1] && !vis[p.a][p.b+1]){
                            vis[p.a][p.b+1] = true;
                            q.add(new Pair(p.a, p.b+1)); 
                    }
                    if(grf[p.a][p.b-1] && !vis[p.a][p.b-1]){
                            vis[p.a][p.b-1] = true;
                            q.add(new Pair(p.a, p.b-1)); 
                    }
                }
                freq[x][y] = sum;

            } else {
                Pair keyPair = key[x][y]; 
                x = keyPair.a;
                y = keyPair.b;
            }

            System.out.println(freq[x][y]);
        }
        // sc.close();
    }
}
