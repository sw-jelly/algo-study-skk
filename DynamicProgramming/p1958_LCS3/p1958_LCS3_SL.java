package DynamicProgramming.p1958_LCS3;

import java.io.*;

public class p1958_LCS3_SL {

    static char[] s1, s2, s3;
    static int a, b, c;
    static int[][][] dp;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        s1 = br.readLine().toCharArray();
        s2 = br.readLine().toCharArray();
        s3 = br.readLine().toCharArray();

        a = s1.length;
        b = s2.length;
        c = s3.length;

        dp = new int[a+1][b+1][c+1];

        for (int i = 1; i <= a; i++) {
            for (int j = 1; j <= b; j++) {
                for (int k = 1; k <= c; k++) {
                    if (s1[i-1] == s2[j-1] && s2[j-1] == s3[k-1]) {
                        dp[i][j][k] = dp[i-1][j-1][k-1] + 1;
                    } else {
                        dp[i][j][k] = Math.max(Math.max(dp[i-1][j][k], dp[i][j-1][k]), dp[i][j][k-1]);
                    }
                }
            }
        }

        System.out.println(dp[a][b][c]);
    }
}