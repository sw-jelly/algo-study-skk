package Greedy.p1911_흙길보수하기;

import java.util.*;
import java.io.*;

public class p1911_SL {

    static class Info implements Comparable<Info> {
        int from, to;
        public Info(int from, int to) {
            this.from = from;
            this.to = to;
        }
        @Override
        public int compareTo(Info o) {
            return Integer.compare(this.from, o.from);
        }
    }

    static int N, L;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // N개의 물웅덩이.
        // 길이가 L인 널빤지 갖고있음.
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        PriorityQueue<Info> pq = new PriorityQueue<>();

        // N개의 줄에 물웅덩이 위치와 크기.
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken())-1;
            pq.add(new Info(from, to));
        }

        int cnt = 0;
        // 1,5   8,11   13,16
        // 1,6 .. 8,13     14,16
        int updatedFrom = 0;
        int lastTo = 0;

        while(!pq.isEmpty()) {
            Info cur = pq.poll();
            int curFrom = cur.from; // 1
            updatedFrom = curFrom;
            int curTo = cur.to; // 5

            if(curTo <= lastTo) continue;

            int len = curTo - curFrom + 1; // 5
            if(curFrom <= lastTo) {
                updatedFrom = lastTo + 1;
                len = curTo - updatedFrom + 1;
            }

            int afterLen = len; // 5
            if(len%L > 0) {
                afterLen = ((len/L)+1)*L; // 6
            }

            lastTo = updatedFrom + afterLen - 1;
            cnt += afterLen/L;
        }

        System.out.println(cnt);

    }
}

/*
3 100000
1 50
51 55
70 80

1
* */