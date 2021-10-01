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
        int n;
        int[] arr, sum, tree;
        long[] squareSum;

        SegmentTree(int arr[]) {
            n = arr.length;
            this.arr = arr;
            sum = new int[4 * n];
            tree = new int[4 * n];
            squareSum = new long[4 * n];
            build(1, 0, n - 1);
        }

        void build(int id, int start, int end) {
            if (start == end) {
                sum[id] = arr[start];
                squareSum[id] = (long) arr[start] * arr[start];
            } else {
                int mid = (start + end) / 2, left = 2 * id, right = left + 1;
                build(left, start, mid);
                build(right, mid + 1, end);
                sum[id] = sum[left] + sum[right];
                squareSum[id] = squareSum[left] + squareSum[right];
            }
        }

        void update(int l, int r, int val) {
            update(1, 0, n - 1, l, r, val);
        }

        void update(int id, int start, int end, int l, int r, int val) {
            distribute(id, start, end);
            if (end < l || r < start)
                return;
            else if (start == end) {
                tree[id] += val;
                distribute(id, start, end);
            } else if (l <= start && end <= r) {
                tree[id] += val;
                distribute(id, start, end);
            } else {
                int mid = (start + end) / 2, left = 2 * id, right = left + 1;
                update(left, start, mid, l, r, val);
                update(right, mid + 1, end, l, r, val);
                sum[id] = sum[left] + sum[right];
                squareSum[id] = squareSum[left] + squareSum[right];
            }
        }

        long query(int l, int r) {
            return query(1, 0, n - 1, l, r);
        }

        long query(int id, int start, int end, int l, int r) {
            if (end < l || r < start)
                return 0L;
            distribute(id, start, end);
            if (start == end) {
                return squareSum[id];
            } else if (l <= start && end <= r) {
                return squareSum[id];
            } else {
                int mid = (start + end) / 2, left = 2 * id, right = left + 1;
                return query(left, start, mid, l, r) + query(right, mid + 1, end, l, r);
            }
        }

        void distribute(int id, int start, int end) {
            if (start == end) {
                sum[id] += tree[id];
                squareSum[id] = 1L * sum[id] * sum[id];
            } else {
                squareSum[id] += (long) (end - start + 1) * tree[id] * tree[id] + 2L * tree[id] * sum[id];
                sum[id] += (end - start + 1) * tree[id];
                tree[2 * id] += tree[id];
                tree[2 * id + 1] += tree[id];
            }
            tree[id] = 0;
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