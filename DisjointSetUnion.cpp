#include<bits/stdc++.h>
using namespace std;
#define N 100000

struct DSU
{
    int parent[N];
    int size[10000];
    int rank[10000];


    int find_set(int v) { //path compression
        if (v == parent[v])
            return v;
        return parent[v] = find_set(parent[v]);
    }

    void make_set_size(int v) { // union by size
        parent[v] = v;
        size[v] = 1;
    }

    void union_sets_size(int a, int b) { // union by size
        a = find_set(a);
        b = find_set(b);
        if (a != b) {
            if (size[a] < size[b])
                swap(a, b);
            parent[b] = a;
            size[a] += size[b];
        }
    }

    void make_set_rank(int v) { // union by rank
        parent[v] = v;
        rank[v] = 0;
    }

    void union_sets_rank(int a, int b) { // union by rank
        a = find_set(a);
        b = find_set(b);
        if (a != b) {
            if (rank[a] < rank[b])
                swap(a, b);
            parent[b] = a;
            if (rank[a] == rank[b])
                rank[a]++;
        }
    }
};