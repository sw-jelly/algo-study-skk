import java.util.*;
import java.io.*;

public class p17829_SA {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static int[][] map;
    static int[][] output;

    static PriorityQueue<Integer> pq = new PriorityQueue<>((i1, i2) -> i2-i1);
    public static void main(String[] args) throws Exception {
        input();
        solve();
    }

    static void solve(){

        int n = N;
        while(n>1){
            go(n);

            map = output;
            n /= 2;
            if(n<2){
                break;
            }
            output = new int[n/2][n/2];
        }

        System.out.println(output[0][0]);
    }

    static void go(int n){

        for(int sr=0; sr<n; sr+=2){
            for(int sc=0; sc<n; sc+=2){

                for(int r=sr; r<sr+2; ++r){
                    for(int c=sc; c<sc+2; ++c){
                        pq.add(map[r][c]);
                    }
                }

                pq.poll();
                int ans = pq.poll();
                pq.clear();
                output[sr/2][sc/2] = ans;
            }

        }


        // for(int r=0; r<n/2; ++r){
        // for(int c=0; c<n/2; ++c){
        // System.out.printf("%d ", output[r][c]);
        // }
        // System.out.println();
        // }
        // System.out.println();

    }

    static void input() throws Exception{
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        output = new int[N/2][N/2];

        for(int r=0; r<N; ++r){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int c=0; c<N; ++c){
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }
    }

}
