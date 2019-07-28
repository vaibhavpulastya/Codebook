package SegmentTree;

public class segTree{
    int[] tree;
    int N;

    segTree(int N, int[] a){
        tree = new int[2*N];
        this.N = N;
        build(N, a);
    }


    void build(int N, int[] a){
        for(int i = N; i < 2*N; i++) tree[i] = a[i - N];
        for (int i = N - 1; i > 0; --i) tree[i] = tree[i<<1]+ tree[i<<1|1];
    }

    int query(int l, int r) {
        int res = 0;
        for (l += N, r += N; l < r; l >>= 1, r >>= 1) {
            if ((l & 1) == 1) res += tree[l++];
            if ((r & 1) == 1) res += tree[--r];
        }
        return res;
    }

//    int query(int l, int r){
//        l += N;
//        r += N;
//        int ans = 0;
//        // [l, r)
//        while(l <= r){
//            if(l%2 == 1){
//                ans += tree[l];
//                l++;
//            }
//            if(r%2 == 1){
//                ans += tree[r-1];
//                r--;
//            }
//            l /= 2;
//            r /= 2;
//        }
//
//        return ans;
//    }

    void update(int idx, int val){
        idx += N;
        tree[idx] = val;

        for(int i = idx; i >= 1; i /= 2){
            tree[i/2] = tree[i] + tree[i^1];
        }
    }
}




