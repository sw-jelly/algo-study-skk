package DynamicProgramming.p7579_앱;

import java.util.*;
import java.io.*;

public class p7579_SA {
    
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, M;
    static int[] costs, memories;
     
    public static void main(String[] args) throws Exception {
        input();
        solve();
    }
    
    static void solve(){
        
        long[][] dp = new long[N+1][10001]; 
        final long INF = 1_000_000_001;
        
        for(int i=1; i<N+1; ++i){
            for(int j=0; j<10001; ++j){
                if(costs[i] <= j){
                    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][Math.max(0, j-costs[i])] + memories[i]);
                    //System.out.printf("%d %d%n",j, dp[i][j]); 
                    continue;  
                }
                
                dp[i][j] = dp[i-1][j];
                //System.out.printf("%d %d%n",j, dp[i][j]);    
            }
            //break;
        }
        
        for(int i=0; i<10001; ++i){
            if(dp[N][i] >= M){
                System.out.println(i);
                break;
            }
        }
    }
    
    static void input() throws Exception{
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        costs = new int[N+1];
        memories = new int[N+1];
        
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<N+1; ++i){
            memories[i] = Integer.parseInt(st.nextToken());
        }
        
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<N+1; ++i){
            costs[i] = Integer.parseInt(st.nextToken());
        }
    }
}

