package TopologySort.p2252_줄세우기;

import java.io.*;
import java.util.*;

public class p2252_YK {

    static int N, M;
    static int[] indegree;
    static List<Integer>[] list;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        indegree = new int[N + 1];
        list = new List[N + 1];
        for (int i = 0; i <= N; ++i) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0, a, b; i < M; ++i) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            ++indegree[b];
            list[a].add(b);
        }

        List<Integer> result = topologySort();
        for (int r : result) {
            sb.append(r).append(" ");
        }

        System.out.println(sb);
        br.close();
    }

    private static List<Integer> topologySort() {
        List<Integer> result = new ArrayList<>();
        Queue<Integer> q = new ArrayDeque<>();

        for (int i = 1; i <= N; ++i) {
            if (indegree[i] == 0) q.add(i);
        }

        while (!q.isEmpty()) {
            int now = q.poll();
            result.add(now);

            for (int next : list[now]) {
                if (--indegree[next] == 0) q.add(next);
            }
        }

        return result;
    }
}
