package SegmentTree;

public class segTree{
    int[] tree;
    int N;  // !!! don't mess up the value of N !!! - it's the number of elements 

    segTree(int N, int[] a){
        tree = new int[2*N];
        this.N = N;
        build(N, a);
    }

    void build(int N, int[] a){
        for(int i = N; i < 2*N; i++) tree[i] = a[i - N];
        for (int i = N - 1; i > 0; --i) tree[i] = Math.max(tree[i<<1], tree[i<<1|1]);
    }

    int query(int l, int r) { // [l,r)
        int res = 0;
        l += N;
        r += N;
        while(l < r) {
            if ((l&1) > 0) res = Math.max(res, tree[l++]);
            if ((r&1) > 0) res = Math.max(res, tree[--r]);
            l >>= 1; r >>= 1;
        }
        return res;
    }

    void update(int idx, int val){
        idx += N;
        tree[idx] = val;

        for(int i = idx; i > 1; i /= 2){
            tree[i/2] = Math.max(tree[i], tree[i^1]);
        }
    }
}



class SegmentTree {
    //min segtree recursive code NOTE - tree T is 1 indexed

    int[] T;
    int[] A;
    int N;

    SegmentTree(int N, int[] A){
        this.A = A;
        this.N = N;
        T = new int[4*N];
        //build(1, 0, N - 1);
    }

    void build(int node, int start, int end) {
        if (start == end) {
            T[node] = A[start];
        } else {
            int mid = (start + end) / 2;
            build(2 * node, start, mid);
            build(2 * node + 1, mid + 1, end);
            T[node] = Math.min(T[2 * node], T[2 * node + 1]);
        }
    }

    void update(int node, int start, int end, int idx, int val) {
        if(start == end) {
            A[idx] += val;
            T[node] += val;
        }
        else {
            int mid = (start + end) / 2;
            if(start <= idx && idx <= mid)
                update(2*node, start, mid, idx, val);
            else
                update(2*node+1, mid+1, end, idx, val);
            T[node] = Math.min(T[2*node], T[2*node+1]);
        }
    }

    int query(int node, int start, int end, int l, int r) {  // answers - [l,r], start = 0, end = N-1
        if (r < start || end < l) {
            return (int) 1e9;   // -- NOTE
        }
        if (l <= start && end <= r) {
            return T[node];
        }
        int mid = (start + end) / 2;
        int p1 = query(2 * node, start, mid, l, r);
        int p2 = query(2 * node + 1, mid + 1, end, l, r);
        return Math.min(p1, p2);
    }
}
