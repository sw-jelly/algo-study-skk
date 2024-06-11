package BackTracking.p1941_소문난칠공주;

import java.io.*;
import java.util.*;

public class p1941_YK {
    static boolean[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int N, result;

    static class Point {
        int x, y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = 5;
        map = new boolean[N][N];
        visited = new boolean[N][N];

        for (int i = 0; i < N; ++i) {
            String line = br.readLine();
            for (int j = 0; j < N; ++j) {
                char ch = line.charAt(j);
                if (ch == 'S') map[i][j] = true;
            }
        }

        dfs(0, 0, 0);
        System.out.println(result);
    }

    private static boolean bfs(int x, int y) {
        boolean[][] v = new boolean[N][N];
        Queue<Point> q = new ArrayDeque<>();
        int r = 1;

        v[x][y] = true;
        q.add(new Point(x, y));

        while (!q.isEmpty()) {
            Point now = q.poll();

            for (int i = 0, nx, ny; i < 4; ++i) {
                nx = now.x + dx[i];
                ny = now.y + dy[i];

                if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
                if (v[nx][ny]) continue;
                if (!visited[nx][ny]) continue;

                ++r;
                v[nx][ny] = true;
                q.add(new Point(nx, ny));
            }
        }

        return r >= 7;
    }

    private static void dfs(int k, int cnt, int depth) {
        if (cnt >= 4) {
            return;
        }

        if (depth == 7) {
            if (bfs(k / N, k % N)) ++result;
            return;
        }

        for (int i = k, nx, ny; i < 25; ++i) {
            nx = i / N; ny = i % N;
            if (visited[nx][ny]) continue;

            visited[nx][ny] = true;
            dfs(i, cnt + oneIfY(nx, ny), depth + 1);
            visited[nx][ny] = false;
        }
    }

    private static int oneIfY(int x, int y) {
        return map[x][y] ? 0 : 1;
    }
}
