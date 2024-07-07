import java.util.*;
import java.io.*;

public class p21758_SA {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static int[] amounts;

    public static void main(String[] args) throws Exception{
        input();
        solve();
    }

    static void solve() throws Exception{

        int max = 0;

        // 1. 벌1 왼쪽, 꿀통 오른쪽 고정
        int[] sumRight = amounts.clone();
        for(int i=1; i<N; ++i){
            sumRight[i] += sumRight[i-1];
        }
        int bee1 = sumRight[N-1] - sumRight[0];

        for(int i=1; i<N-1; ++i){
            int bee2 = sumRight[N-1] - sumRight[i];
            int sum = bee1-(sumRight[i]-sumRight[i-1])+bee2;
            max = Math.max(max, sum);
        }

        // 2. 꿀통 왼쪽, 벌1 오른쪽 고정
        int[] sumLeft = amounts.clone();
        for(int i=N-2; i>-1; --i){
            sumLeft[i] += sumLeft[i+1];
        }
        bee1 = sumLeft[0] - sumLeft[N-1];

        for(int i=N-2; i>0; --i){
            int bee2 = sumLeft[0] - sumLeft[i];
            int sum = bee1-(sumLeft[i]-sumLeft[i+1])+bee2;
            max = Math.max(max, sum);
        }

        // 3. 벌1 왼쪽, 벌2 오른쪽 고정
        for(int i=1; i<N-1; ++i){
            bee1 = sumRight[i] - sumRight[0];
            int bee2 = sumLeft[i] - sumLeft[N-1];
            //System.out.printf("%d %d %d%n", i, bee1, bee2);
            max = Math.max(max, bee1+bee2);
        }

        System.out.println(max);
    }

    static void input() throws Exception{

        N = Integer.parseInt(br.readLine());
        amounts = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; ++i){
            amounts[i] = Integer.parseInt(st.nextToken());
        }

    }
}
