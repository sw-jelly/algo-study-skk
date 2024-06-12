package BackTracking.p1941_소문난칠공주;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class p1941_SL {

    static class Info {

        int x, y;

        public Info(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static char[][] map;
    static boolean[][] visited;
    static int result;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        result = 0;
        map = new char[5][5];

        for (int i = 0; i < 5; i++) {
            String line = br.readLine();
            for (int j = 0; j < 5; j++) {
                map[i][j] = line.charAt(j);
            }
        }

        visited = new boolean[5][5];
        combi(0, 0);

        System.out.println(result);
    }

    public static void combi(int cnt, int start) {
        if (cnt == 7) {
            if (check()) {
                result++;
            }
            return;
        }

        for (int i = start; i < 25; i++) {
            visited[i / 5][i % 5] = true;
            combi(cnt + 1, i + 1);
            visited[i / 5][i % 5] = false;
        }
    }

    public static boolean check() { // 이다솜파가 4명 이상인지 확인 && 7명이 서로 인접했는지 확인

        int sCnt = 0;
        Queue<Info> q = new LinkedList<>();
        boolean[][] tempVisited = new boolean[5][5];
        boolean first = true;
        int startX = 0;
        int startY = 0;

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (visited[i][j]) {
                    if (map[i][j] == 'S') {
                        sCnt++;
                    }
                    if (first) {
                        startX = i;
                        startY = j;
                        first = false;
                    }
                }
            }

        }

        if (sCnt < 4) {
            return false;
        }

        q.add(new Info(startX, startY));
        tempVisited[startX][startY] = true;
        int cnt = 1;
        while (!q.isEmpty()) {
            Info cur = q.poll();
            int x = cur.x;
            int y = cur.y;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || ny < 0 || nx >= 5 || ny >= 5) {
                    continue;
                }

                if (visited[nx][ny] && !tempVisited[nx][ny]) {
                    cnt++;
                    tempVisited[nx][ny] = true;
                    q.add(new Info(nx, ny));
                }
            }
        }

        return cnt == 7;

    }

}
