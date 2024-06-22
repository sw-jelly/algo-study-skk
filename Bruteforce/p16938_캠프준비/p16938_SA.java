import java.util.*;
import java.io.*;

public class p16938_SA {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, L, R, X;
    static int[] levels;
    static int ans;

    public static void main(String[] args) throws Exception{
        input();
        solve();
    }

    static void solve() throws Exception{

        go(0, 0, -1, -1, 0);
        System.out.println(ans);

    }

    static void go(int idx, int sum, int hard, int easy, int cnt){
        if(idx == N){
            if(cnt < 2){
                return;
            }

            if(sum < L){
                return;
            }

            if(sum > R){
                return;
            }

            if(Math.abs(levels[hard] - levels[easy]) < X){
                return;
            }

            ++ans;
            return;
        }

        // No selected
        go(idx+1, sum, hard, easy, cnt);

        // selected
        int h = idx;
        int e = idx;
        if(hard > -1 && levels[hard] > levels[h]){
            h = hard;
        }

        if(easy > -1 && levels[easy] < levels[e]){
            e = easy;
        }

        go(idx+1, sum+levels[idx], h, e, cnt+1);

    }


    static void input() throws Exception{
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        levels = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; ++i){
            levels[i] = Integer.parseInt(st.nextToken());
        }
    }
}
