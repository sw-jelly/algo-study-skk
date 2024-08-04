import java.util.*;
import java.io.*;

public class p18116_SA {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static int[] parent = new int[1_000_001];
    static int[] cnt = new int[1_000_001];
    public static void main(String[] args) throws Exception {
        int N = Integer.parseInt(br.readLine());
        for(int i=0; i<1_000_001; ++i){
            parent[i] = i;
            cnt[i] = 1;
        }

        for(int i=0; i<N; ++i){
            StringTokenizer st = new StringTokenizer(br.readLine());

            if(st.nextToken().equals("I")){
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                union(a,b);
                continue;
            }

            int a = Integer.parseInt(st.nextToken());
            sb.append(cnt[find(a)]).append("\n");

        }
        System.out.print(sb);
    }

    static void union(int a, int b){
        int pa = find(a);
        int pb = find(b);

        if(pa==pb){
            return;
        }

        if(pa <= pb){
            cnt[pa] += cnt[pb];
            parent[parent[pb]] = pa;
        } else{
            cnt[pb] += cnt[pa];
            parent[parent[pa]] = pb;
        }
    }

    static int find(int a){
        if (a == parent[a]) {
            return a;
        }
        return parent[a] = find(parent[a]);
    }
}
