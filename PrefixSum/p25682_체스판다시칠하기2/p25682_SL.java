package PrefixSum.p25682_체스판다시칠하기2;

import java.io.*;
import java.util.*;

public class p25682_SL {

    static int N, M, K;
    static int result = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        int[][] whiteFirst = new int[N+1][M+1];
        int[][] blackFirst = new int[N+1][M+1];

        for (int i=1; i<=N; i++) {
            String line = br.readLine();
            for (int j=1; j<=M; j++) {
                int num = line.charAt(j-1) == 'B' ? 1 : 0;
                if ((i + j) % 2 != 0) {
                    num = num == 1 ? 0 : 1;
                }
                whiteFirst[i][j] = whiteFirst[i][j - 1] + whiteFirst[i - 1][j] - whiteFirst[i - 1][j - 1] + (num == 0 ? 0 : 1);
                blackFirst[i][j] = blackFirst[i][j - 1] + blackFirst[i - 1][j] - blackFirst[i - 1][j - 1] + (num == 1 ? 0 : 1);
            }
        }

        K--;
        for (int i=1; i<=N-K; i++) {
            for (int j=1; j<=M-K; j++) {
                int white = whiteFirst[i+K][j+K] - whiteFirst[i-1][j+K] - whiteFirst[i+K][j-1] + whiteFirst[i-1][j-1];
                int black = blackFirst[i+K][j+K] - blackFirst[i-1][j+K] - blackFirst[i+K][j-1] + blackFirst[i-1][j-1];
                result = Math.min(result, Math.min(white, black));
            }
        }

        System.out.println(result);
    }
}
