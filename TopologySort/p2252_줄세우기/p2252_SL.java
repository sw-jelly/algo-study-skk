package TopologySort.p2252_줄세우기;

import java.util.*;
import java.io.*;

public class p2252_SL {

    static int N, M;
    static int[] num;
    static ArrayList<Integer>[] list;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        num = new int[N + 1];
        list = new ArrayList[N + 1];
        for(int i=0; i<N+1; i++) {
            list[i] = new ArrayList<>();
        }

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken()); // 작은 애
            int b = Integer.parseInt(st.nextToken()); // 큰 애
            list[s].add(b);
            num[b]++;
        }

        topologySort();

    }

    private static void topologySort() {

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for(int i=1; i<N+1; i++) {
            if(num[i] == 0) {
                pq.add(i);
            }
        }

        while(!pq.isEmpty()) {
            int cur = pq.poll();
            System.out.print(cur + " ");

            for(int i=0; i<list[cur].size(); i++) {
                int next = list[cur].get(i);
                num[next]--;
                if(num[next] == 0) {
                    pq.add(next);
                }
            }
        }

    }

}
