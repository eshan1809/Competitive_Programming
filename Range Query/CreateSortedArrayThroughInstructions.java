/*
1.Given an integer array instructions.
2.you are asked to create a sorted array from the elements in instructions. You start with another empty array nums. For each element from left to right in instructions, insert it into nums.
3. The cost of each insertion is the minimum of the following:
     a)The number of elements currently in nums that are strictly less than instructions[i].
     b)The number of elements currently in nums that are strictly greater than instructions[i].

4.For example, if inserting element 4 into nums = [2,3,4,5], the cost of insertion is min(2, 1) (elements 2 and 3 are less than 4, element 5 is greater than 4) and nums will become [2,3,4,4,5].
5.Return the total cost to insert all elements from instructions into nums.
*/

import java.io.*;
import java.util.stream.*;

public class CreateSortedArrayThroughInstructions {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int n = Integer.parseInt(br.readLine()), ans = 0;
        FT ft = new FT(n);
        int[] arr = Stream.of(br.readLine().split(" ")).mapToInt(num -> Integer.parseInt(num)).toArray();
        for (int i = 0; i < n; i++) {
            ans += Math.min(ft.find(arr[i] - 1), i - ft.find(arr[i]));
            ft.update(arr[i]);
        }
        out.println(ans);
        out.flush();
    }

    static class FT {
        int n;
        int[] tree;

        FT(int n) {
            this.n = n;
            tree = new int[n + 1];
        }

        void update(int val) {
            while (val <= n) {
                tree[val]++;
                val += val & -val;
            }
        }

        int find(int val) {
            int ans = 0;
            while (val > 0) {
                ans += tree[val];
                val -= val & -val;
            }
            return ans;
        }
    }
}
