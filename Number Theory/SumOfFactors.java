/*
Given a number n, output the summation of all its proper Factors.

Note: A proper Factor of a natural number is the divisor that is strictly less than the number.
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class SumOfFactors {

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        preprocess();

        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            out.println(sieve(n) - n);
        }
        out.flush();
    }

    private static long sieve(int n) {
        Map<Integer, Integer> map = new HashMap<>();

        while (n > 1) {
            map.put(primes[n], map.getOrDefault(primes[n], 0) + 1);
            n /= primes[n];
        }

        long sum = 1L;
        for (int k : map.keySet()) {
            sum *= (Math.pow(k, map.get(k) + 1) - 1) / (k - 1);
        }
        return sum;
    }

    static int[] primes;

    private static void preprocess() {
        primes = new int[(int) 1e6 + 1];
        for (int i = 1; i <= 1e6; i++)
            primes[i] = i;

        for (int i = 2; i <= 1e6; i++) {
            if (primes[i] == i) {
                for (long j = 1L * i * i; j <= 1e6; j += i) {
                    if (primes[(int) j] == j)
                        primes[(int) j] = i;
                }
            }
        }
    }
}
