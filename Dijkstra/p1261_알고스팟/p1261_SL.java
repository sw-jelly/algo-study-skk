package Dijkstra.p1261_알고스팟;

import java.io.*;
import java.util.*;

public class p1261_SL {

    static class Info implements Comparable<Info> {
        int x, y, cnt;
        public Info(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
        @Override
        public int compareTo(Info o) {
            return Integer.compare(this.cnt, o.cnt);
        }
    }

    static int[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int N, M;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for(int i=0; i<N; i++) {
            String line = br.readLine();
            for(int j=0; j<M; j++) {
                map[i][j] = line.charAt(j) - '0';
            }
        }

        System.out.println(solve());

    }

    private static int solve() {

        PriorityQueue<Info> pq = new PriorityQueue<>();
        pq.add(new Info(0, 0, 0));

        boolean[][] visited = new boolean[N + 1][M + 1];
        visited[0][0] = true;

        while(!pq.isEmpty()) {
            Info cur = pq.poll();

            if(cur.x == N-1 && cur.y == M-1) {
                return cur.cnt;
            }

            for(int d=0; d<4; d++) {
                int nx = cur.x + dx[d];
                int ny = cur.y + dy[d];

                if (nx < 0 || ny < 0 || nx >= N || ny >= M || visited[nx][ny]) {
                    continue;
                }

                if (map[nx][ny] == 0) {
                    visited[nx][ny] = true;
                    pq.add(new Info(nx, ny, cur.cnt));
                } else {
                    visited[nx][ny] = true;
                    pq.add(new Info(nx, ny, cur.cnt+1));
                }

            }
        }

        return -1;
    }
}
