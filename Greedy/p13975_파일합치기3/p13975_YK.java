package Greedy.p13975_파일합치기3;

import java.io.*;
import java.util.*;

public class p13975_YK {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        Queue<Long> pq = new PriorityQueue<>();
        int K;
        long sum, result;
        while (T-- > 0) {
            K = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < K; ++i) {
                pq.add(Long.parseLong(st.nextToken()));
            }

            result = 0;
            while (pq.size() > 1) {
                sum = pq.poll() + pq.poll();
                result += sum;
                pq.add(sum);
            }

            pq.poll();
            sb.append(result).append("\n");
        }

        System.out.println(sb);
        br.close();
    }
}
