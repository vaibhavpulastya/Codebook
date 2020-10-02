public class ZAlgo {
    int[] z_function(String s) {
        int n = (int) s.length();
        int[] z = new int[n];
        for (int i = 1, l = 0, r = 0; i < n; ++i) {
            if (i <= r) {
                z[i] = Math.min(r - i + 1, z[i - l]);
            }
            while (i + z[i] < n && s.charAt(z[i]) == s.charAt(i + z[i])){
                ++z[i];
            }
            if (i + z[i] - 1 > r){
                l = i; r = i + z[i] - 1;
            }
        }
        return z;
    }
}
