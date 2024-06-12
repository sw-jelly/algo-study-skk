package p16437_양구출작전;

import java.io.*;
import java.util.*;

public class p16437_YK {

    static int N;
    static Island[] islands;
    static List<Integer>[] roads;

    static class Island {
        boolean isWolf;
        int cnt;

        public Island(boolean isWolf, int cnt) {
            this.isWolf = isWolf;
            this.cnt = cnt;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        islands = new Island[N];
        roads = new ArrayList[N];

        for (int i = 0; i < N; ++i) {
            roads[i] = new ArrayList<>();
        }

        islands[0] = new Island(false, 0);

        for (int i = 1; i < N; ++i) {
            st = new StringTokenizer(br.readLine());
            char ch = st.nextToken().charAt(0);
            int cnt = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken()) - 1;
            islands[i] = new Island((ch == 'W'), cnt);
            roads[to].add(i);
        }

        System.out.println(dfs(0));
    }

    private static long dfs(int k) {
        long sheeps = islands[k].isWolf ? -islands[k].cnt : islands[k].cnt;

        for (int next : roads[k]) {
            sheeps += dfs(next);
        }

        return sheeps < 0 ? 0 : sheeps;
    }
}
