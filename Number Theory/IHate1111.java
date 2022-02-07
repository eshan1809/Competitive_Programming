/*
You are given an integer x. Can you make x by summing up some number of 11,111,1111,11111,..?(You can use any number among them any number of times)
*/

import java.util.*;

public class IHate1111 {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int t = scn.nextInt();
        while (t-- > 0) {
            int n = scn.nextInt();
            if ((n - 111 * (n % 11)) > 0 && (n - 111 * (n % 11)) % 11 == 0)
                System.out.println("YES");
            else
                System.out.println("NO");
        }
        scn.close();
    }
}
