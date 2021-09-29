/*
You are given an array(of integers) of length n.
You are required to answer q queries.

Queries can be of two types
0. 0 l r : In this you have to return sum of squares of all elements int range [l, r].
1. 1 l r val: In this query u have to increase all elements in this interval [l, r] by val.

To do the above task u have to create a datastructure as follows :-

Implement the SegmentTree class:
1. SegmentTree(int arr[]): Initializes the SegmentTree object with an array,
2. void update(int l, int r, int val): increase all elements in this interval [l, r] by val,
3. long query(int l, int r): return sum of squares of all elements arr[i] for which i is in range [l, r].
*/

import java.io.*;

public class SumOfSquares {

    public static class SegmentTree {

        SegmentTree(int arr[]) {

        }

        void update(int l, int r, int val) {

        }

        long query(int l, int r) {
            return 0L;
        }

    }

    public static void main(String[] args) throws Exception {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(read.readLine());
        int arr[] = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(read.readLine());
        }

        SegmentTree obj = new SegmentTree(arr);

        int q = Integer.parseInt(read.readLine());

        StringBuilder out = new StringBuilder();
        while (q-- > 0) {
            String[] inp = read.readLine().split(" ");

            int t = Integer.parseInt(inp[0]);
            int l = Integer.parseInt(inp[1]);
            int r = Integer.parseInt(inp[2]);

            if (t == 0) {
                long ans = obj.query(l, r);
                out.append(ans + "\n");
            } else {
                int val = Integer.parseInt(inp[3]);
                obj.update(l, r, val);
            }
        }

        System.out.println(out);
    }

}