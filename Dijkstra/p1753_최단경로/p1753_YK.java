package Dijkstra.p1753_최단경로;

import java.io.*;
import java.util.*;

public class p1753_YK {
    static class Node implements Comparable<Node> {
        int v, w;

        public Node(int v, int w) {
            this.v = v;
            this.w = w;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.w, o.w);
        }
    }

    static int V, E;
    static List<Node>[] list;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        list = new List[V];
        for (int i = 0; i < V; ++i) {
            list[i] = new ArrayList<>();
        }

        int start = Integer.parseInt(br.readLine()) - 1;

        for (int i = 0, from, to, weight; i < E; ++i) {
            st = new StringTokenizer(br.readLine());
            from = Integer.parseInt(st.nextToken()) - 1;
            to = Integer.parseInt(st.nextToken()) - 1;
            weight = Integer.parseInt(st.nextToken());
            list[from].add(new Node(to, weight));
        }

        int[] result = dijkstra(start);
        for (int r : result) {
            sb.append(r == Integer.MAX_VALUE ? "INF" : r).append("\n");
        }

        System.out.println(sb);
        br.close();
    }

    private static int[] dijkstra(int start) {
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        Queue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node now = pq.poll();

            if (dist[now.v] < now.w) continue;

            for (Node next : list[now.v]) {
                if (dist[next.v] > dist[now.v] + next.w) {
                    dist[next.v] = dist[now.v] + next.w;
                    pq.add(new Node(next.v, dist[next.v]));
                }
            }
        }

        return dist;
    }
}
