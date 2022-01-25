/*
Mr. Pepcoder has a 2D Array, and his Friend love to do operations on thearray. The operations can be a query or an update.

For each query the Friend say four indices x1, y1, x2 and y2, and their father answers back with the sum of the elements of the rectangle whose top left index is x1, y1 and bottom right is x2, y2.

When there is an update, the friend says index (x1,y1) and a value x , and Pepcoder will add x to arr[x1][y1] (so the new value of arr[x1][y1]  is arr[x1][y1] + x ).

Because indexing the array from zero is too obscure for friend, all indices start from 1.
*/

import java.io.*;
import java.util.stream.*;

public class BinaryIndexedTree2D {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int[] nm = Stream.of(br.readLine().split(" ")).mapToInt(num -> Integer.parseInt(num)).toArray();
        int[][] mat = new int[nm[0]][nm[1]];
        for (int i = 0; i < nm[0]; i++)
            mat[i] = Stream.of(br.readLine().split(" ")).mapToInt(num -> Integer.parseInt(num)).toArray();
        BIT bit = new BIT(mat, nm[0], nm[1]);
        int q = Integer.parseInt(br.readLine());
        while (q-- > 0) {
            String[] arr = br.readLine().split(" ");
            int a = Integer.parseInt(arr[1]), b = Integer.parseInt(arr[2]), c = Integer.parseInt(arr[3]);
            if (arr[0].equals("q"))
                out.println(bit.query(a, b, c, Integer.parseInt(arr[4])));
            else
                bit.update(a, b, c);
        }
        out.flush();
    }

    static class BIT {
        long[][] tree;
        int n, m;

        BIT(int[][] mat, int n, int m) {
            this.n = n;
            this.m = m;
            tree = new long[n + 1][m + 1];
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= m; j++) {
                    update(i, j, mat[i - 1][j - 1]);
                }
            }
        }

        void update(int x, int y, int val) {
            while (x <= n) {
                int t = y;
                while (t <= m) {
                    tree[x][t] += val;
                    t += t & -t;
                }
                x += x & -x;
            }
        }

        long query(int x1, int y1, int x2, int y2) {
            return getSum(x2, y2) - getSum(x1 - 1, y2) - getSum(x2, y1 - 1) + getSum(x1 - 1, y1 - 1);
        }

        long getSum(int x, int y) {
            long ans = 0L;
            while (x > 0) {
                int t = y;
                while (t > 0) {
                    ans += tree[x][t];
                    t -= t & -t;
                }
                x -= x & -x;
            }
            return ans;
        }
    }
}
