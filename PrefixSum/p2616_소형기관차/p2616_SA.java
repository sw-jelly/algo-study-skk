import java.util.*;
import java.io.*;

public class p2616_SA {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int N, M;
    static int[] arr;
    static int[] sum;
    static int[][] dp;

    public static void main(String[] args) throws Exception{
        input();
        solve();
    }

    static void input() throws Exception{
        N = Integer.parseInt(br.readLine());
        arr = new int[N+1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; ++i){
            arr[i+1] = Integer.parseInt(st.nextToken());
        }

        M = Integer.parseInt(br.readLine());
    }

    static void solve(){
        // 소형기관차 3대를 이용해서 최대한 많은 손님을 태우도록
        // 소형기관차가 끌 수 있는 객차의 수는 정해져있음 (M)
        // 소형기관차는 번호가 연속적으로 이어진 객차를 끌게 함

        // 구간합
        sum = new int[N+1];
        sum[1] = arr[1];
        for(int i=2; i<N+1; ++i){
            sum[i] = sum[i-1] + arr[i];
        }

        // dp[i][j]: 객차i까지 확인했을 때, 소형기관차 j개를 사용해서 옮길 수 있는 최대의 손님 수
        dp = new int[N+1][4];
        for(int j=1; j<=3; ++j){
            for(int i=j*M; i<=N; ++i){
                dp[i][j] = Math.max(dp[i-1][j], dp[i-M][j-1]+(sum[i]-sum[i-M]));
            }
        }
        System.out.println(dp[N][3]);
    }
}
