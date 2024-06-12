package p2151_거울설치;
import java.io.*;
import java.util.*;

public class p2151_SL {

    static class Info implements Comparable<Info> {
        int x, y, dir, cnt;
        public Info(int x, int y, int dir, int cnt) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.cnt = cnt;
        }
        @Override
        public int compareTo(Info o) {
            return this.cnt - o.cnt;
        }
    }

    static int N, Sx, Sy, Ex, Ey, result;
    static char[][] map;
    static int[][][] dist;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        boolean first = true;
        map = new char[N][N];
        for(int i=0; i<N; i++) {
            String line = br.readLine();
            for(int j=0; j<N; j++) {
                char c = line.charAt(j);
                if(c == '#') {
                    if(first) {
                        Sx = i;
                        Sy = j;
                        first = false;
                    } else {
                        Ex = i;
                        Ey = j;
                    }
                }
                map[i][j] = c;
            }
        }

        dist = new int[N][N][4];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                Arrays.fill(dist[i][j], Integer.MAX_VALUE);
            }
        }

        result = 2_000_000_000;
        go();
        System.out.println(result);

    }

    // dir: 0(상), 1(하), 2(좌), 3(우)
    private static void go() {

        PriorityQueue<Info> pq = new PriorityQueue<>();
        for(int i=0; i<4; i++) {
            pq.add(new Info(Sx, Sy, i, 0));
            dist[Sx][Sy][i] = 0;
        }

        while(!pq.isEmpty()) {
            Info cur = pq.poll();
            int x = cur.x;
            int y = cur.y;
            int dir = cur.dir;
            int cnt = cur.cnt;

            if(x == Ex && y == Ey) {
                result = Math.min(result, cnt);
                return;
            }

            int nx = x + dx[dir];
            int ny = y + dy[dir];

            if(nx < 0 || ny < 0 || nx >= N || ny >= N || map[nx][ny] == '*') { // 벽
                continue;
            }

            if(dist[nx][ny][dir] > cnt) {
                dist[nx][ny][dir] = cnt;
                pq.add(new Info(nx, ny, dir, cnt));

                if(map[nx][ny] == '!') { // 거울
                    if(dir == 0 || dir == 1) { // 상하
                        if(dist[nx][ny][2] > cnt+1) {
                            dist[nx][ny][2] = cnt+1;
                            pq.add(new Info(nx, ny, 2, cnt+1));
                        }
                        if(dist[nx][ny][3] > cnt+1) {
                            dist[nx][ny][3] = cnt+1;
                            pq.add(new Info(nx, ny, 3, cnt+1));
                        }
                    } else { // 좌우
                        if(dist[nx][ny][0] > cnt+1) {
                            dist[nx][ny][0] = cnt+1;
                            pq.add(new Info(nx, ny, 0, cnt+1));
                        }
                        if(dist[nx][ny][1] > cnt+1) {
                            dist[nx][ny][1] = cnt+1;
                            pq.add(new Info(nx, ny, 1, cnt+1));
                        }
                    }
                }
            }


        }

    }


}
