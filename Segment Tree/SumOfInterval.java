/*
You are given an array(of integers) of length n.
You are required to answer q queries.

Queries can be of two types
0. 0 pos val : In this you have to update arr[pos] to val.
1. 1 l r: In this query u have to find the sum of all elements in this interval.

Sum of elements in interval [l, r] means sum of all arr[i] for which i is in range [l, r].

To do the above task u have to create a datastructure as follows :-

Implement the SegmentTree class:
1. SegmentTree(int arr[]): Initializes the SegmentTree object with an array,
2. void update(int pos, int val): updates the arr[pos] to val,
3. int query(int l, int r): return sum of all element's in interval [l, r].
*/

import java.io.*;

public class SumOfInterval {

    public static class SegmentTree {
        int[] arr, tree;
        int n;

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
                int mid = (start + end) / 2, left = 2 * id, right = left + 1;
                build(left, start, mid);
                build(right, mid + 1, end);
                tree[id] = tree[left] + tree[right];
            }
        }

        void update(int pos, int val) {
            update(1, 0, n - 1, pos, val);
        }

        void update(int id, int start, int end, int pos, int val) {
            if (start == end) {
                arr[pos] = val;
                tree[id] = val;
            } else {
                int mid = (start + end) / 2, left = 2 * id, right = left + 1;
                if (pos <= mid)
                    update(left, start, mid, pos, val);
                else
                    update(right, mid + 1, end, pos, val);
                tree[id] = tree[left] + tree[right];
            }
        }

        int query(int l, int r) {
            return query(1, 0, n - 1, l, r);
        }

        int query(int id, int start, int end, int l, int r) {
            if (end < l || r < start)
                return 0;
            else if (start == end)
                return tree[id];
            else if (l <= start && end <= r)
                return tree[id];
            else {
                int mid = (start + end) / 2;
                int left = query(2 * id, start, mid, l, r);
                int right = query(2 * id + 1, mid + 1, end, l, r);
                return left + right;
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
            int l = Integer.parseInt(inp[1]);
            int r = Integer.parseInt(inp[2]);

            if (t == 0) {
                obj.update(l, r);
            } else {
                int ans = obj.query(l, r);
                out.append(ans);
                out.append("\n");
            }
        }

        System.out.println(out);
    }

}