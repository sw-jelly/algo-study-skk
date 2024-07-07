package BinarySearch.p3079_입국심사;

import java.io.*;
import java.util.*;

public class p3079_YK {

    static int N, M;
    static int[] desks;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        desks = new int[N];
        for (int i = 0; i < N; ++i) {
            desks[i] = Integer.parseInt(br.readLine());
        }

        System.out.println(lowerbound());
        br.close();
    }

    private static long lowerbound() {
        long left = 0, right = Long.MAX_VALUE;

        while (left <= right) {
            long mid = (left + right) >> 1;
            if (check(mid)) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    private static boolean check(long time) {
        long cnt = 0;
        for (int i = 0; i < N; ++i) {
            cnt += time / desks[i];
            if (cnt >= M) return true;
        }
        return false;
    }
}
