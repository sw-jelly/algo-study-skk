import java.util.*;
import java.io.*;

public class Main {
    
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n;
    static final long MOD = ((long)Integer.MAX_VALUE + 1)*2;
    static final int MAX = 100_000_001;
    
    public static void main(String[] args) throws Exception{
        input();
        solve();
    }
    
    static void solve(){
        // 에라토스테네스의 체
        boolean[] nums = new boolean[MAX];
        nums[1] = true;
        int sqrtMAX = (int)Math.sqrt(MAX)+1;
        for(int i=2; i<sqrtMAX; ++i){
            if(nums[i]) continue;
            
            for(int j=i+i; j<MAX; j+=i){
                nums[j] = true;
            }
        }
        
        
        // n이하의 소수의 가장 큰 거듭제곱 곱하기
        long result = 1;
        for(int i=2; i<MAX; ++i){
            if(nums[i]) continue;
            
            long tmp = 1;
            while(tmp*i <= n){
                tmp *= i;
            }
            
            result = (result*tmp)%MOD;
        }
        
        System.out.println(result);
        
    }
    
    static void input() throws Exception{
        n = Integer.parseInt(br.readLine());
    }
}

