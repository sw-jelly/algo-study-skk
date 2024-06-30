package PrefixSum.p25682_체스판다시칠하기2;

import java.io.*;
import java.util.*;

public class p25682_YK {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[][] wBoard = new int[N + 1][M + 1];
        int[][] bBoard = new int[N + 1][M + 1];

        for (int i = 1; i <= N; ++i) {
            String line = br.readLine();
            for (int j = 1; j <= M; ++j) {
                char ch = line.charAt(j - 1);
                if ((i + j) % 2 != 0) {
                    ch = ch == 'B' ? 'W' : 'B';
                }
                wBoard[i][j] = wBoard[i][j - 1] + wBoard[i - 1][j] - wBoard[i - 1][j - 1] + (ch == 'W' ? 0 : 1);
                bBoard[i][j] = bBoard[i][j - 1] + bBoard[i - 1][j] - bBoard[i - 1][j - 1] + (ch == 'B' ? 0 : 1);
            }
        }

        int result = Integer.MAX_VALUE;
        --K;
        for (int i = 1; i <= N - K; ++i) {
            for (int j = 1, wTmp, bTmp; j <= M - K; ++j) {
                wTmp = wBoard[i + K][j + K] - wBoard[i - 1][j + K] - wBoard[i + K][j - 1] + wBoard[i - 1][j - 1];
                bTmp = bBoard[i + K][j + K] - bBoard[i - 1][j + K] - bBoard[i + K][j - 1] + bBoard[i - 1][j - 1];
                result = Math.min(result, Math.min(wTmp, bTmp));
            }
        }

        System.out.println(result);
        br.close();
    }
}
