package Bruteforce.p16938_캠프준비;

import java.io.*;
import java.util.*;

public class p16938_YK {

    static int N, L, R, X, result;
    static int[] problems;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        problems = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; ++i) {
            problems[i] = Integer.parseInt(st.nextToken());
        }

        dfs(0, 0, Integer.MAX_VALUE, 0, 0);
        System.out.println(result);
        br.close();
    }

    static void dfs(int depth, int cnt, int min, int max, int sum) {
        if (sum > R) return;

        if (depth == N) {
            if (cnt < 2) return;
            if (sum < L) return;
            if (max - min < X) return;
            ++result;
            return;
        }

        int d = problems[depth];
        dfs(depth + 1, cnt, min, max, sum);
        dfs(depth + 1, cnt + 1, Math.min(min, d), Math.max(max, d), sum + d);
    }
}
