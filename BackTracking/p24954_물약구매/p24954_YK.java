package BackTracking.p24954_물약구매;

import java.io.*;
import java.util.*;

public class p24954_YK {

    static int N, result = Integer.MAX_VALUE;
    static boolean[] visited;
    static int[] potions;
    static int[][] discounts;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        visited = new boolean[N];
        potions = new int[N];
        discounts = new int[N][N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; ++i) {
            potions[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < N; ++i) {
            int k = Integer.parseInt(br.readLine());
            for (int j = 0; j < k; ++j) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken()) - 1;
                int d = Integer.parseInt(st.nextToken());
                discounts[i][a] = d;
            }
        }

        perm(0, 0);
        System.out.println(result);
        br.close();
    }

    static void perm(int depth, int sum) {
        if (sum >= result) return;

        if (depth == N) {
            result = sum;
            return;
        }

        for (int i = 0; i < N; ++i) {
            if (visited[i]) continue;
            visited[i] = true;

            applyDiscounts(i, true);
            perm(depth + 1, sum + Math.max(potions[i], 1));
            applyDiscounts(i, false);

            visited[i] = false;
        }
    }

    static void applyDiscounts(int p, boolean isApply) {
        for (int i = 0; i < N; ++i) {
            potions[i] += discounts[p][i] * (isApply ? -1 : 1);
        }
    }
}
