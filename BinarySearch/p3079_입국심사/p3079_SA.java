import java.util.*;
import java.io.*;

public class p3079_SA {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, M;
    static long[] checkpoints;

    public static void main(String[] args) throws Exception{
        input();
        solve();
    }

    static void solve(){

        Arrays.sort(checkpoints);

        // 입국심사하는데 걸리는 최소시간을 찾는 이분탐색
        long lo = 0;
        long hi = M * checkpoints[N-1];
        long ans = hi;

        while(lo <= hi){
            long mid = (lo+hi) / 2;
            long sum = 0; // 인원수

            for(int i=0; i<N; ++i){
                // 입국심사대 당 받을 수 있는 인원 수
                long cnt =  mid/checkpoints[i];
                sum += cnt;
                if(sum >= M){
                    break;
                }
            }

            if(sum >= M){
                hi = mid-1;
                ans = Math.min(ans, mid);
                continue;
            } else{
                lo = mid+1;
                continue;
            }

        }

        System.out.println(ans);


    }

    static void input() throws Exception{
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        checkpoints = new long[N];
        for(int i=0; i<N; ++i){
            checkpoints[i] = Long.parseLong(br.readLine());
        }
    }
}
