/*
You have an array with n elements. you have to create all subsequences of this array with length K. For each subsequence, you have to write down the product of k-2 integers, all elements of this sequence except the minimum and maximum element.
your work will be considered done if you will be able to write down all these numbers and tell PepBoss their product mod 10^9+7.
*/

import java.util.Arrays;
import java.util.Scanner;

public class NoMaxNoMin {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);

        int n = scn.nextInt();
        int k = scn.nextInt();

        long[] arr = new long[n];
        for (int z = 0; z < n; z++) {
            arr[z] = scn.nextLong();
        }

        Arrays.sort(arr);
        int mod = (int) (1e9 + 7), mod2 = (int) (1e9 + 6);

        int[][] dp = new int[n + 1][k + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= k && j <= i; j++) {
                if (j == 0)
                    dp[i][j] = 1;
                else
                    dp[i][j] = (dp[i - 1][j] + dp[i - 1][j - 1]) % mod2;
            }
        }
        long[] count = new long[n];

        int total = dp[n - 1][k - 1];
        for (int i = 1; i <= n / 2; i++) {
            long min = 0, max = 0;
            if (i >= k - 1)
                max = dp[i][k - 1];
            if (n - 1 - i >= k - 1)
                min = dp[n - i - 1][k - 1];
            count[n - i - 1] = count[i] = ((total - min + mod2) % mod2 - max + mod2) % mod2;
        }

        long ans = 1L;
        for (int i = 1; i < n - 1; i++)
            ans = (ans * mod_pow(arr[i], count[i], mod)) % mod;
        System.out.println(ans % mod);
        scn.close();
    }

    public static int mod_pow(long x, long n, long p) {
        if (n == 0)
            return 1;
        int ans = mod_pow(x, n / 2, p);
        ans %= p;
        ans = (int) ((1L * ans * ans) % p);
        if ((n & 1) == 1)
            ans = (int) ((x * ans) % p);
        return ans;
    }
}
