/*
You are given a list of N numbers and Q queries.Each query is specified by two numbers i and j.The answer to
each query is the minimum number between the range between i and j(including i and j).The query are specified using 0 based indexing.

Expected complexity : O(Q * logN)
*/

import java.io.*;
import java.util.stream.*;
import java.util.*;

public class SquareRootDecomposition {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int n = Integer.parseInt(br.readLine());
        SRD srd = new SRD(Stream.of(br.readLine().split(" ")).mapToInt(num -> Integer.parseInt(num)).toArray(), n);
        int q = Integer.parseInt(br.readLine());
        for (int i = 0; i < q; i++) {
            int[] lr = Stream.of(br.readLine().split(" ")).mapToInt(num -> Integer.parseInt(num)).toArray();
            out.println(srd.query(lr[0], lr[1]));
        }
        out.flush();
    }

    private static class SRD {
        int[] arr, root;
        int n, len;

        SRD(int[] arr, int n) {
            this.arr = arr;
            this.n = n;
            this.len = (int) Math.ceil(Math.sqrt(n));
            root = new int[len];
            Arrays.fill(root, Integer.MAX_VALUE);
            build();
        }

        void build() {
            for (int i = 0; i < n; i++)
                root[i / len] = Math.min(root[i / len], arr[i]);
        }

        int query(int l, int r) {
            int ans = Integer.MAX_VALUE;
            while (l <= r) {
                if (l % len == 0) {
                    if (l + len < r) {
                        ans = Math.min(ans, root[l / len]);
                        l += len;
                    } else {
                        ans = Math.min(ans, arr[l++]);
                    }
                } else {
                    ans = Math.min(ans, arr[l++]);
                }
            }
            return ans;
        }
    }
}
