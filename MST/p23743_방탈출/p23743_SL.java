package MST.p23743_방탈출;

import java.util.*;
import java.io.*;

public class p23743_SL {

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

    static int N, M; // N개의 방, M개의 워프
    static int[] parent;
    static ArrayList<Info> list;

    private static void init() {
        parent = new int[N + 1];
        for(int i=0; i<=N; i++) {
            parent[i] = i;
        }
    }

    private static int find(int a) {
        if(parent[a] == a) return a;
        return parent[a] = find(parent[a]);
    }

    private static boolean union(int a, int b) {
        a = find(a);
        b = find(b);

        if(a == b) {
            return false; // cicle
        }

        parent[a] = b;
        return true;
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        list = new ArrayList<>();

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            list.add(new Info(from, to, weight));
        }

        init();
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            int weight = Integer.parseInt(st.nextToken());
            list.add(new Info(0, i+1, weight));
        }

        Collections.sort(list);

        int result = 0;
        for(int i=0; i<list.size(); i++) {
            Info cur = list.get(i);
            if(union(cur.from, cur.to)) {
                result += cur.weight;
            }
        }

        System.out.println(result);

    }
}