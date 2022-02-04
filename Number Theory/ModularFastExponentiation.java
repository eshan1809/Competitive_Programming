/*
1. You are given a number x.
2. You are given another number n.
3. You are required to calculate x raised to the power n mod 10^9 + 7 in logn complexity.
*/

import java.io.IOException;
import java.util.Scanner;

public class ModularFastExponentiation {
    public static int xpown(long x, long n, long p) {
        if (n == 0)
            return 1;
        int ans = xpown(x, n / 2, p);
        ans %= p;
        ans = (int) ((1L * ans * ans) % p);
        if ((n & 1) == 1)
            ans = (int) ((x * ans) % p);
        return ans;
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        Scanner scn = new Scanner(System.in);

        int x = scn.nextInt();
        int n = scn.nextInt();

        int ans = xpown(x, n, 1000000007);
        System.out.println(ans);
        scn.close();
    }
}