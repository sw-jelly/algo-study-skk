package MST.p1197_최소스패닝트리;

import java.io.*;
import java.util.*;

public class p1197_YK {

    static class Edge implements Comparable<Edge> {
        int from, to, weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.weight, o.weight);
        }
    }

    static void init() {
        parents = new int[V];
        for (int i = 0; i < V; ++i) {
            parents[i] = i;
        }
    }

    static boolean union(int a, int b) {
        int pa = find(a);
        int pb = find(b);

        if (pa == pb) return false;

        parents[pa] = pb;
        return true;
    }

    static int find(int a) {
        if (parents[a] == a) return a;
        return parents[a] = find(parents[a]);
    }

    static int V, E;
    static int[] parents;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        Queue<Edge> pq = new PriorityQueue<>();

        for (int i = 0, from, to, weight; i < E; ++i) {
            st = new StringTokenizer(br.readLine());
            from = Integer.parseInt(st.nextToken()) - 1;
            to = Integer.parseInt(st.nextToken()) - 1;
            weight = Integer.parseInt(st.nextToken());
            pq.add(new Edge(from, to, weight));
        }

        init();

        int result = 0;
        while (!pq.isEmpty()) {
            Edge now = pq.poll();
            if (!union(now.from, now.to)) continue;
            result += now.weight;
        }

        System.out.println(result);
        br.close();
    }
}
