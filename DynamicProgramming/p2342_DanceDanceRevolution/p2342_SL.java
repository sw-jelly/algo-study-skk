package DynamicProgramming.p2342_DanceDanceRevolution;

import java.util.*;
import java.io.*;

public class p2342_SL {

    static int[] arr;
    static int[][][] dp; // left, right, cnt
    static int n;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] s = br.readLine().split(" ");
        n = s.length - 1;
        arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(s[i]);
        }

        dp = new int[5][5][n + 1];
        System.out.println(solve(0, 0, 0));

    }

    private static int solve(int left, int right, int cnt) {

        if(cnt == n) {
            return 0;
        }

        if(dp[left][right][cnt] != 0) {
            return dp[left][right][cnt];
        }

        int leftScore = score(left, arr[cnt]) + solve(arr[cnt], right, cnt + 1);
        int rightScore = score(right, arr[cnt]) + solve(left, arr[cnt], cnt + 1);

        return dp[left][right][cnt] = Math.min(leftScore, rightScore);

    }

    private static int score(int from, int to) {

        if(from == to) {
            return 1;
        } else if(from == 0) {
            return 2;
        } else if(Math.abs(from - to) == 2) {
            return 4;
        } else {
            return 3;
        }

    }

}
