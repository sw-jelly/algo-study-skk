package Greedy.p1339_단어수학;

import java.io.*;
import java.util.*;

public class p1339_YK {

    static class Number implements Comparable<Number> {
        int alpha, place;

        public Number(int alpha, int place) {
            this.alpha = alpha;
            this.place = place;
        }

        @Override
        public int compareTo(Number o) {
            return -Integer.compare(place, o.place);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        char[][] nums = new char[N][];
        int[] lengths = new int[N];
        int[] places = new int[26];

        for (int i = 0; i < N; ++i) {
            nums[i] = br.readLine().toCharArray();
            lengths[i] = nums[i].length;
        }

        Queue<Number> pq = new PriorityQueue<>();

        for (int i = 0; i < N; ++i) {
            for (int k = lengths[i], idx = 0; k > 0; --k) {
                places[nums[i][idx++] - 'A'] += Math.pow(10, k - 1);
            }
        }

        for (int i = 0; i < 26; ++i) {
            if (places[i] == 0) continue;
            pq.add(new Number(i, places[i]));
        }

        int result = 0;
        int digit = 9;
        while (!pq.isEmpty()) {
            Number num = pq.poll();
            result += places[num.alpha] * digit--;
        }

        System.out.println(result);
        br.close();
    }
}
