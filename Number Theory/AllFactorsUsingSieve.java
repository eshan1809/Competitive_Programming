/*
Given t test cases and in each test case you have a positive integer n, we have to find the total number of divisors for n.
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class AllFactorsUsingSieve {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        preprocess();

        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            System.out.println(sieve(n));
        }
    }

    private static long sieve(int n) {
        Map<Integer, Integer> map = new HashMap<>();

        while (n > 1) {
            map.put(primes[n], map.getOrDefault(primes[n], 0) + 1);
            n /= primes[n];
        }

        long count = 1L;
        for (int k : map.keySet())
            count *= (map.get(k) + 1);

        return count;
    }

    static int[] primes;

    private static void preprocess() {
        primes = new int[(int) 1e5 + 1];
        for (int i = 1; i <= 1e5; i++)
            primes[i] = i;

        for (int i = 2; i <= 1e5; i++) {
            if (primes[i] == i) {
                for (long j = 1L * i * i; j <= 1e5; j += i) {
                    if (primes[(int) j] == j)
                        primes[(int) j] = i;
                }
            }
        }
    }
}
