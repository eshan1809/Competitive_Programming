/*
You are given three integers a,b and c.

Find two positive integers x and y (x>0 & y>0) such that:

1.the decimal representation of x without leading zeroes consists of "a" digits;
2.the decimal representation of y without leading zeroes consists of "b" digits;
3.the decimal representation of gcd(x,y) without leading zeroes consists of c digits.
gcd(x,y) denotes the greatest common divisor (GCD) of integers x and y.

Output x and y.
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.stream.*;

public class GCDLength {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            int[] arr = Stream.of(br.readLine().split(" ")).mapToInt(a -> Integer.parseInt(a)).toArray();
            int c = 1;
            while ((c + "").length() < arr[2])
                c *= 10;
            int a = c, b = c;
            while ((a + "").length() < arr[0]) {
                a *= 2;
            }
            while ((b + "").length() < arr[1]) {
                b *= 3;
            }
            out.println(a + " " + b);
        }

        out.flush();
    }
}
