package DynamicProgramming.p1949_우수마을;

import java.util.*;
import java.io.*;

public class p1949_SL {

    static int N;
    static int[] people;
    static List<Integer>[] list;
    static int[][] dp;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        people = new int[N + 1];
        list = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            list[i] = new ArrayList<>();
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            people[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            list[a].add(b);
            list[b].add(a);
        }

        dp = new int[N + 1][2]; // 0: 우수마을이 아닌 경우, 1: 우수마을인 경우
        dfs(1, 0);
        System.out.println(Math.max(dp[1][0], dp[1][1]));

    }

    private static void dfs(int cur, int parent) {

        dp[cur][0] = 0;
        dp[cur][1] = people[cur];

        for (int i = 0; i < list[cur].size(); i++) {
            int next = list[cur].get(i);

            if(next == parent) {
                continue;
            }

            dfs(next, cur);
            dp[cur][0] += Math.max(dp[next][0], dp[next][1]);
            dp[cur][1] += dp[next][0];
        }

    }

}
