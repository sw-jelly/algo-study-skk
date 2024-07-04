package BinarySearch.p3079_입국심사;

import java.io.*;
import java.util.*;

// 이분탐색
public class p3079_SL {

    static int N;  // 입국 심사대 수
    static long M; // 사람 수
    static long[] time;
    static long max = 0;
    static long min = Long.MAX_VALUE;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Long.parseLong(st.nextToken());
        time = new long[N];
        for (int i = 0; i < N; i++) {
            time[i] = Long.parseLong(br.readLine());
            min = Math.min(min, time[i]);
            max = Math.max(max, time[i]);
        }

        // 0~7, 7~14, 14~21, 21~28
        // 0~10, 10~20

        long left = 0;
        long right = max * M;
        long answer = Long.MAX_VALUE;

        while(left <= right) {
            long mid = (left + right) / 2;

            // mid 시간동안 각 심사대에서 심사할 수 있는 사람 수
            long cnt = 0;
            for(int i=0; i<N; i++) {
                // 모든 사람을 심사할 수 있으면 break
                if (cnt >= M) {
                    break;
                }
                cnt += mid / time[i];
            }

            if (cnt >= M) {
                answer = Math.min(answer, mid);
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        System.out.println(answer);

    }
}
