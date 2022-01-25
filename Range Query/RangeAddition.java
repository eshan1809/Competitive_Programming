/*
1. You are given a number N.
2. Assume you have an array of length N initialised with all 0's.
3. You are also given K update operations.
4. Each operation contain 3 numbers: startIndex, endIndex and inc and updates each element of the subarray arr[startIndex, endIndex](both inclusive) with inc.
5. You have to find the resultant array when all K operations are executed.
6. display is a utility function, feel free to use it for debugging purposes.
7. main takes input from the users.
8. This is a functional problem. 
9. You have to complete the getModifiedArray function. It takes as input a length and a 2D array of update operations. It should return the modified array.
10. Don't change the code of main and display.

Expected Complexity : O(n+k)
*/

import java.util.*;

public class RangeAddition {

    // This is a functional problem. You have to complete this function.
    // It takes as input a length and a 2D array of update operations.
    // It should return the modified array.
    public static int[] Range(int length, int[][] updates) {
        // write your code here.
        int[] arr = new int[length];
        for (int[] x : updates) {
            arr[x[0]] += x[2];
            if (x[1] + 1 < length)
                arr[x[1] + 1] -= x[2];
        }
        for (int i = 1; i < length; i++)
            arr[i] += arr[i - 1];
        return arr;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input for length of first array.
        int length = sc.nextInt();

        int K = sc.nextInt();

        int[][] updates = new int[K][3];

        for (int i = 0; i < updates.length; i++) {
            for (int j = 0; j < updates[0].length; j++) {
                updates[i][j] = sc.nextInt();
            }
        }
        sc.close();
        int[] result = Range(length, updates);
        display(result);
    }

    // function to display an array.
    public static void display(int[] arr) {

        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }

        System.out.println();
    }

}