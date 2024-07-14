package Bitmasking.p5175_문제없는문제;

import java.io.*;
import java.util.*;

public class p5175_YK {

    static int M, N, selectedAlgos, result, minCnt;
    static int[] problems;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int K = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= K; ++tc) {
            sb.append("Data Set ").append(tc).append(": ");
            st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());

            problems = new int[N];
            for (int i = 0; i < N; ++i) {
                st = new StringTokenizer(br.readLine());
                while (st.hasMoreTokens()) {
                    int algoIndex = Integer.parseInt(st.nextToken()) - 1;
                    problems[i] |= (1 << algoIndex);
                }
            }

            minCnt = Integer.MAX_VALUE; // 선택된 문제 수
            result = Integer.MAX_VALUE; // 선택된 문제 목록
            selectedAlgos = 0;
            chooseAlgo(0, 0, 0);

            for (int i = 0; i < N; ++i) {
                if ((result & (1 << i)) == 0) continue;
                sb.append((char) (i + 'A')).append(" ");
            }
            sb.append("\n\n");
        }

        System.out.println(sb.toString().trim());
        br.close();
    }

    private static void chooseAlgo(int k, int flag, int cnt) {
        if (k == N) {
            if (minCnt < cnt) return;
            if (result <= flag) return;
            if (selectedAlgos + 1 < (1 << M)) return;
            minCnt = cnt;
            result = flag;
            return;
        }

        int tmp = selectedAlgos;
        chooseAlgo(k + 1, flag, cnt);
        selectedAlgos |= problems[k];
        chooseAlgo(k + 1, flag | (1 << k), cnt + 1);
        selectedAlgos = tmp;
    }
}
