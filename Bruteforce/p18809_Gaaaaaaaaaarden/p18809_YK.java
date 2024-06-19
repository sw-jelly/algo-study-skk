package Bruteforce.p18809_Gaaaaaaaaaarden;

import java.io.*;
import java.util.*;

public class p18809_YK {

    static int N, M, G, R, idx, result; // idx : 씨앗을 뿌릴 수 있는 영역의 수 (<= 10)
    static final int LAKE = 0, EMPTY = 1, AVAILABLE = 2, GREEN = 3, RED = 4, FLOWER = 5;
    static int[][] map;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};
    static Seed[][] tmp;
    static int[] isChosen;
    static Point[] availables = new Point[10];

    static class Point {
        int x, y, time, color;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Point(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }

    static class Seed {
        int color, time;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        G = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        tmp = new Seed[N][M];

        for (int i = 0; i < N; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; ++j) {
                map[i][j] = Integer.parseInt(st.nextToken());
                tmp[i][j] = new Seed();
                if (map[i][j] == AVAILABLE) {
                    map[i][j] = EMPTY;
                    availables[idx++] = new Point(i, j);
                }
            }
        }

        isChosen = new int[idx];
        dfs(0, 0, 0);

        System.out.println(result);
        br.close();
    }

    static void dfs(int depth, int red, int green) {
        if (depth == idx) {
            if (red + green < R + G) return;

            copyMap();
            // 씨앗 퍼뜨리기
            result = Math.max(bfs(), result);
            return;
        }

        Point p = availables[depth];

        if (red < R) {
            map[p.x][p.y] = RED;
            isChosen[depth] = RED;
            dfs(depth + 1, red + 1, green);
        }

        if (green < G) {
            map[p.x][p.y] = GREEN;
            isChosen[depth] = GREEN;
            dfs(depth + 1, red, green + 1);
        }

        map[p.x][p.y] = EMPTY;
        isChosen[depth] = 0;
        dfs(depth + 1, red, green);
    }


    static void copyMap() {
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < M; ++j) {
                tmp[i][j].color = map[i][j];
                tmp[i][j].time = 0;
            }
        }
    }

    static int bfs() {
        Queue<Point> q = new ArrayDeque<>();
        for (int i = 0; i < idx; ++i) {
            if (isChosen[i] == 0) continue;
            Point p = availables[i];
            q.add(new Point(p.x, p.y, 0));
        }

        int flowers = 0;
        while (!q.isEmpty()) {
            for (int k = 0, size = q.size(); k < size; ++k) {
                Point p = q.poll();
                Seed s = tmp[p.x][p.y];

                if (s.color == FLOWER) continue;

                for (int i = 0, nx, ny; i < 4; ++i) {
                    nx = p.x + dx[i];
                    ny = p.y + dy[i];

                    // 범위 밖
                    if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
                    Seed ns = tmp[nx][ny];

                    // 호수, 이미 꽃, 같은 색
                    if (ns.color == LAKE || ns.color == FLOWER || ns.color == s.color) continue;

                    if (ns.color == EMPTY) { // 빈 자리
                        ns.color = s.color;
                        ns.time = p.time + 1;
                        q.add(new Point(nx, ny, p.time + 1));
                    } else if (ns.time == p.time + 1) { // 같은 시간에 도착한 씨앗이 있음
                        ++flowers;
                        ns.color = FLOWER;
                    }
                }
            }
        }

        return flowers;
    }
}
