package DynamicProgramming.p10835_카드게임;

import java.util.*;
import java.io.*;

public class p10835_SL {

    static int N;
    static int[] left;
    static int[] right;
    static int[][] dp;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        left = new int[N];
        right = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            left[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            right[i] = Integer.parseInt(st.nextToken());
        }

        dp = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i], -1);
        }

        System.out.println(solve(0, 0));

    }

    static int solve(int l, int r) {

        if (l == N || r == N) {
            return 0;
        }

        if (dp[l][r] != -1) {
            return dp[l][r];
        }

        dp[l][r] = 0;

        // 첫 번째 경우 : 왼쪽만 버리기 or 둘 다 버리기
        dp[l][r] = Math.max(dp[l][r], solve(l + 1, r));
        dp[l][r] = Math.max(dp[l][r], solve(l + 1, r + 1));

        // 두 번째 경우 : 오른쪽 버리기
        if (left[l] > right[r]) {
            dp[l][r] = Math.max(dp[l][r], solve(l, r + 1) + right[r]);
        }

        return dp[l][r];
    }

}
