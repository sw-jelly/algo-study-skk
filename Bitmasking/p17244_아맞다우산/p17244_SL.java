package Bitmasking.p17244_아맞다우산;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class p17244_SL {

    static class Info {
        int x, y, dist, cnt;
        public Info(int x, int y, int dist, int cnt) {
            this.x = x;
            this.y = y;
            this.dist = dist;
            this.cnt = cnt;
        }
    }

    static int N, M, Sx, Sy, Ex, Ey, key;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[][] map;
    static int[][][] dist;
    static int INF = 2_000_000_000;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        dist = new int[N][M][1<<5]; // 물건 최대 5개 주머니 늒낌쓰

        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                for(int k=0; k<(1<<5); k++) {
                    dist[i][j][k] = INF;
                }
            }
        }


        key = 0; // map에 key 0~4 저장

        for(int i=0; i<N; i++) {
            String line = br.readLine();
            for(int j=0; j<M; j++) {
                char c = line.charAt(j);
                if(c == '#') {
                    map[i][j] = 5; // 벽
                } else if(c == 'S') {
                    Sx = i;
                    Sy = j;
                    map[i][j] = -1; // 길
                } else if(c == 'E') {
                    Ex = i;
                    Ey = j;
                    map[i][j] = -1; // 길
                } else if(c == 'X') {
                    map[i][j] = key;
                    key++;
                } else if(c == '.'){
                    map[i][j] = -1;
                }
            }
        }

        go();
        System.out.println(dist[Ex][Ey][(1<<key)-1]);

    }

    public static void go() {

        Queue<Info> q = new LinkedList<>();
        q.add(new Info(Sx, Sy, 0, 0));
        dist[Sx][Sy][0] = 0;

        while(!q.isEmpty()) {
            Info cur = q.poll();

            if(cur.x == Ex && cur.y == Ey && cur.cnt==((1<<key)-1)) {
                return;
            }

            for(int i=0; i<4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if(0<=nx && nx<N && 0<=ny && ny<M && map[nx][ny]!=5) {
                    // 갈 수 있음
                    int mapInfo = map[nx][ny];
                    int nextKey = cur.cnt;
                    if(0<=mapInfo && mapInfo<key) { // 물건일때
                        nextKey = nextKey | (1<<mapInfo);
                    }
                    if(dist[nx][ny][nextKey] > cur.dist+1) {
                        dist[nx][ny][nextKey] = cur.dist+1;
                        q.add(new Info(nx, ny, cur.dist+1, nextKey));
                    }

                }
            }


        }


    }

}
