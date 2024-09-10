package Dijkstra.p2307_도로검문;

import java.util.*;
import java.io.*;

// https://gomster96.github.io/PS/%EB%8B%A4%EC%9D%B5%EC%8A%A4%ED%8A%B8%EB%9D%BC/2307/
public class p2307_SL {

    static class Info implements Comparable<Info> {
        int to, weight;
        public Info (int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
        @Override
        public int compareTo(Info o) {
            return Integer.compare(this.weight, o.weight);
        }
    }

    static int N,M;
    static ArrayList<Info>[] list;
    static int[] dist, path;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        list = new ArrayList[N];
        for(int i=0; i<N; i++) {
            list[i] = new ArrayList<>();
        }
        dist = new int[N];
        path = new int[N];
        Arrays.fill(path, -1);
        for(int i=0; i<N; i++) {
            dist[i] = Integer.MAX_VALUE;
        }
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken())-1;
            int b = Integer.parseInt(st.nextToken())-1;
            int w = Integer.parseInt(st.nextToken());
            list[a].add(new Info(b, w));
            list[b].add(new Info(a, w));
        }

        solve();
        int origin = dist[N-1];
//        System.out.println(Arrays.toString(path));
        int maxPath = 0;

        // path 대로 순차적으로 이동할 수 있도록 하는 for
        // [next정점이 INdex] 그 value 는 current 정점
        for(int i=N-1; i>=0; i=path[i]) {
            maxPath = Math.max(maxPath, otherPath(path[i], i));
        }

        if(maxPath == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(maxPath - origin);
        }

    }

    static int otherPath(int from, int to) {

        PriorityQueue<Info> pq = new PriorityQueue<>();
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[0] = 0;
        pq.add(new Info(0,0));

        while(!pq.isEmpty()) {
            Info cur = pq.poll();

            if(dist[cur.to] < cur.weight ) continue;

            for(Info next : list[cur.to]) {
                if(cur.to == from && next.to == to) continue; // 제거된 간선은 무시

                if(dist[next.to] > dist[cur.to] + next.weight) {
                    dist[next.to] = dist[cur.to] + next.weight;
                    pq.add(new Info(next.to, dist[next.to]));
                }
            }
        }
        return dist[N-1];
    }


    private static void solve() {
        PriorityQueue<Info> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[N];

        pq.add(new Info(0, 0));
        dist[0] = 0;

        while(!pq.isEmpty()) {
            Info cur = pq.poll();
            if(visited[cur.to]) continue;
            visited[cur.to] = true;

            for(int i=0; i<list[cur.to].size(); i++) {
                int nextTo = list[cur.to].get(i).to;
                int nextWeight = list[cur.to].get(i).weight;
                if(dist[nextTo] > dist[cur.to] + nextWeight) {
                    dist[nextTo] = dist[cur.to] + nextWeight;
                    pq.add(new Info(nextTo, dist[nextTo]));
                    path[nextTo] = cur.to;
                }
            }
        }
    }


}
