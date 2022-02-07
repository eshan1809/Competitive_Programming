/*
you have T numbers and for each number N you want to print all of it's prime factors.
Expected complexity : 0(logn) for each query. You can do linear time preprocessing.
*/

import java.util.*;

public class PrimeFactorsUsingSieve {
    public static void main(String args[]) {
        Scanner scn = new Scanner(System.in);

        boolean[] primes = new boolean[(int) 1e5 + 1];
        Arrays.fill(primes, true);
        for (int i = 2; i <= 1e5; i++) {
            if (primes[i]) {
                for (long j = 1L * i * i; j <= 1e5; j += i)
                    primes[(int) j] = false;
            }
        }

        int t = scn.nextInt();
        while (t-- > 0) {
            int n = scn.nextInt();
            List<Integer> list = new ArrayList<>();

            for (int i = 2; i <= n; i++) {
                if (primes[i] && n % i == 0) {
                    while (n % i == 0) {
                        list.add(i);
                        n /= i;
                    }
                }
            }

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < list.size(); i++)
                sb.append(list.get(i) + " ");
            System.out.println(sb);
        }
        scn.close();
    }
}
