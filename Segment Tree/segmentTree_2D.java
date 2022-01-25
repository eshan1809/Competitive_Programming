import java.io.*;
import java.util.*;
import java.util.stream.*;

public class segmentTree_2D {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);

    static class SegmentTree {
        int n, m;
        int[][] arr;
        long[][] tree;

        SegmentTree(int[][] arr) {
            this.arr = arr;
            n = arr.length - 1;
            m = arr[0].length - 1;
            tree = new long[n << 2][m << 2];
            build(1, 1, n);
        }

        void build(int idx, int startx, int endx) {
            if (startx == endx) {
                build(idx, startx, endx, 1, 1, m);
            } else {
                int mid = (startx + endx) / 2;
                build(idx << 1, startx, mid);
                build((idx << 1) + 1, mid + 1, endx);
                build(idx, startx, endx, 1, 1, m);
            }
        }

        void build(int idx, int startx, int endx, int idy, int starty, int endy) {
            if (starty == endy) {
                if (startx == endx)
                    tree[idx][idy] = arr[startx][starty];
                else
                    tree[idx][idy] = tree[idx << 1][idy] + tree[(idx << 1) + 1][idy];
            } else {
                int mid = (starty + endy) / 2, left = idy << 1, right = left + 1;
                build(idx, startx, endx, left, starty, mid);
                build(idx, startx, endx, right, mid + 1, endy);
                tree[idx][idy] = tree[idx][left] + tree[idx][right];
            }
        }

        long query(int x1, int y1, int x2, int y2) {
            return query(1, 1, n, x1, x2, y1, y2);
        }

        long query(int idx, int startx, int endx, int x1, int x2, int y1, int y2) {
            if (endx < x1 || x2 < startx)
                return 0L;
            if (startx == endx) {
                return query(idx, 1, 1, m, y1, y2);
            } else if (x1 <= startx && endx <= x2) {
                return query(idx, 1, 1, m, y1, y2);
            } else {
                int mid = (startx + endx) / 2;
                long left = query((idx << 1), startx, mid, x1, x2, y1, y2);
                long right = query((idx << 1) + 1, mid + 1, endx, x1, x2, y1, y2);
                return left + right;
            }
        }

        long query(int idx, int idy, int starty, int endy, int y1, int y2) {
            if (endy < y1 || y2 < starty)
                return 0L;
            if (starty == endy) {
                return tree[idx][idy];
            } else if (y1 <= starty && endy <= y2) {
                return tree[idx][idy];
            } else {
                int mid = (starty + endy) / 2;
                long left = query(idx, (idy << 1), starty, mid, y1, y2);
                long right = query(idx, (idy << 1) + 1, mid + 1, endy, y1, y2);
                return left + right;
            }
        }

        void update(int x, int y, int val) {
            update(1, 1, n, x, y, val);
        }

        void update(int idx, int startx, int endx, int x, int y, int val) {
            if (startx == endx) {
                update(idx, startx, endx, 1, 1, m, x, y, val);
            } else {
                int mid = (startx + endx) / 2, left = idx << 1, right = left + 1;
                if (startx <= x && x <= endx)
                    update(left, startx, mid, x, y, val);
                else
                    update(right, mid + 1, endx, x, y, val);
                update(idx, startx, endx, 1, 1, m, x, y, val);
            }
        }

        void update(int idx, int startx, int endx, int idy, int starty, int endy, int x, int y, int val) {
            if (starty == endy) {
                if (startx == endx)
                    tree[idx][idy] += val;
                else
                    tree[idx][idy] = tree[idx << 1][idy] + tree[(idx << 1) + 1][idy];
            } else {
                int mid = (starty + endy) / 2, left = idy << 1, right = left + 1;
                if (starty <= y && y <= endy)
                    update(idx, startx, endx, left, starty, mid, x, y, val);
                else
                    update(idx, startx, endx, right, mid + 1, endy, x, y, val);
                tree[idx][idy] = tree[idx][left] + tree[idx][right];
            }
        }
    }

    public static void main(String[] args) throws IOException {
        int[] nm = Stream.of(br.readLine().split(" ")).mapToInt(num -> Integer.parseInt(num)).toArray();
        int n = nm[0], m = nm[1];
        int[][] arr = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            int[] a = Stream.of(br.readLine().split(" ")).mapToInt(num -> Integer.parseInt(num)).toArray();
            for (int j = 1; j <= m; j++)
                arr[i][j] = a[j - 1];
        }
        SegmentTree smt = new SegmentTree(arr);
        int q = Integer.parseInt(br.readLine());
        while (q-- > 0) {
            String[] ip = br.readLine().split(" ");
            if (ip[0].equals("q"))
                out.println(smt.query(Integer.parseInt(ip[1]), Integer.parseInt(ip[2]), Integer.parseInt(ip[3]),
                        Integer.parseInt(ip[4])));
            else
                smt.update(Integer.parseInt(ip[1]), Integer.parseInt(ip[2]), Integer.parseInt(ip[3]));
        }
        out.flush();
    }
}
