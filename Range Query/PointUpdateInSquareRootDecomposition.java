/*
You are given a list of N numbers and Q queries. There are two types of queries:

1. f l r : In a line, the first character would be f, and 2 index l and r, you have to find the sum of numbers between l and r.
2. u i d : In a line, the first character would be u, and we have to change the value at index i in the original array by d.
*/

import java.io.*;
import java.util.stream.*;

public class PointUpdateInSquareRootDecomposition {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int n = Integer.parseInt(br.readLine());
        SRD srd = new SRD(Stream.of(br.readLine().split(" ")).mapToInt(num -> Integer.parseInt(num)).toArray(), n);
        int q = Integer.parseInt(br.readLine());
        for (int i = 0; i < q; i++) {
            String[] arr = br.readLine().split(" ");
            int a = Integer.parseInt(arr[1]), b = Integer.parseInt(arr[2]);
            if (arr[0].equals("f"))
                out.println(srd.query(a, b));
            else
                srd.update(a, b);
        }
        out.flush();
    }

    private static class SRD {
        int[] arr;
        long[] root;
        int n, len;

        SRD(int[] arr, int n) {
            this.arr = arr;
            this.n = n;
            this.len = (int) Math.ceil(Math.sqrt(n));
            root = new long[len];
            build();
        }

        void build() {
            for (int i = 0; i < n; i++)
                root[i / len] += arr[i];
        }

        long query(int l, int r) {
            long ans = 0L;
            while (l <= r) {
                if (l % len == 0 && l + len < r) {
                    ans += root[l / len];
                    l += len;
                } else {
                    ans += arr[l++];
                }
            }
            return ans;
        }

        void update(int idx, int val) {
            root[idx / len] += val;
            arr[idx] += val;
        }
    }
}
