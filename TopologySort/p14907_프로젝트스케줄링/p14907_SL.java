package TopologySort.p14907_프로젝트스케줄링;

import java.util.*;
import java.io.*;

public class p14907_SL {

    static class Info implements Comparable<Info> {
        int n, time;
        public Info(int n, int time) {
            this.n = n;
            this.time = time;
        }
        @Override
        public int compareTo(Info o) {
            return this.time - o.time;
        }
    }

    static int[] num;
    static int[] times;
    static int[] finishTimes;
    static ArrayList<Integer>[] list;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        num = new int[26];
        times = new int[26];
        finishTimes = new int[26];
        list = new ArrayList[26];
        for(int i=0; i<26; i++) {
            list[i] = new ArrayList<>();
        }
        String line;

        // input
        while((line = br.readLine()) != null && !line.isEmpty()) {
            st = new StringTokenizer(line);

            int now = st.nextToken().charAt(0) - 'A';
            int time = Integer.parseInt(st.nextToken());
            times[now] = time;

            if(st.hasMoreTokens()) {
                String froms = st.nextToken();
                for(int i=0; i<froms.length(); i++) {
                    int from = froms.charAt(i) - 'A';
                    num[from]++; // 선행한거 넣어주기
                    list[now].add(from); // now가 from보다 앞에 있다.
                }
            }
        }

        topology();

    }

    private static void topology() {

        PriorityQueue<Info> pq = new PriorityQueue<>();

        for(int i=0; i<26; i++) {
            if(num[i] == 0 && times[i] > 0) {
                pq.add(new Info(i, times[i]));
                finishTimes[i] = times[i];
            }
        }

        int totalTime = 0;
        while(!pq.isEmpty()) {
            Info cur = pq.poll();
            totalTime = Math.max(totalTime, cur.time);

            for(int i=0; i<list[cur.n].size(); i++) {
                int nextIdx = list[cur.n].get(i);
                num[nextIdx]--;

                if(num[nextIdx] == 0) {
                    pq.add(new Info(nextIdx, cur.time + times[nextIdx]));
                    finishTimes[nextIdx] = cur.time + times[nextIdx];
                }
            }
        }

        System.out.println(totalTime);

    }


}