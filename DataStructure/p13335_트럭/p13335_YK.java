package DataStructure.p13335_트럭;

import java.io.*;
import java.util.*;

public class p13335_YK {

    static int N, W, L;

    static class Truck {
        int weight, time;

        public Truck(int weight, int time) {
            this.weight = weight;
            this.time = time;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        int[] input = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; ++i) {
            input[i] = Integer.parseInt(st.nextToken());
        }

        Queue<Truck> q = new ArrayDeque<>();
        int time = 1;
        q.add(new Truck(input[0], time));
        int weight = input[0];
        int nextTruckIdx = 1;

        while (!q.isEmpty()) {
            ++time;

            // 맨 앞 트럭이 나온다.
            if (time >= q.peek().time + L) {
                weight -= q.poll().weight;
            }

            if (nextTruckIdx >= N) continue;
            if (q.size() >= L) continue;
            if (weight + input[nextTruckIdx] > W) continue;

            weight += input[nextTruckIdx];
            q.add(new Truck(input[nextTruckIdx++], time));
        }

        System.out.println(time);
    }
}
