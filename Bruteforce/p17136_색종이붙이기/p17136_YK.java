package Bruteforce.p17136_색종이붙이기;

import java.io.*;
import java.util.*;

public class p17136_YK {

    static int N = 10, result = 26;
    static boolean[][] map;
    static int[] papers = {0, 5, 5, 5, 5, 5};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        map = new boolean[N][N];
        for (int i = 0; i < N; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; ++j) {
                if (st.nextToken().equals("0")) continue;
                map[i][j] = true;
            }
        }

        dfs(0, 0);
        System.out.println(result > 25 ? -1 : result);
        br.close();
    }

    private static void dfs(int depth, int cnt) {
        if (result <= cnt) return;
        if (isValid()) {
            result = cnt;
            return;
        }
        if (depth >= 100) {
            return;
        }

        int x = depth / N, y = depth % N;
        if (!map[x][y]) {
            dfs(depth + 1, cnt);
            return;
        }

        for (int i = 5; i > 0; --i) {
            if (!check(x, y, i)) continue;

            for (int j = i; j > 0; --j) {
                if (papers[j] < 1) continue;
                changeState(x, y, j, true);
                --papers[j];
                dfs(depth + 1, cnt + 1);
                ++papers[j];
                changeState(x, y, j, false);
            }
            break;
        }
    }

    private static boolean check(int x, int y, int k) {
        if (papers[k] < 1) return false; // 해당 크기의 종이가 없다
        if (x + k > N || y + k > N) return false; // 범위를 벗어남
        for (int i = x; i < x + k; ++i) {
            for (int j = y; j < y + k; ++j) {
                if (!map[i][j]) return false; // 붙이면 안되는 영역
            }
        }
        return true;
    }

    private static void changeState(int x, int y, int k, boolean isPut) {
        for (int i = x; i < x + k; ++i) {
            for (int j = y; j < y + k; ++j) {
                map[i][j] = !isPut;
            }
        }
    }

    private static boolean isValid() {
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                if (map[i][j]) return false;
            }
        }
        return true;
    }
}
