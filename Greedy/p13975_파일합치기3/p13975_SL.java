package Greedy.p13975_파일합치기3;

import java.io.*;
import java.util.*;

public class p13975_SL {

    static int N;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());

        for (int t = 0; t < T; t++) {

            N = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());

            PriorityQueue<Long> pq = new PriorityQueue<>();
            for (int i = 0; i < N; i++) {
                pq.add(Long.parseLong(st.nextToken()));
            }

            long result = 0;

            while (true) {

                if (pq.size() == 1) {
                    System.out.println(result);
                    break;
                }

                long sum = pq.poll() + pq.poll();
                result += sum;
                pq.add(sum);

            }
        }


    }
}
