package UnionFind.p18116_로봇조립;

import java.util.*;
import java.io.*;

public class p18116_SL {

    private static int find(int a) {
        if(a == parents[a]) {
            return a;
        }
        return parents[a] = find(parents[a]);
    }

    private static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if(a < b) {
            parents[b] = a;
            count[a] += count[b];
        } else if(a > b) {
            parents[a] = b;
            count[b] += count[a];
        }
    }

    static int[] parents;
    static int[] count;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        parents = new int[1_000_001];
        count = new int[1_000_001];
        for(int i=1; i<=1000000; i++) {
            parents[i] = i;
            count[i] = 1;
        }

        int N = Integer.parseInt(st.nextToken());
        for(int t=0; t<N; t++) {
            st = new StringTokenizer(br.readLine());
            String s = st.nextToken();
            if(s.equals("I")) {
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                union(a, b);
            } else { // Q
                int w = Integer.parseInt(st.nextToken());
                w = find(w);
                sb.append(count[w]).append("\n");
            }
        }

        System.out.println(sb);

    }

}
