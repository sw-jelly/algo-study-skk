package BinarySearch.p1561_놀이공원;

import java.io.*;
import java.util.*;

public class p1561_SL {

    static int N, M;
    static int[] time;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        time = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            time[i] = Integer.parseInt(st.nextToken());
        }

        // 마지막 아이가 타게 되는 놀이기구의 번호 구하기
        if (N <= M) {
            System.out.println(N);
            return;
        }

        long left = 0;
        long right = N * 30L; // 가장 오래 걸리는 시간

        while (left < right) {
            long mid = (left + right) / 2;
            long cnt = M; // 처음 M명은 다 탐

            // mid 시간 동안 탄 아이들의 수
            for (int i = 0; i < M; i++) {
                cnt += mid / time[i];
            }

            // 범위 갱신
            if (cnt >= N) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        // left 시간 동안 탄 아이들의 수 cnt
        long cnt = M;
        for (int i = 0; i < M; i++) {
            cnt += (left - 1) / time[i];
        }

        // left 시간에 탄 아이들 중 N번째 아이가 탄 놀이기구 찾기
        for (int i = 0; i < M; i++) {
            // 새로운 아이 태움
            if (left % time[i] == 0) {
                cnt++;
            }
            if (cnt == N) {
                System.out.println(i + 1);
                return;
            }
        }

    }
}