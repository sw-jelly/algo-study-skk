package p2151_거울설치;

import java.io.*;
import java.util.*;

public class p2151_YK {

    static int N, result = Integer.MAX_VALUE;
    static char[][] map;
    static final char WALL = '*', MIRROR = '!', DOOR = '#';

    static class Point {
        int x, y, cnt, d;

        public Point(int x, int y, int cnt, int d) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
            this.d = d;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new char[N][N];
        for (int i = 0; i < N; ++i) {
            map[i] = br.readLine().toCharArray();
        }

        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                if (map[i][j] != DOOR) continue;
                bfs(i, j);
                System.out.println(result);
                return;
            }
        }

        br.close();
    }

    private static void bfs(int x, int y) {
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};

        Queue<Point> q = new ArrayDeque<>();
        int[][][] visited = new int[N][N][4];
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                Arrays.fill(visited[i][j], Integer.MAX_VALUE);
            }
        }

        map[x][y] = '.';

        for (int i = 0, nx, ny; i < 4; ++i) {
            nx = x + dx[i];
            ny = y + dy[i];

            if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
            if (map[nx][ny] == WALL) continue;

            q.add(new Point(x, y, 0, i));
            visited[x][y][i] = 0;
        }

        while (!q.isEmpty()) {
            Point now = q.poll();

            if (map[now.x][now.y] == DOOR) {
                result = Math.min(now.cnt, result);
                continue;
            }

            if (map[now.x][now.y] == MIRROR) {
                for (int i = 1; i < 4; i += 2) {
                    int nx = now.x + dx[(now.d + i) % 4];
                    int ny = now.y + dy[(now.d + i) % 4];

                    if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
                    if (map[nx][ny] == WALL) continue;
                    if (visited[nx][ny][(now.d + i) % 4] <= now.cnt) continue;

                    visited[nx][ny][(now.d + i) % 4] = now.cnt + 1;
                    q.add(new Point(nx, ny, now.cnt + 1, (now.d + i) % 4));
                }
            }

            int nx = now.x + dx[now.d];
            int ny = now.y + dy[now.d];

            if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
            if (map[nx][ny] == WALL) continue;
            if (visited[nx][ny][now.d] <= now.cnt) continue;

            visited[nx][ny][now.d] = now.cnt;
            q.add(new Point(nx, ny, now.cnt, now.d));
        }
    }
}
