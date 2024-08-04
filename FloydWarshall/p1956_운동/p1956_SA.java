import java.util.*;
import java.io.*;

public class p1956_SA {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int V, E;
    static long[][] dist;
    static final long MAX = Long.MAX_VALUE/4;

    public static void main(String[] args) throws Exception{
        input();
        solve();
    }

    static void solve(){
        // 플로이드 와샬
        for(int k=1; k<V+1; k++) {
            for (int i=1; i<V+1; i++) {
                for (int j=1; j<V+1; j++) {
                    dist[i][j] = Math.min(dist[i][j], dist[i][k]+dist[k][j]);
                }
            }
        }

        long min = MAX;
        for(int i=1; i<V+1; ++i){
            for(int j=1; j<V+1; ++j){
                if(i==j) continue;
                min = Math.min(min, dist[i][j] + dist[j][i]);
            }
        }

        if(min == MAX){
            System.out.println(-1);
        } else{
            System.out.println(min);
        }
    }

    static void input() throws Exception{
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        dist = new long[V+1][V+1];
        for(int i=0; i<V+1; ++i){
            Arrays.fill(dist[i], MAX);
            dist[i][i] = 0;
        }

        for(int i=0; i<E; ++i){
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int val = Integer.parseInt(st.nextToken());

            dist[start][end] = val;
        }
    }
}
