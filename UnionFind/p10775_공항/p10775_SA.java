import java.util.*;
import java.io.*;

public class Main {
    
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int G, P;
    static int[] parents;
    
    public static void main(String[] args) throws Exception{
        input();
        solve();
    }
    
    static void solve() throws Exception{

        for(int i=0; i<P; ++i){
            int curr = Integer.parseInt(br.readLine());
            
            int p = find(curr);
            
            if(p == 0){
                System.out.println(i);
                System.exit(0);
            }
            
            union(p-1, p);            
        }
        System.out.println(P);
    }
    
    static void union(int a, int b){
        parents[b] = a;
    }
    
    static int find(int curr){
        if(parents[curr] == curr){
            return curr;
        }
        return parents[curr] = find(parents[curr]);
    }
    
    static void input() throws Exception{
        G = Integer.parseInt(br.readLine());
        P = Integer.parseInt(br.readLine());
        
        parents = new int[G+1];
        for(int i=0; i<G+1; ++i){
            parents[i] = i;
        }
    }
}

