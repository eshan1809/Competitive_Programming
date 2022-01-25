/*
Recently Moksh(HR of Pepcoding) invented a serum which can increase height of plant by 1 unit. Moksh's girlfriend birthday is next month.
He is planning something amazing as a gift for her.His girlfriend's favourite Integer is M.

Initially Moksh has N (1 to N) no. of beautiful Rose plants, Each plant can have an Integer height,Initially the height of each plant is zero.
Subhesh gives Moksh Q number of operations,In each operation Moksh got two integer start and end and he increases the height of all the plants between start and end position (including start and end) by 1.
As Moksh wasn't happy with the pattern of height of the plants formed, He went to Rajneesh and asked him to remove any 1 operation from the Q operations such that the count of plants with height M is maximum possible.
Output the maximum possible number of plants with height M.

Expected Complexity : O(n+q)
*/

import java.io.*;
import java.util.stream.*;

public class MokshAndHisGirlfriend {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int[] nqm = Stream.of(br.readLine().split(" ")).mapToInt(num -> Integer.parseInt(num)).toArray();
        int n = nqm[0], q = nqm[1], m = nqm[2];
        int[][] query = new int[q][2];
        for (int i = 0; i < q; i++)
            query[i] = Stream.of(br.readLine().split(" ")).mapToInt(num -> Integer.parseInt(num)).toArray();
        int[] arr = new int[n], same = new int[n], plusone = new int[n];
        for (int i = 0; i < q; i++) {
            arr[query[i][0] - 1]++;
            if (query[i][1] < n)
                arr[query[i][1]]--;
        }
        for (int i = 1; i < n; i++)
            arr[i] += arr[i - 1];
        if (arr[0] == m)
            same[0] = 1;
        if (arr[0] == m + 1)
            plusone[0] = 1;
        for (int i = 1; i < n; i++)
            same[i] = same[i - 1] + (arr[i] == m ? 1 : 0);
        for (int i = 1; i < n; i++)
            plusone[i] = plusone[i - 1] + (arr[i] == m + 1 ? 1 : 0);
        int max = 0;
        for (int i = 0; i < q; i++) {
            int ans = same[n - 1] + plusone[query[i][1] - 1] - (query[i][0] > 1 ? plusone[query[i][0] - 2] : 0)
                    - (same[query[i][1] - 1] - (query[i][0] > 1 ? same[query[i][0] - 2] : 0));
            max = Math.max(max, ans);
        }
        out.println(max);
        out.flush();
    }
}
