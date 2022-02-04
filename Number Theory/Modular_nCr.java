/*
You have T test cases and for each test case you have been given n and r values and you need to find nCr mod 10^9+7.
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Modular_nCr {

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(br.readLine()), mod = (int) 1e9 + 7;
        int[][] dp = new int[3001][3001];
        dp[0][0] = 1;
        for (int n = 1; n <= 3000; n++) {
            for (int r = 0; r <= 3000; r++) {
                if (r == 0)
                    dp[n][r] = 1;
                else
                    dp[n][r] = (dp[n - 1][r] + dp[n - 1][r - 1]) % mod;
            }
        }
        while (t-- > 0) {
            String ip = br.readLine();
            int n = Integer.parseInt(ip.split(" ")[0]), r = Integer.parseInt(ip.split(" ")[1]);
            out.println(dp[n][r]);
        }
        out.flush();
    }
}
