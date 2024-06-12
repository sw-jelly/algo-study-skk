package Bitmasking.p17244_아맞다우산;

import java.io.*;
import java.util.*;

public class p17244_YK {

    static int[][] map;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};
    static int N, M, objects, sx, sy, ex, ey;

    static class Point {
        int x, y, flag, cnt;

        public Point(int x, int y, int flag, int cnt) {
            this.x = x;
            this.y = y;
            this.flag = flag;
            this.cnt = cnt;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        int oCnt = 0;
        for (int i = 0; i < N; ++i) {
            String line = br.readLine();
            for (int j = 0; j < M; ++j) {
                char ch = line.charAt(j);
                if (ch == '#') map[i][j] = -1;
                else if (ch == 'X') map[i][j] = ++oCnt;
                else if (ch == 'S') {
                    sx = i; sy = j;
                }
                else if (ch == 'E') {
                    ex = i;
                    ey = j;
                }
            }
        }

        for (int i = 0; i < oCnt; ++i) {
            objects |= (1 << i);
        }


        System.out.println(bfs());
        br.close();
    }

    private static int bfs() {
        Queue<Point> q = new ArrayDeque<>();
        boolean[][][] visited = new boolean[N][M][1 << 5];
        visited[sx][sy][0] = true;
        q.add(new Point(sx, sy, 0, 0));

        while (!q.isEmpty()) {
            Point now = q.poll();

            if (now.x == ex && now.y == ey) {
                if (now.flag != objects) continue;
                return now.cnt;
            }

            if (map[now.x][now.y] > 0) {
                now.flag |= (1 << (map[now.x][now.y] - 1));
            }

            for (int i = 0, nx, ny; i < 4; ++i) {
                nx = now.x + dx[i];
                ny = now.y + dy[i];

                if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
                if (map[nx][ny] == -1) continue;
                if (visited[nx][ny][now.flag]) continue;

                visited[nx][ny][now.flag] = true;
                q.offer(new Point(nx, ny, now.flag, now.cnt + 1));
            }
        }

        return -1;
    }
}
