package Greedy.p1202_보석도둑;

import java.io.*;
import java.util.*;

public class p1202_SL {

    static class Bosuk implements Comparable<Bosuk> {
        int weight, value;

        public Bosuk(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }

        @Override
        public int compareTo(Bosuk o) {
            return this.weight - o.weight;
        }
    }

    static int N, K;
    static long result;
    static int[] bags;
    static Bosuk[] bosuks;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        bags = new int[K];
        bosuks = new Bosuk[N];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            bosuks[i] = new Bosuk(w, v);
        }

        for(int i = 0; i < K; i++) {
            bags[i] = Integer.parseInt(br.readLine());
        }

        // 상덕이가 훔칠 수 있는 보석의 최대 가격 구하기
        // 보석과 가방을 무게 순으로 정렬
        Arrays.sort(bosuks);
        Arrays.sort(bags);

        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        int idx = 0;
        // 각 가방별로, 가방에 들어갈 수 있는 보석들 중 가장 가치가 높은 보석을 pq에 넣어서 하나씩 빼기
        for(int i = 0; i < K; i++) {
            // 가방에 들어갈 수 있는 보석들을 pq에 넣기
            while(idx < N && bosuks[idx].weight <= bags[i]) {
                pq.add(bosuks[idx].value);
                idx++;
            }
            if(!pq.isEmpty()) {
                result += pq.poll();
            }
        }

        System.out.println(result);

    }
}
