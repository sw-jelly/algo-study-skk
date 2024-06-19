package BackTracking.p15684_사다리조작;

import java.io.*;
import java.util.*;

public class p15684_YK {

    static boolean[][] routes;
    static int N, H, M, maxRoutes, result = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        maxRoutes = H * (N - 1);
        routes = new boolean[H][N - 1];

        for (int i = 0, a, b; i < M; ++i) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            routes[a - 1][b - 1] = true;
        }

        dfs(0, 0);
        System.out.println(result == Integer.MAX_VALUE ? -1 : result);
        br.close();
    }

    private static void dfs(int depth, int cnt) {
        if (result == 0) return;
        if (cnt >= result) return;

        if (cnt == 3 || depth == maxRoutes) {
            for (int i = 0; i < N; ++i) {
                if (!check(i)) return;
            }

            result = cnt;
            return;
        }

        int x = depth / (N - 1);
        int y = depth % (N - 1);

        dfs(depth + 1, cnt);
        if (!routes[x][y]) {
            if (y > 0 && routes[x][y - 1]) {}
            else if (y < N - 2 && routes[x][y + 1]) {}
            else {
                routes[x][y] = true;
                dfs(depth + 1, cnt + 1);
            }

            routes[x][y] = false;
        }
    }

    private static boolean check(int ey) {
        int x = 0, y = ey;
        while (x < H) {
            if (y > 0 && routes[x][y - 1]) { // 좌
                x += 1; y -= 1;
            } else if (y < N - 1 && routes[x][y]) { // 우
                x += 1; y += 1;
            } else { // 하
                x += 1;
            }
        }
        return y == ey;
    }
}
