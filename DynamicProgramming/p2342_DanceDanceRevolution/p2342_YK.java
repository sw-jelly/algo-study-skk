package DynamicProgramming.p2342_DanceDanceRevolution;

import java.io.*;
import java.util.*;

public class p2342_YK {

    // dp에 뭘 저장할 것이냐???
    // dp[왼발][오른발][현재 인덱스]

    static int N, result = Integer.MAX_VALUE;
    static List<Integer> ddr;
    static int[][][] dp;
    static int[][] move = {
            {0, 2, 2, 2, 2},
            {0, 1, 3, 4, 3},
            {0, 3, 1, 3, 4},
            {0, 4, 3, 1, 3},
            {0, 3, 4, 3, 1}
    };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        ddr = new ArrayList<>();

        while (true) {
            int k = Integer.parseInt(st.nextToken());
            if (k == 0) break;
            ddr.add(k);
        }

        N = ddr.size();
        dp = new int[N][5][5];

        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < 5; ++j) {
                Arrays.fill(dp[i][j], Integer.MAX_VALUE >> 2);
            }
        }

        dp[0][0][ddr.get(0)] = move[0][ddr.get(0)];
        dp[0][ddr.get(0)][0] = move[ddr.get(0)][0];

        for (int i = 1, now; i < N; ++i) {
            now = ddr.get(i);
            for (int left = 0; left < 5; ++left) {
                for (int right = 0; right < 5; ++right) {
                    if (left != now) { // 왼발이 해당 칸에 있지 않으면, 오른발 이동
                        dp[i][left][now] = Math.min(dp[i][left][now], dp[i - 1][left][right] + move[right][now]);
                    }
                    if (right != now) { // 오른발이 해당 칸에 있지 않으면, 왼발 이동
                        dp[i][now][right] = Math.min(dp[i][now][right], dp[i - 1][left][right] + move[left][now]);
                    }
                }
            }
        }

        int result = Integer.MAX_VALUE;
        for (int i = 0; i < 5; ++i) {
            for (int j = 0; j < 5; ++j) {
                result = Math.min(result, dp[N - 1][i][j]);
            }
        }

        System.out.println(result);
        br.close();
    }
}
