package Dijkstra.p1753_최단경로;

import java.util.*;
import java.io.*;

public class p1753_SL {

    static class Info implements Comparable<Info> {
        int to, weight;
        public Info(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
        @Override
        public int compareTo(Info o) {
            return this.weight - o.weight;
        }
    }

    static int V, E, K;
    static ArrayList<Info>[] list;
    static int[] dist;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken())-1;
        list = new ArrayList[V];
        dist = new int[V];
        for(int i=0; i<V; i++) {
            list[i] = new ArrayList<>();
        }
        for(int i=0; i<V; i++) {
            dist[i] = Integer.MAX_VALUE;
        }
        for(int i=0; i<E; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken())-1;
            int to = Integer.parseInt(st.nextToken())-1;
            int weight = Integer.parseInt(st.nextToken());
            list[from].add(new Info(to, weight));
        }

        solve(K);

        for(int i=0; i<V; i++) {
            if(dist[i] == Integer.MAX_VALUE) {
                System.out.println("INF");
            } else {
                System.out.println(dist[i]);
            }
        }

    }

    private static void solve(int start) {

        // 다익스트라
        PriorityQueue<Info> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[V];
        dist[start] = 0;
        pq.add(new Info(start, 0));

        while(!pq.isEmpty()) {
            Info cur = pq.poll();

            if(visited[cur.to]) continue;
            visited[cur.to] = true;

            for(int i=0; i<list[cur.to].size(); i++) {
                Info next = list[cur.to].get(i);
                if(dist[next.to] > dist[cur.to] + next.weight) {
                    dist[next.to] = dist[cur.to] + next.weight;
                    pq.add(new Info(next.to, dist[next.to]));
                }
            }
        }

    }

}
