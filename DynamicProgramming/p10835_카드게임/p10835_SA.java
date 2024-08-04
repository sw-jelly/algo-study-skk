import java.util.*;
import java.io.*;

public class p10835_SA {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;

    static int[] left, right;
    static int[][] dp;

    public static void main(String[] args) throws Exception {
        input();
        solve();
    }

    static void input() throws Exception{
        N = Integer.parseInt(br.readLine());
        left = new int[N+1];
        right = new int[N+1];
        dp = new int[N+1][N+1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=N; i>0; --i){
            left[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for(int i=N; i>0; --i){
            right[i] = Integer.parseInt(st.nextToken());
        }
    }

    static void solve(){

        for(int i=0; i<=N; ++i){
            for(int j=0; j<=N; ++j){
                dp[i][j] = -1;
            }
        }

        System.out.println(go(N, N));
        return;
    }

    // dp[l][r]: l, r 만큼 카드가 남았을 때의 점수
    static int go(int l, int r){
        if(l==0 || r==0){
            return 0;
        }

        if(dp[l][r] != -1){
            return dp[l][r];
        }

        dp[l][r] = 0;
        // 1. 왼쪽 카드만 통에 버릴 수도 있고, 둘다 버릴 수 있음 (점수 0점)
        dp[l][r] = Math.max(dp[l][r], go(l-1,r));
        dp[l][r] = Math.max(dp[l][r], go(l-1,r-1));

        // 2. 오른쪽 < 왼쪽카드, 오른쪽카드만 버림 (점수 오른쪽카드)
        if(right[r] < left[l]){
            dp[l][r] = Math.max(dp[l][r], go(l,r-1)+right[r]);
        }

        return dp[l][r];
    }
}
