package SegmentTree;

// tested

public class Lazy{

    long[] tree;
    long[] lazy;
    int SZ;

    Lazy(int N){
        int SZ = Integer.highestOneBit(N);
        if(SZ != N) SZ <<= 1;
        tree = new long[2*SZ];
        lazy = new long[2*SZ];  // NOTE - check initialised value
        this.SZ = SZ;
    }

    // updates tree index ind according to corresponding lazy value and pushes it down to its children

    void push(int ind, int L, int R) {
        tree[ind] += (R-L+1)*lazy[ind];
        if (L != R){ lazy[2*ind] += lazy[ind]; lazy[2*ind+1] += lazy[ind]; }
        lazy[ind] = 0;
    }


    void pull(int ind) {
        tree[ind] = tree[2*ind]+tree[2*ind+1];
    }

    void build(){
        for(int i = SZ - 1; i >= 1; i--) pull(i);
    }

    // initially ind = 1, L = 0, R = SZ - 1
    void upd(int lo, int hi, long inc, int ind, int L, int R) {
        push(ind,L,R);
        if (hi < L || R < lo) return;
        if (lo <= L && R <= hi) {
            lazy[ind] = inc;
            push(ind,L,R);
            return;
        }

        int M = (L+R)/2;
        upd(lo,hi,inc,2*ind,L,M); upd(lo,hi,inc,2*ind+1,M+1,R);
        pull(ind);
    }

    // initially ind = 1, L = 0, R = SZ - 1
    long query(int lo, int hi, int ind, int L, int R) {
        push(ind,L,R);
        if (lo > R || L > hi) return 0;
        if (lo <= L && R <= hi) return tree[ind];

        int M = (L+R)/2;
        return query(lo,hi,2*ind,L,M) + query(lo,hi,2*ind+1,M+1,R);
    }

}