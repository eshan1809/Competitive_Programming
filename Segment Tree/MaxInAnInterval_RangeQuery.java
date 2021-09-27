/*
You are given an array(of integers) of length n.
You are required to answer q queries.
In each query u are given an interval l, r both inclusive and u have to find the maximum element in this interval.

To do the above task u have to create a datastructure as follows :-

Implement the SegmentTree class:
1. SegmentTree(int arr[]): Initializes the SegmentTree object with an array,
2. int query(int l, int r): return max in interval [l, r].

Can u do it in O(log(n)) or better Time Complexity.
*/

import java.io.*;

public class MaxInAnInterval_RangeQuery {

    public static class SegmentTree {
        int[] arr, tree;

        SegmentTree(int arr[]) {
            this.arr = arr;
            tree = new int[4 * arr.length];
            build(1, 0, arr.length - 1);
        }

        void build(int id, int start, int end) {
            if (start == end)
                tree[id] = arr[start];
            else {
                int mid = (start + end) / 2, left = 2 * id, right = 2 * id + 1;
                build(left, start, mid);
                build(right, mid + 1, end);
                tree[id] = Math.max(tree[left], tree[right]);
            }
        }

        int query(int l, int r) {
            return find(1, 0, arr.length - 1, l, r);
        }

        int find(int id, int start, int end, int l, int r) {
            if (r < start || end < l)
                return Integer.MIN_VALUE;
            else if (start == end)
                return tree[id];
            else if (l <= start && end <= r)
                return tree[id];
            else {
                int mid = (start + end) / 2;
                int left = find(2 * id, start, mid, l, r);
                int right = find(2 * id + 1, mid + 1, end, l, r);
                return Math.max(left, right);
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

            int l = Integer.parseInt(inp[0]);
            int r = Integer.parseInt(inp[1]);

            int ans = obj.query(l, r);
            out.append(ans);
            out.append("\n");
        }

        System.out.println(out);
    }

}