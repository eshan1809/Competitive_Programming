
/*
You are given an array(of integers) of length n.
You are required to answer q queries.

Queries can be of two types
0. 0 ind : In this you have to tell the value of arr[ind].
1. 1 l r val: In this query u have to increase all elements in this interval [l, r] by val.

To do the above task u have to create a datastructure as follows :-

Implement the SegmentTree class:
1. SegmentTree(int arr[]): Initializes the SegmentTree object with an array,
2. void update(int l, int r, int val): increase all elements in this interval [l, r] by val,
3. int query(int ind): return arr[ind].
*/
import java.io.*;

public class WhatIsAtIdx_PointQueryRangeUpdate {

    public static class SegmentTree {
        int n;
        int[] arr, tree;

        SegmentTree(int arr[]) {
            n = arr.length;
            this.arr = arr;
            tree = new int[4 * n];
            build(1, 0, n - 1);
        }

        void build(int id, int start, int end) {
            if (start == end)
                tree[id] = arr[start];
            else {
                int mid = (start + end) / 2;
                build(2 * id, start, mid);
                build(2 * id + 1, mid + 1, end);
            }
        }

        void update(int l, int r, int val) {
            update(1, 0, n - 1, l, r, val);
        }

        void update(int id, int start, int end, int l, int r, int val) {
            if (end < l || r < start)
                return;
            else if (start == end)
                tree[id] += val;
            else if (l <= start && end <= r)
                tree[id] += val;
            else {
                int mid = (start + end) / 2;
                update(2 * id, start, mid, l, r, val);
                update(2 * id + 1, mid + 1, end, l, r, val);
            }
        }

        int query(int ind) {
            return query(1, 0, n - 1, ind);
        }

        int query(int id, int start, int end, int ind) {
            if (start == end)
                return tree[id];
            else {
                int mid = (start + end) / 2;
                if (ind <= mid)
                    return tree[id] + query(2 * id, start, mid, ind);
                else
                    return tree[id] + query(2 * id + 1, mid + 1, end, ind);
            }
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

            if (t == 0) {
                int ind = Integer.parseInt(inp[1]);
                long ans = obj.query(ind);
                out.append(ans + "\n");
            } else {
                int l = Integer.parseInt(inp[1]);
                int r = Integer.parseInt(inp[2]);
                int val = Integer.parseInt(inp[3]);
                obj.update(l, r, val);
            }
        }

        System.out.println(out);
    }

}