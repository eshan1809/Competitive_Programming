
/*
Given two integer numbers, your task is to find count of all common divisors of given numbers
*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.HashMap;

public class CountOfCommonDivisors {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        preprocess();

        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            String[] nos = br.readLine().split(" ");
            System.out.println(sieve(Integer.parseInt(nos[0]), Integer.parseInt(nos[1])));
        }
    }

    private static long sieve(int a, int b) {
        Map<Integer, Integer> mapA = new HashMap<>(), mapB = new HashMap<>();

        while (a > 1) {
            mapA.put(primes[a], mapA.getOrDefault(primes[a], 0) + 1);
            a /= primes[a];
        }

        while (b > 1) {
            mapB.put(primes[b], mapB.getOrDefault(primes[b], 0) + 1);
            b /= primes[b];
        }

        long count = 1L;
        for (int k : mapA.keySet()) {
            if (mapB.containsKey(k))
                count *= (Math.min(mapA.get(k), mapB.get(k)) + 1);
        }
        return count;
    }

    static int[] primes;

    private static void preprocess() {
        primes = new int[(int) 1e6 + 1];
        for (int i = 1; i <= 1e6; i++)
            primes[i] = i;

        for (int i = 2; i * i <= 1e6; i++) {
            if (primes[i] == i) {
                for (long j = 1L * i * i; j <= 1e6; j += i) {
                    if (primes[(int) j] == j)
                        primes[(int) j] = i;
                }
            }
        }
    }
}
