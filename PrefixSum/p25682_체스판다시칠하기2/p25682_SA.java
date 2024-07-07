import java.util.*;
import java.io.*;

public class p25682_SA {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, M, K;
    static int[][] map;
    static int min = 2000*2000;
    public static void main(String[] args) throws Exception {
        input();
        solve();
    }

    static void solve(){

        int[][] sum = new int[N+1][M+1];

        // 다시 칠해야하는 칸을 1로 표시
        int ch = 0;
        for(int r=1; r<N+1; ++r){
            for(int c=1; c<M+1; ++c){
                if(map[r][c] != ch){
                    sum[r][c] = 1;
                }
                ch ^= 1;
            }
            ch = (r%2!=0)? 1:0;
        }


        // 누적합(행)
        for(int r=1; r<N+1; ++r){
            for(int c=1; c<M+1; ++c){
                sum[r][c] += sum[r][c-1];
            }
        }

        // 누적합(열)
        for(int c=1; c<M+1; ++c){
            for(int r=1; r<N+1; ++r){
                sum[r][c] += sum[r-1][c];
            }
        }

        for(int sr=1; sr<N-K+2; ++sr){
            for(int sc=1; sc<M-K+2; ++sc){
                int er=sr+K-1;
                int ec=sc+K-1;

                int cnt = sum[er][ec] - sum[sr-1][ec] - sum[er][sc-1] + sum[sr-1][sc-1];

                min = Math.min(min, cnt);
                min = Math.min(min, K*K-cnt);
                //System.out.printf("%d %d %d %d %d %d%n", sr, sc, er, ec, cnt, K*K-cnt);
            }
        }

        System.out.println(min);
    }

    static void input() throws Exception{
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N+1][M+1];
        for(int i=1; i<N+1; ++i){
            String str = br.readLine();
            for(int j=1; j<M+1; ++j){
                map[i][j] = 0;
                if(str.charAt(j-1)=='W'){
                    map[i][j] = 1;
                }
            }
        }
    }
}
