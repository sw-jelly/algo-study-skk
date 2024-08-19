import java.util.*;
import java.io.*;

public class Main {
    
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, K;
    static int[] heights;
    public static void main(String[] args) throws Exception{
        input();
        solve();
    }
    
    static void input() throws Exception{
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
    
        heights = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; ++i){
            heights[i] = Integer.parseInt(st.nextToken());
        }
    }
    
    static void solve() {

        List<Integer> diff = new ArrayList<>();    
        for(int i=1; i<N; ++i){
            diff.add(heights[i]-heights[i-1]);
        }
       
        Collections.sort(diff);
        
        int cost = 0;
        for (int i=0; i<N-K; i++) {
            cost += diff.get(i);
        }
        
        System.out.println(cost);
        return;
    }    
}

