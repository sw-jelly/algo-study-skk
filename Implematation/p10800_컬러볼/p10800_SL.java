package Implematation.p10800_컬러볼;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class p10800_SL {

    static class Ball implements Comparable<Ball> {
        int idx, color, size;
        public Ball(int idx, int color, int size) {
            this.idx = idx;
            this.color = color;
            this.size = size;
        }
        @Override
        public int compareTo(Ball o) {
            return this.size - o.size;
        }
    }
    static int N;
    static Ball[] balls;
    static int[] result;
    static int[] colorSum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        balls = new Ball[N];
        result = new int[N];
        colorSum = new int[N+1];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int color = Integer.parseInt(st.nextToken());
            int size = Integer.parseInt(st.nextToken());
            balls[i] = new Ball(i, color, size);
        }

        Arrays.sort(balls);

        int sum = 0;
        int j = 0;
        for(int i=0; i<N; i++) {
            Ball ball = balls[i];
            while(balls[j].size < ball.size) {
                sum += balls[j].size;
                colorSum[balls[j].color] += balls[j].size;
                j++;
            }
            result[ball.idx] = sum - colorSum[ball.color];
        }

        for (int r : result) {
            sb.append(r).append("\n");
        }

        System.out.println(sb);

    }

}