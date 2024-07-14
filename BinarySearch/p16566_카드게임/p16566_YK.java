package BinarySearch.p16566_카드게임;

import java.io.*;
import java.util.*;

public class p16566_YK {

    static int N, M, K;
    static int[] minsoo, parents;

    private static void init() {
        parents = new int[M + 1];
        for (int i = 0; i <= M; ++i) {
            parents[i] = i;
        }
    }

    private static int find(int a) {
        if (parents[a] == a) return a;
        return parents[a] = find(parents[a]);
    }

    private static boolean union(int a, int b) {
        int pa = find(a);
        int pb = find(b);

        if (pa == pb) return false;

        parents[pa] = pb;
        return true;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        minsoo = new int[M];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; ++i) {
            minsoo[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(minsoo);
        init();

        st = new StringTokenizer(br.readLine());
        for (int i = 0, chulsoo, idx; i < K; ++i) {
            chulsoo = Integer.parseInt(st.nextToken());
            idx = lowerbound(chulsoo);
            union(idx, idx + 1);
            sb.append(minsoo[idx]).append("\n");
        }

        System.out.println(sb);
        br.close();
    }

    private static int lowerbound(int num) {
        int left = 0, right = M - 1;

        while (left <= right) {
            int mid = (left + right) >> 1;

            if (minsoo[find(mid)] > num) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return find(left);
    }
}
