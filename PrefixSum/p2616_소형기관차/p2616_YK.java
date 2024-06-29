package PrefixSum.p2616_소형기관차;

import java.io.*;
import java.util.*;

public class p2616_YK {

    static int[] prefixSum;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        prefixSum = new int[N + 1];
        int[][] dp = new int[4][N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; ++i) {
            prefixSum[i] += prefixSum[i - 1] + Integer.parseInt(st.nextToken());
        }

        int M = Integer.parseInt(br.readLine());

        for (int i = 1; i <= 3; ++i) {
            for (int j = 1; j <= N; ++j) {
                if (j < M) {
                    dp[i][j] = dp[i][j - 1];
                    continue;
                }
                // dp[i][j] : i번째 기관차까지 고려했을 때, 1 ~ j번 객차까지 최대 손님 수
                // j - M(최대 객차 길이) ~ j 까지의 기관차를 포함하는게 이득인지 아닌지 판단
                dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j - M] + calculateSum(j - M, j));
            }
        }

        System.out.println(dp[3][N]);
        br.close();
    }

    private static int calculateSum(int s, int e) {
        return prefixSum[e] - prefixSum[s];
    }
}
