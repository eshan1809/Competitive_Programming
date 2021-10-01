/*
You are given n bulbs, numbered from 1 to n. Initially all the bulbs are turned off.
You have to perform 2 types of operations:-

1. Toggle all bulds numbered between A to B.Here toggle mean turn on bulbs to off and off bulbs to on.This is represented by 1 A B.
2. Count how many bulbs numbered between A to B are on.This is represented by 2 A B.
*/

import java.io.*;

public class ToggleBulbs {

    static class Bulbs {
        int n;
        int[] count;
        boolean[] toggle;

        Bulbs(int n) {
            this.n = n;
            count = new int[4 * n];
            toggle = new boolean[4 * n];
        }

        void toggle(int l, int r) {
            toggle(1, 0, n - 1, l - 1, r - 1);
        }

        void toggle(int id, int start, int end, int l, int r) {
            distribute(id, start, end);
            if (end < l || r < start)
                return;
            else if (start == end) {
                toggle[id] ^= true;
                distribute(id, start, end);
            } else if (l <= start && end <= r) {
                toggle[id] ^= true;
                distribute(id, start, end);
            } else {
                int mid = (start + end) / 2, left = 2 * id, right = left + 1;
                toggle(left, start, mid, l, r);
                toggle(right, mid + 1, end, l, r);
                count[id] = count[left] + count[right];
            }
        }

        int count(int l, int r) {
            return count(1, 0, n - 1, l - 1, r - 1);
        }

        int count(int id, int start, int end, int l, int r) {
            if (end < l || r < start)
                return 0;
            distribute(id, start, end);
            if (start == end)
                return count[id];
            else if (l <= start && end <= r)
                return count[id];
            else {
                int mid = (start + end) / 2;
                return count(2 * id, start, mid, l, r) + count(2 * id + 1, mid + 1, end, l, r);
            }
        }

        void distribute(int id, int start, int end) {
            if (!toggle[id])
                return;
            if (start == end) {
                count[id] ^= 1;
            } else {
                count[id] = (end - start + 1) - count[id];
                toggle[2 * id] ^= true;
                toggle[2 * id + 1] ^= true;
            }
            toggle[id] = false;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));

        String inps[] = read.readLine().split(" ");
        int n = Integer.parseInt(inps[0]);
        int q = Integer.parseInt(inps[1]);

        // write your code here
        StringBuilder out = new StringBuilder();
        Bulbs obj = new Bulbs(n);
        while (q-- > 0) {
            String[] parts = read.readLine().split(" ");
            int option = Integer.parseInt(parts[0]), l = Integer.parseInt(parts[1]), r = Integer.parseInt(parts[2]);
            if (option == 1)
                obj.toggle(l, r);
            else
                out.append(obj.count(l, r) + "\n");
        }
        System.out.println(out);
    }
}
