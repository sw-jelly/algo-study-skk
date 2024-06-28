package p17142_연구소3;

import java.util.*;
import java.io.*;

public class p17142_SL {

    static class Info {
        int x, y;
        public Info(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int N, M, answer, cnt0;
    static int[][] map;
    static ArrayList<Info> virus;
    static int[] virusIndex;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        // 2 개수 중에 M개를 뽑는 조합
        virus = new ArrayList<>();
        virusIndex = new int[M];

        cnt0 = 0;
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 2) {
                    virus.add(new Info(i, j));
                } else if(map[i][j] == 0) {
                    cnt0++;
                }
            }
        }

        if(cnt0 == 0) {
            System.out.println(0);
            return;
        }
        answer = 2_000_000_000;
        combi(0, 0);
        System.out.println(answer == 2_000_000_000 ? -1 : answer);

    }

    private static void solve(int[] index) {
        int[][] copyMap = new int[N][N];
        boolean[][] visited = new boolean[N][N];
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                copyMap[i][j] = map[i][j];
            }
        }

        Queue<Info> q = new LinkedList<>();

        for(int i=0; i<M; i++) {
            Info v = virus.get(index[i]);
            visited[v.x][v.y] = true;
            q.add(v);
        }

        int c = 0;
        int time = 0;

        while(!q.isEmpty()) {
            if (c == cnt0) {
                answer = Math.min(answer, time);
                return;
            }
            time++;

            for(int k=0, size=q.size(); k<size; k++) {
                Info cur = q.poll();

                for (int i = 0; i < 4; i++) {
                    int nx = cur.x + dx[i];
                    int ny = cur.y + dy[i];

                    if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                    if (visited[nx][ny] || map[nx][ny] == 1) continue;

                    if (map[nx][ny] == 0) {
                        c++;
                    }
                    visited[nx][ny] = true;
                    q.offer(new Info(nx, ny));
                }
            }
        } // while

    }

    private static void combi(int start, int cnt) {
        if(cnt == M) {
//            System.out.println(Arrays.toString(virusIndex));
            solve(virusIndex);
            return;
        }

        for(int i=start; i<virus.size(); i++) {
            virusIndex[cnt] = i;
            combi(i+1, cnt+1);
        }
    }

}
