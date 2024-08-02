package Dijkstra.p16118_달빛여우;

import java.io.*;
import java.util.*;

public class p16118_SL {

    static class Info implements Comparable<Info> {
        int to, weight, cnt;
        public Info(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
        public Info(int to, int weight, int cnt) {
            this.to = to;
            this.weight = weight;
            this.cnt = cnt;
        }
        @Override
        public int compareTo(Info o) {
            return this.weight - o.weight;
        }
    }

    static int N, M;
    static ArrayList<Info>[] list;
    static int[] dist1;
    static int[][] dist2;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        list = new ArrayList[N];
        for(int i=0; i<N; i++) {
            list[i] = new ArrayList<>();
        }
        dist1 = new int[N];
        dist2 = new int[N][2];
        for(int i=0; i<N; i++) {
            dist1[i] = Integer.MAX_VALUE;
            for(int j=0; j<2; j++) {
                dist2[i][j] = Integer.MAX_VALUE;
            }
        }

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken())-1;
            int to = Integer.parseInt(st.nextToken())-1;
            int weight = Integer.parseInt(st.nextToken()) * 2;

            list[from].add(new Info(to, weight));
            list[to].add(new Info(from, weight));
        }

        solve1();
        solve2();

        int cnt = 0;
        for(int i =0 ; i < N ; i++) {
            if(dist1[i] < Math.min(dist2[i][0], dist2[i][1])) {
                cnt++;
            }
        }
        System.out.println(cnt);

    }

    private static void solve1() {

        // 다익스트라
        PriorityQueue<Info> pq = new PriorityQueue<>();
        dist1[0] = 0;
        pq.add(new Info(0, 0));

        while(!pq.isEmpty()) {
            Info cur = pq.poll();

            //
            if(dist1[cur.to] < cur.weight) continue;

            for(int i=0; i<list[cur.to].size(); i++) {
                Info next = list[cur.to].get(i);
                if(dist1[next.to] > dist1[cur.to] + next.weight) {
                    dist1[next.to] = dist1[cur.to] + next.weight;
                    pq.add(new Info(next.to, dist1[next.to]));
                }
            }
        }

    }

    private static void solve2() {

        // 다익스트라
        PriorityQueue<Info> pq = new PriorityQueue<>();
        dist2[0][0] = 0;
        pq.add(new Info(0, 0, 0));

        while (!pq.isEmpty()) {
            Info cur = pq.poll();
            int speed = cur.cnt % 2; // 0 : 느리게, 1 : 빠르게

            if(dist2[cur.to][speed] < cur.weight) continue;

            for (int i = 0; i < list[cur.to].size(); i++) {

                Info next = list[cur.to].get(i);
                int newWeight = 0;
                if(speed == 0) {
                    newWeight = next.weight / 2;
                } else {
                    newWeight = next.weight * 2;
                }

                if (dist2[next.to][1 - speed] > dist2[cur.to][speed] + newWeight) {
                    dist2[next.to][1 - speed] = dist2[cur.to][speed] + newWeight;
                    pq.add(new Info(next.to, dist2[next.to][1 - speed], cur.cnt + 1));
                }

            }
        }
    }

}