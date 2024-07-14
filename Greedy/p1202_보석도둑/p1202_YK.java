package Greedy.p1202_보석도둑;

import java.io.*;
import java.util.*;

public class p1202_YK {

    static int N, K;

    static class Jewel {
        int weight, value;

        public Jewel(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        Queue<Jewel> wpq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.weight, o2.weight));
        Queue<Jewel> vpq = new PriorityQueue<>((o1, o2) -> -Integer.compare(o1.value, o2.value));
        Queue<Integer> bags = new PriorityQueue<>();

        for (int i = 0, w, v; i < N; ++i) {
            st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            wpq.add(new Jewel(w, v));
        }

        for (int i = 0; i < K; ++i) {
            bags.add(Integer.parseInt(br.readLine()));
        }

        long result = 0;
        while (!bags.isEmpty()) {
            int bag = bags.poll();

            while (!wpq.isEmpty() && wpq.peek().weight <= bag) {
                vpq.add(wpq.poll());
            }

            if (!vpq.isEmpty()) result += vpq.poll().value;
        }

        System.out.println(result);
        br.close();
    }
}
