/*
you have been given number n and p, you need to find n! % p (n factorial mod p). p is a prime no.
*/

import java.util.Scanner;

public class WilsonsTheorem {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int p = scn.nextInt();
        if (n >= p)
            System.out.println(0);
        else {
            long ans = p - 1;
            for (int i = p - 1; i > n; i--)
                ans = (ans * mod_pow(i, p - 2, p) % p) % p;
            System.out.println(ans);
        }
        scn.close();
    }

    private static int mod_pow(int n, int p, int mod) {
        if (p == 0)
            return 1;
        int ans = mod_pow(n, p / 2, mod);
        ans = (int) ((((1L * ans) % mod) * (ans % mod)) % mod);
        if ((p & 1) == 1)
            ans = (int) ((((1L * ans) % mod) * (n % mod)) % mod);
        return ans;
    }
}
