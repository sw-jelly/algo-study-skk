import java.util.*;
import java.io.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static String str1, str2;
    static int n1, n2;
    static int dp[][];
    
    public static void main(String[] args) throws Exception {
        input();
        solve();
        output(n1, n2);
        System.out.println(sb.reverse());
    }

    static void output(int n1, int n2){

        if(n1==0 || n2==0){
            return;
        }

        char c1 = str1.charAt(n1-1);
        char c2 = str2.charAt(n2-1);

        if(c1==c2){
            sb.append(c1);
            output(n1-1, n2-1);
            return;
        }
        
        if(dp[n1-1][n2] > dp[n1][n2-1]){
            output(n1-1, n2);
            return;
        }
        
        output(n1, n2-1);
    }
    
    static void solve(){
        for(int i=1; i<n1+1; ++i){
            char c1 = str1.charAt(i-1);
            
            for(int j=1; j<n2+1; ++j){
                char c2 = str2.charAt(j-1);

                if(c1==c2){
                    dp[i][j] = dp[i-1][j-1]+1;
                    continue;
                }

                dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
            }
        }
        
        System.out.println(dp[n1][n2]);
    }

    static void input() throws Exception{
        str1 = br.readLine();
        str2 = br.readLine();

        n1 = str1.length();
        n2 = str2.length();

        dp = new int[n1+1][n2+1];
    }

}
