/*
you have T numbers and for each number N you want to print all of it's prime factors.
Expected complexity : 0(logn) for each query. You can do linear time preprocessing.
*/

import java.util.*;

public class PrimeFactorsUsingSieve {
    public static void main(String args[]) {
        Scanner scn = new Scanner(System.in);
        preprocess();

        int t = scn.nextInt();
        while (t-- > 0) {
            int n = scn.nextInt();
            List<Integer> list = sieve(n);
            for (int i : list)
                System.out.print(i + " ");
            System.out.println();
        }
        scn.close();
    }

    private static List<Integer> sieve(int n) {
        List<Integer> list = new ArrayList<>();
        while (n > 1) {
            list.add(primes[n]);
            n /= primes[n];
        }
        return list;
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
