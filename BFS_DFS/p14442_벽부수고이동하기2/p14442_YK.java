package p14442_벽부수고이동하기2;

import java.io.*;
import java.util.*;

public class p14442_YK {
    
    static int[][] map;
    static int N, M, K;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};
    
    static class Point {
        int x, y, k, cnt;
        
        public Point(int x, int y, int k, int cnt) {
            this.x = x;
            this.y = y;
            this.k = k;
            this.cnt = cnt;
        }
    }
    
    public static void main(String[] args) throws Exception {
        input();
        System.out.println(bfs());
    }
    
    private static int bfs() {
        boolean[][][] visited = new boolean[N][M][K + 1];
        Queue<Point> q = new ArrayDeque<>();
        
        q.add(new Point(0, 0, 0, 1));
        visited[0][0][0] = true;
        
        while (!q.isEmpty()) {
            Point p = q.poll();
            
            if (p.x == N - 1 && p.y == M - 1) {
                return p.cnt;
            }
            
            for (int i = 0, nx, ny; i < 4; ++i) {
                nx = p.x + dx[i];
                ny = p.y + dy[i];
                
                if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
                if (map[nx][ny] == 1 && p.k == K) continue;
                if (visited[nx][ny][p.k + map[nx][ny]]) continue;
                
                visited[nx][ny][p.k + map[nx][ny]] = true;
                q.add(new Point(nx, ny, p.k + map[nx][ny], p.cnt + 1));
            }
        }
        
        return -1;
    }
    
    private static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        
        map = new int[N][M];
        for (int i = 0; i < N; ++i) {
            String line = br.readLine();
            for (int j = 0; j < M; ++j) {
                map[i][j] = line.charAt(j) - '0';
            }
        }
    }
}
