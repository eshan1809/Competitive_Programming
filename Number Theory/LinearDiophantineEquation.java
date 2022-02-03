/*
You have been given three Integers a, b and k. you need to find an integral solution of x and y such that a*x + b*y= k * gcd(a,b). 

It can be proven that solution always exist.
*/

import java.util.Scanner;

public class LinearDiophantineEquation {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int a = scn.nextInt(), b = scn.nextInt(), k = scn.nextInt();
        int[] ans = solve(a, b);
        System.out.println(1L * k * ans[0] + " " + 1L * k * ans[1]);
        scn.close();
    }

    private static int[] solve(int a, int b) {
        if (b == 0)
            return new int[] { 1, 0 };
        int[] ans = solve(b, a % b);
        return new int[] { ans[1], ans[0] - (a / b) * ans[1] };
    }
}
