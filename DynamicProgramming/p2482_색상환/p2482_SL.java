package DynamicProgramming.p2482_색상환;
import java.util.*;
import java.io.*;

public class p2482_SL {

    static int N, M;
    static int[][] dp;
    static final int mod = 1_000_000_003;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        dp = new int[N + 1][N + 1]; // n개 중에서 n개 선택하는 방법

        for(int i=1; i<=N; i++) {
            dp[i][1] = i;
            dp[i][0] = 1;
        }

        for(int i=3; i<=N; i++) { // i가 2까지일때는 이미 초기화 위에 시켜놓음
            for(int j=2; j<=(i+1)/2; j++) { // n개 중에서 n/2보다 더 많이 고르는 경우는 0가지니까
                         // i번째 선택 x + i번째 선택 o
                dp[i][j] = (dp[i-1][j] + dp[i-2][j-1]) % mod;
            }
        }

        // 마지막 선택(N-3인 이유 : 마지막 빼고, 마지막 바로 전 빼고, 1 빼고) + 마지막 선택 x
        System.out.println((dp[N - 3][M - 1] + dp[N - 1][M]) % mod);

    }

}
