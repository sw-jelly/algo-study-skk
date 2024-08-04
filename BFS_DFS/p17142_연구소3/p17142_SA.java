import java.util.*;
import java.io.*;

public class p17142_SA {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, M;
    static int[][] map;
    static int time = Integer.MAX_VALUE;
    static int empty = 0;
    static int available = 0;
    static final int[] dr = {-1, 1, 0, 0};
    static final int[] dc = {0, 0, -1, 1};

    static List<Point> availables = new ArrayList<>();

    static class Point{
        int r;
        int c;

        Point(int r, int c){
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws Exception {
        input();
        solve();
        output();
    }

    static void output(){
        System.out.println((time == Integer.MAX_VALUE)? -1 : time);
    }

    static void solve(){
        if(empty == 0){
            time = 0;
            return;
        }
        go(0, 0, new boolean[available]);
    }

    static void go(int curr, int cnt, boolean[] visited){
        if(cnt == M){
            //System.out.println(Arrays.toString(visited));
            time = Math.min(bfs(visited), time);
            return;
        }

        for(int i=curr; i < available; ++i){
            visited[i] = true;
            go(i+1, cnt+1, visited);
            visited[i] = false;
        }
    }

    static Queue<Point> q = new LinkedList<>();
    static int bfs(boolean[] virusPos){
        q.clear();

        boolean[][] visited = new boolean[N][N];
        for(int i=0; i<available; ++i){
            if(virusPos[i]){
                Point pnt = availables.get(i);
                q.add(pnt);
                visited[pnt.r][pnt.c] = true;
            }
        }

        int t = 0;
        int e = empty;
        while(!q.isEmpty()){
            int size = q.size();

            while(size-- > 0){
                Point curr = q.poll();

                for(int i=0; i<4; ++i){
                    int nr = curr.r + dr[i];
                    int nc = curr.c + dc[i];

                    if(nr < 0 || nc < 0 || nr >= N || nc >= N || visited[nr][nc] || map[nr][nc] == 1){
                        continue;
                    }

                    visited[nr][nc] = true;
                    if(map[nr][nc] == 0){
                        if(--e == 0){
                            return t+1;
                        }
                    }
                    q.add(new Point(nr, nc));
                }
            }
            ++t;
        }
        return Integer.MAX_VALUE;
    }

    static void input() throws Exception{
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][N];

        for(int r=0; r<N; ++r){
            st = new StringTokenizer(br.readLine());
            for(int c=0; c<N; ++c){
                map[r][c] = Integer.parseInt(st.nextToken());

                if(map[r][c] == 2){
                    availables.add(new Point(r, c));
                    continue;
                }

                if(map[r][c] == 0){
                    ++empty;
                }
            }
        }

        available = availables.size();

    }
}
