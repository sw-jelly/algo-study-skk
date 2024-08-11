package DynamicProgramming.p9252_LCS2;

import java.io.*;
import java.util.*;

public class p9252_YK {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        char[] s1 = br.readLine().toCharArray();
        char[] s2 = br.readLine().toCharArray();
        int N = s1.length;
        int M = s2.length;

        int[][] dp = new int[N + 1][M + 1];

        for (int i = 1; i <= N; ++i) {
            for (int j = 1; j <= M; ++j) {
                if (s1[i - 1] == s2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        Stack<Character> s = new Stack<>();
        int i = N, j = M;
        while (i > 0 && j > 0) {
            if (s1[i - 1] == s2[j - 1]) {
                s.push(s1[i - 1]);
                --i;
                --j;
            } else if (dp[i][j - 1] > dp[i - 1][j]) {
                --j;
            } else {
                --i;
            }
        }

        sb.append(dp[N][M]).append("\n");
        while (!s.isEmpty()) {
            sb.append(s.pop());
        }
        System.out.println(sb);
        br.close();
    }
}
