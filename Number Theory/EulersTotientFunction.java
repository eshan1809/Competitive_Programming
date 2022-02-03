/*
you have been given a number n, count the number of integers between 1 to n inclusive, which are co-prime to n.
*/

import java.util.Scanner;

public class EulersTotientFunction {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        System.out.println(euler(n));
        scn.close();
    }

    public static int euler(int n) {
        int count = n;
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0)
                count -= count / i;
            while (n % i == 0)
                n /= i;
        }
        if (n > 1)
            count -= count / n;
        return count;
    }
}
