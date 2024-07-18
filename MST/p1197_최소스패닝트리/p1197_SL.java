package MST.p1197_최소스패닝트리;

import java.util.*;
import java.io.*;

public class p1197_SL {

    static class Info implements Comparable<Info> {
        int from, to, weight;

        public Info(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Info o) {
            return this.weight - o.weight;
        }
    }

    static int V, E;
    static int[] parents;
    static Info[] infoList;

    private static void init() {
        parents = new int[V + 1];
        for(int i = 0; i <= V; i++) {
            parents[i] = i;
        }
    }

    private static int find(int a) {
        if(parents[a] == a) return a;
        return parents[a] = find(parents[a]);
    }

    private static boolean union(int a, int b) {
        int pa = find(a);
        int pb = find(b);

        if(pa == pb) return false; // 다 이어짐

        parents[pa] = pb;
        return true;
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        infoList = new Info[E];
        init();

        for(int i=0; i<E; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            infoList[i] = new Info(from, to, weight);
        }

        Arrays.sort(infoList); // weight 작은거부터 큰 순서대로

        long result = 0;
        int cnt = 0;
        for(int i=0; i<E; i++) {
            Info cur = infoList[i];
            if(union(cur.from, cur.to)) { // 합칠 수 있을 때
                result += cur.weight;
                if(++cnt == V-1) break;
            }
        }

        System.out.println(result);

    }
}
