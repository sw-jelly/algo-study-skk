package DynamicProgramming.p1949_우수마을;

import java.io.*;
import java.util.*;

public class p1949_YK {

    static int N;
    static int[] cities;
    static List<Integer>[] route;
    static int[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        cities = new int[N];
        dp = new int[N][2];
        route = new List[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; ++i) {
            cities[i] = Integer.parseInt(st.nextToken());
            route[i] = new ArrayList<>();
        }

        for (int i = 0, a, b; i < N - 1; ++i) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken()) - 1;
            b = Integer.parseInt(st.nextToken()) - 1;
            route[a].add(b);
            route[b].add(a);
        }

        dfs(0, -1);
        System.out.println(Math.max(dp[0][0], dp[0][1]));
        br.close();
    }

    private static void dfs(int city, int parent) {
        dp[city][1] = cities[city];

        for (int next : route[city]) {
            if (next == parent) continue;

            dfs(next, city);
            dp[city][0] += Math.max(dp[next][0], dp[next][1]);
            dp[city][1] += dp[next][0]; // '우수 마을'끼리는 서로 인접해 있을 수 없다.
        }
    }
}
