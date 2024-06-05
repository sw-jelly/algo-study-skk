package p20166_문자열지옥에빠진호석;

import java.io.*;
import java.util.*;

public class p20166_YK {

    static char[][] map;
    static int N, M, K;
    static Map<String, Integer> godString;
    static String[] list;
    static int[] dx = {0, 0, -1, 1, -1, -1, 1, 1};
    static int[] dy = {-1, 1, 0, 0, 1, -1, 1, -1};

    static class Pair {
        int x, y;
        StringBuilder sb;

        public Pair(int x, int y, String st) {
            this.x = x;
            this.y = y;
            this.sb = new StringBuilder();
            this.sb.append(st);
        }

        public Pair(int x, int y, StringBuilder st) {
            this.x = x;
            this.y = y;
            this.sb = st;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        godString = new HashMap<>();
        list = new String[K];

        for (int i = 0; i < N; ++i) {
            map[i] = br.readLine().toCharArray();
        }

        for (int i = 0; i < K; ++i) {
            list[i] = br.readLine();
            godString.put(list[i], 0);
        }

        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < M; ++j) {
                bfs(i, j);
            }
        }

        for (int i = 0; i < K; ++i) {
            System.out.println(godString.get(list[i]));
        }
    }

    private static void bfs(int x, int y) {
        Queue<Pair> q = new ArrayDeque<>();
        q.add(new Pair(x, y, String.valueOf(map[x][y])));

        while (!q.isEmpty()) {
            Pair p = q.poll();

            if (godString.containsKey(String.valueOf(p.sb))) {
                godString.put(String.valueOf(p.sb), godString.get(String.valueOf(p.sb)) + 1);
            }

            if (p.sb.length() == 5) {
                continue;
            }

            for (int i = 0, nx, ny; i < 8; ++i) {
                nx = p.x + dx[i];
                ny = p.y + dy[i];

                if (nx < 0) nx = N - 1;
                else if (nx >= N) nx = 0;

                if (ny < 0) ny = M - 1;
                else if (ny >= M) ny = 0;

                StringBuilder sb = new StringBuilder();
                sb.append(p.sb).append(map[nx][ny]);
                q.add(new Pair(nx, ny, sb));
            }
        }
    }
}
