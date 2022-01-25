/*
Mr. Pepcoder has an array A, and his Friend love to do operations on the
array. The operations can be a query or an update.

For each query the Friend say two indices l and r , and their father answers back with the sum
of the elements from indices l to r (both included).

When there is an update, the friend says an index i and a value x , and Pepcoder will add x to
ith index of array (so the new value of arr[i]  is arr[i] + x ).

Because indexing the array from zero is too obscure for children, all indices start from 1.
*/

import java.io.*;
import java.util.stream.*;

public class FenwickTree {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int n = Integer.parseInt(br.readLine());
        FT ft = new FT(Stream.of(br.readLine().split(" ")).mapToInt(num -> Integer.parseInt(num)).toArray(), n);
        int q = Integer.parseInt(br.readLine());
        for (int i = 0; i < q; i++) {
            String[] arr = br.readLine().split(" ");
            int a = Integer.parseInt(arr[1]), b = Integer.parseInt(arr[2]);
            if (arr[0].equals("q"))
                out.println(ft.query(a, b));
            else
                ft.update(a, b);
        }
        out.flush();
    }

    static class FT {
        long[] tree;
        int n;

        FT(int[] arr, int n) {
            this.n = n;
            this.tree = new long[n + 1];
            for (int i = 1; i <= n; i++) {
                update(i, arr[i - 1]);
            }
        }

        void update(int idx, int val) {
            while (idx <= n) {
                tree[idx] += val;
                idx += idx & -idx;
            }
        }

        long query(int l, int r) {
            return getSum(r) - getSum(l - 1);
        }

        long getSum(int idx) {
            long ans = 0L;
            while (idx > 0) {
                ans += tree[idx];
                idx -= idx & -idx;
            }
            return ans;
        }
    }
}
