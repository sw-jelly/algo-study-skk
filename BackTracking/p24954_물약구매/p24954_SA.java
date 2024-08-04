import java.util.*;
import java.io.*;

public class p24954_SA {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static int[] coins;
    static List<Discount>[] discountInfo;

    static class Discount{
        int obj;
        int discount;

        Discount(int obj, int discount){
            this.obj = obj;
            this.discount = discount;
        }
    }

    public static void main(String[] args) throws Exception {
        input();
        solve();
    }

    static int ans = Integer.MAX_VALUE / 2;
    static void solve(){
        go(0, 0, 0);
        System.out.println(ans);
    }

    static void go(int cnt, int visited, int cost){

        if(cnt == N){
            ans = Math.min(cost, ans);
            return;
        }

        for(int i=0; i<N; ++i){
            if((visited & (1<<i))==0){
                // cost 계산
                // 할인정보 반영
                cost += coins[i];
                int[] copied = Arrays.copyOf(coins, coins.length);
                for(Discount d : discountInfo[i]){
                    coins[d.obj] = Math.max(1, coins[d.obj]-d.discount);
                }
                go(cnt+1, visited | (1<<i), cost);
                // 할인복구
                // cost 복구
                coins = copied;
                cost -= coins[i];
            }
        }

    }

    static void input() throws Exception{

        N = Integer.parseInt(br.readLine());
        coins = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; ++i){
            coins[i] = Integer.parseInt(st.nextToken());
        }

        discountInfo = new List[N];
        for(int i=0; i<N; ++i){

            discountInfo[i] = new ArrayList<>();
            int size = Integer.parseInt(br.readLine());

            for(int s=0; s<size; ++s){
                st = new StringTokenizer(br.readLine());

                int obj = Integer.parseInt(st.nextToken())-1;
                int cost = Integer.parseInt(st.nextToken());

                discountInfo[i].add(new Discount(obj, cost));
            }
        }
    }
}
