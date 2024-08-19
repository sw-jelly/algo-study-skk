package DynamicProgramming.p7579_앱;

import java.util.*;
import java.io.*;

public class p7579_SL {

    static class Info {
        int app, bt;
        public Info(int app, int bt) {
            this.app = app;
            this.bt = bt;
        }
    }

    static int N, M;
    static Info[] arr;
    static int[] dp;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st1, st2;

        st1 = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st1.nextToken());
        M = Integer.parseInt(st1.nextToken());
        arr = new Info[N];

        st1 = new StringTokenizer(br.readLine());
        st2 = new StringTokenizer(br.readLine());
        int maxCost = 0;
        for(int i=0; i<N; i++) {
            int tmp1 = Integer.parseInt(st1.nextToken());
            int tmp2 = Integer.parseInt(st2.nextToken());
            arr[i] = new Info(tmp1, tmp2);
            maxCost += tmp2;
        }

        dp = new int[maxCost + 1];
        for(int i=0; i<N; i++) {
            int app = arr[i].app;
            int bt = arr[i].bt;

            for(int j=maxCost; j>=bt; j--) {
                dp[j] = Math.max(dp[j], dp[j-bt] + app);
            }
        }

        //  0, 0,  0,  30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30
        // 10, 10, 10, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40
        // 10, 10, 10, 40, 40, 40, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60
        // 10, 10, 10, 40, 40, 45, 60, 60, 75, 75, 80, 95, 95, 110, 110, 115
        // 10, 10, 10, 40, 50, 50, 60, 80, 80, 85, 100, 100, 115, 115, 120, 135

        int result = maxCost;
        for (int i = 0; i <= maxCost; i++) {
            if (dp[i] >= M) {
                result = i;
                break;
            }
        }

        System.out.println(result);

    }

}
