package DynamicProgramming.p9592_LCS2;

import java.util.*;
import java.io.*;

public class p9592_LCS2_SL {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] s1 = br.readLine().toCharArray();
        char[] s2 = br.readLine().toCharArray();

        int a = s1.length;
        int b = s2.length;

        int[][] dp = new int[a+1][b+1];

        // 최대길이
        for(int i=1; i<=a; i++) {
            char c1 = s1[i-1];

            for(int j=1; j<=b; j++) {
                char c2 = s2[j-1];

                if(c1 == c2) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }

        System.out.println(dp[a][b]);

        // path
        if(dp[a][b] != 0) {
            StringBuilder sb = new StringBuilder();
            Stack<Character> st = new Stack<>();

            while(a>0 && b>0) {
                if(a==0 || b==0) {
                    break;
                }

                if(dp[a][b] == dp[a-1][b]) {
                    a--;
                } else if(dp[a][b] == dp[a][b-1]) {
                    b--;
                } else {
                    st.push(s1[a-1]);
                    a--;
                    b--;
                }
            }

            while(!st.isEmpty()) {
                sb.append(st.pop());
            }

            System.out.println(sb);

        }

    }
}
