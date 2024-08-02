package Dijkstra.p1445_일요일아침의데이트;

import java.io.*;
import java.util.*;

public class p1445_SL {

    static class Info implements Comparable<Info> {
        int x, y, g, ga;
        public Info(int x, int y) {
            this.x = x;
            this.y = y;
        }
        public Info(int x, int y, int g, int ga) {
            this.x = x;
            this.y = y;
            this.g = g;
            this.ga = ga;
        }
        @Override
        public int compareTo(Info o) {
            if(this.g == o.g) {
                return this.ga - o.ga;
            }
            return this.g - o.g;
        }
    }

    static int N,M, Sx,Sy, Fx,Fy;
    static char[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static ArrayList<Info> list;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 쓰레기로 차있는 칸을 되도록 적게 지나가기
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        list = new ArrayList<>();

        for(int i=0; i<N; i++) {
            String line = br.readLine();

            for(int j=0; j<M; j++) {
                char tmp = line.charAt(j);

                if(tmp == 'g') {
                    list.add(new Info(i, j));
                } else if(tmp == 'S') {
                    Sx = i;
                    Sy = j;
                } else if(tmp == 'F') {
                    Fx = i;
                    Fy = j;
                }

                map[i][j] = tmp;
            }
        }

        makeA();
        go();
    }

    // 쓰레기 옆 ga 만들어주기
    private static void makeA() {
        for(int i=0; i<list.size(); i++) {
            Info cur = list.get(i);

            for(int d=0; d<4; d++) {
                int nx = cur.x + dx[d];
                int ny = cur.y + dy[d];

                if(nx<0 || nx>=N || ny<0 || ny>=M) continue;

                if(map[nx][ny] == '.') {
                    map[nx][ny] = 'a';
                }
            }
        }
    }

    private static void go() {

        PriorityQueue<Info> pq = new PriorityQueue<>();
        boolean[][] visited = new boolean[N][M];
        pq.add(new Info(Sx, Sy, 0, 0));

        while(!pq.isEmpty()) {
            Info cur = pq.poll();

            if (visited[cur.x][cur.y]) continue;
            visited[cur.x][cur.y] = true;

            if (cur.x == Fx && cur.y == Fy) {
                System.out.println(cur.g + " " + cur.ga);
                return;
            }

            for(int i=0; i<4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                int g = cur.g;
                int ga = cur.ga;

                if(nx<0 || nx>=N || ny<0 || ny>=M) continue;
                if(visited[nx][ny]) continue;

                if(map[nx][ny] == 'g') {
                    g++;
                }
                if(map[nx][ny] == 'a') {
                    ga++;
                }

                pq.add(new Info(nx, ny, g, ga));
            }
        }
    }

}
