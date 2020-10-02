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
