package BinarySearch.p1561_놀이공원;

import java.io.*;
import java.util.*;

public class p1561_YK {

    static int N, M;
    static int[] rides;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        rides = new int[M];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; ++i) {
            rides[i] = Integer.parseInt(st.nextToken());
        }

        if (N <= M) { // 사람이 놀이기구 수보다 적을 때
            System.out.println(N);
            return;
        }

        System.out.println(findLastRide(lowerbound()));
        br.close();
    }

    private static long lowerbound() {
        long left = 0, right = 30L * N;

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
        long cnt = M;

        for (int i = 0; i < M; ++i) {
            cnt += time / rides[i];
            if (cnt >= N) return true;
        }

        return false;
    }

    private static int findLastRide(long time) {
        long cnt = M;

        // 직전 시간까지 탄 사람 수
        for (int i = 0; i < M; ++i) {
            cnt += (time - 1) / rides[i];
        }

        for (int i = 0; i < M; ++i) {
            if (time % rides[i] == 0) ++cnt; // 비어 있는 놀이기구에 태우기
            if (cnt == N) return i + 1;
        }

        return -1;
    }
}
