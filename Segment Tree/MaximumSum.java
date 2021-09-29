/*
You are given an array(of integers) of length n.
You are required to answer q queries.

Queries can be of two types :-
Update
0 idx val : set arr[idx] to val.
Query
1 l r: find i,j such that l <= i < j <= r, such that arr[i]+arr[j] is maximized. return arr[i]+arr[j].
*/

import java.io.*;

public class MaximumSum {

    public static class SegmentTree {
        int n;
        int[] arr, sum, max;

        SegmentTree(int[] arr) {
            n = arr.length;
            this.arr = arr;
            sum = new int[4 * n];
            max = new int[4 * n];
            build(1, 0, n - 1);
        }

        private void build(int id, int start, int end) {
            if (start == end)
                sum[id] = max[id] = arr[start];
            else {
                int mid = (start + end) / 2, left = 2 * id, right = left + 1;
                build(left, start, mid);
                build(right, mid + 1, end);
                sum[id] = Math.max(Math.max(sum[left], sum[right]), max[left] + max[right]);
                max[id] = Math.max(max[left], max[right]);
            }
        }

        int query(int l, int r) {
            return query(1, 0, n - 1, l, r)[0];

        }

        private int[] query(int id, int start, int end, int l, int r) {
            if (end < l || r < start)
                return new int[2];
            else if (start == end)
                return new int[] { sum[id], max[id] };
            else if (l <= start && end <= r)
                return new int[] { sum[id], max[id] };
            else {
                int mid = (start + end) / 2;
                int[] a1 = query(2 * id, start, mid, l, r), a2 = query(2 * id + 1, mid + 1, end, l, r);
                int s = Math.max(Math.max(a1[0], a2[0]), a1[1] + a2[1]), m = Math.max(a1[1], a2[1]);
                return new int[] { s, m };
            }
        }

        void update(int idx, int val) {
            update(1, 0, n - 1, idx, val);
        }

        private void update(int id, int start, int end, int idx, int val) {
            if (start == end)
                sum[id] = max[id] = arr[idx] = val;
            else {
                int mid = (start + end) / 2, left = 2 * id, right = left + 1;
                if (idx <= mid)
                    update(left, start, mid, idx, val);
                else
                    update(right, mid + 1, end, idx, val);
                sum[id] = Math.max(Math.max(sum[left], sum[right]), max[left] + max[right]);
                max[id] = Math.max(max[left], max[right]);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(read.readLine());
        int[] arr = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = Integer.parseInt(read.readLine());

        SegmentTree obj = new SegmentTree(arr);
        StringBuilder out = new StringBuilder();
        int q = Integer.parseInt(read.readLine());
        while (q-- > 0) {
            String[] parts = read.readLine().split(" ");
            if (parts[0].equals("0"))
                obj.update(Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
            else
                out.append(obj.query(Integer.parseInt(parts[1]), Integer.parseInt(parts[2])) + "\n");
        }
        System.out.println(out);
    }

}