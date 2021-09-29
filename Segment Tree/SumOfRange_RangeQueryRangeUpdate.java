/*
You are given an array(of integers) of length n.
You are required to answer q queries.

Queries can be of two types
0. 0 l r : In this you have to return sum of all elements arr[i] for i in l<=i<=r.
1. 1 l r val: In this query u have to increase all elements in this interval [l, r] by val.

To do the above task u have to create a datastructure as follows :-

Implement the SegmentTree class:
1. SegmentTree(int arr[]): Initializes the SegmentTree object with an array,
2. void update(int l, int r, int val): increase all elements in this interval [l, r] by val,
3. int query(int l, int r): return sum of all elements arr[i] for which i is in range [l, r].
*/

import java.io.*;

public class SumOfRange_RangeQueryRangeUpdate {
    public static class SegmentTree {
        int n;
        int[] arr, tree, lazy;

        SegmentTree(int arr[]) {
            n = arr.length;
            this.arr = arr;
            tree = new int[4 * n];
            lazy = new int[4 * n];
            build(1, 0, n - 1);
        }

        private void build(int id, int start, int end) {
            if (start == end)
                tree[id] = arr[start];
            else {
                int mid = (start + end) / 2, left = 2 * id, right = left + 1;
                build(left, start, mid);
                build(right, mid + 1, end);
                tree[id] = tree[left] + tree[right];
            }
        }

        void update(int l, int r, int val) {
            update(1, 0, n - 1, l, r, val);
        }

        private void update(int id, int start, int end, int l, int r, int val) {
            distribute(id, start, end);
            if (end < l || r < start)
                return;
            else if (start == end) {
                tree[id] += val;
            } else if (l <= start && end <= r) {
                lazy[id] += val;
                distribute(id, start, end);
            } else {
                int mid = (start + end) / 2, left = 2 * id, right = left + 1;
                update(left, start, mid, l, r, val);
                update(right, mid + 1, end, l, r, val);
                tree[id] = tree[left] + tree[right];
            }
        }

        int query(int l, int r) {
            return query(1, 0, n - 1, l, r);
        }

        private int query(int id, int start, int end, int l, int r) {
            if (end < l || r < start)
                return 0;
            distribute(id, start, end);
            if (start == end)
                return tree[id];
            else if (l <= start && end <= r) {
                return tree[id];
            } else {
                int mid = (start + end) / 2;
                return query(2 * id, start, mid, l, r) + query(2 * id + 1, mid + 1, end, l, r);
            }
        }

        private void distribute(int id, int start, int end) {
            if (start == end)
                tree[id] += lazy[id];
            else {
                tree[id] += lazy[id] * (end - start + 1);
                lazy[2 * id] += lazy[id];
                lazy[2 * id + 1] += lazy[id];
            }
            lazy[id] = 0;
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
