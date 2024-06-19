package Implematation.p10800_컬러볼;

import java.io.*;
import java.util.*;

public class p10800_YK {

    static class Ball implements Comparable<Ball> {
        int idx, color, size;

        public Ball(int idx, int color, int size) {
            this.idx = idx;
            this.color = color;
            this.size = size;
        }

        @Override
        public int compareTo(Ball o) {
            return -Integer.compare(this.size, o.size);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int[] result = new int[N];
        int[] colors = new int[N];
        Queue<Ball> pq = new PriorityQueue<>();
        int totalSize = 0;

        for (int i = 0, color, size; i < N; ++i) {
            st = new StringTokenizer(br.readLine());
            color = Integer.parseInt(st.nextToken()) - 1;
            size = Integer.parseInt(st.nextToken());

            pq.add(new Ball(i, color, size));
            colors[color] += size;
            totalSize += size;
        }

        Queue<Ball> q = new ArrayDeque<>();
        while (!pq.isEmpty()) {
            int size = pq.peek().size;

            while (!pq.isEmpty() && pq.peek().size == size) {
                Ball ball = pq.poll();
                colors[ball.color] -= ball.size;
                totalSize -= ball.size;
                q.add(ball);
            }

            while (!q.isEmpty()) {
                Ball ball = q.poll();
                result[ball.idx] = totalSize - colors[ball.color];
            }
        }

        for (int r : result) {
            sb.append(r).append("\n");
        }

        System.out.println(sb);
        br.close();
    }
}
