package p17142_연구소3;

import java.io.*;
import java.util.*;

public class p17142_YK {

    static int[][] map;
    static int N, M, idx, emptyCnt, result = Integer.MAX_VALUE;
    static final int EMPTY = 0, WALL = 1, VIRUS = 2;
    static Point[] viruses = new Point[10];
    static boolean[] isSelected = new boolean[10];

    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];

        for (int i = 0; i < N; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; ++j) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == VIRUS) {
                    viruses[idx++] = new Point(i, j);
                } else if (map[i][j] == EMPTY) {
                    ++emptyCnt;
                }
            }
        }

        dfs(0, 0);
        System.out.println(result == Integer.MAX_VALUE ? -1 : result);
        br.close();
    }

    private static void dfs(int depth, int cnt) {
        if (depth == idx) {
            if (cnt != M) return;
            result = Math.min(result, bfs());
            return;
        }

        if (cnt < M) {
            isSelected[depth] = true;
            dfs(depth + 1, cnt + 1);
            isSelected[depth] = false;
        }
        dfs(depth + 1, cnt);
    }

    private static int bfs() {
        Queue<Point> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][N];

        int cnt = 0, time = 0;
        for (int i = 0; i < idx; ++i) {
            if (!isSelected[i]) continue;
            Point p = viruses[i];
            visited[p.x][p.y] = true;
            q.add(new Point(p.x, p.y));
        }

        while (!q.isEmpty()) {
            if (cnt == emptyCnt) return time;
            ++time;

            for (int k = 0, size = q.size(); k < size; ++k) {
                Point p = q.poll();

                for (int i = 0, nx, ny; i < 4; ++i) {
                    nx = p.x + dx[i];
                    ny = p.y + dy[i];

                    if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
                    if (map[nx][ny] == WALL) continue;
                    if (visited[nx][ny]) continue;

                    if (map[nx][ny] == EMPTY) ++cnt;
                    visited[nx][ny] = true;
                    q.offer(new Point(nx, ny));
                }
            }
        }

        return cnt < emptyCnt ? Integer.MAX_VALUE : time;
    }
}
