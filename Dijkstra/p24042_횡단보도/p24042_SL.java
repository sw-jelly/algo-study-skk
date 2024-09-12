package Dijkstra.p24042_횡단보도;

import java.io.*;
import java.util.*;

public class p24042_SL {

    static class Info implements Comparable<Info> {
        int loc;
        long time;
        public Info(int loc, long time) {
            this.loc = loc;
            this.time = time;
        }
        @Override
        public int compareTo(Info o) {
            return Long.compare(this.time, o.time);
        }
    }

    static int N, M;
    static ArrayList<Info>[] list;
    static long[] minTime;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 지역의 수
        M = Integer.parseInt(st.nextToken()); // 횡단보도의 주기
        list = new ArrayList[N+1];
        for(int i=0; i<N+1; i++) {
            list[i] = new ArrayList<>();
        }
        minTime = new long[N+1];
        Arrays.fill(minTime, Long.MAX_VALUE);

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            // 1분동안 파란불이 들어오는 횡단보도의 두 끝점 a, b
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            list[a].add(new Info(b, i));
            list[b].add(new Info(a, i));
        }

        solve();
        System.out.println(minTime[N]);

    }

    private static void solve() {

        PriorityQueue<Info> pq = new PriorityQueue<>();
        pq.add(new Info(1, 0));
        minTime[1] = 0;

        while(!pq.isEmpty()) {
            Info cur = pq.poll();
            int curLoc = cur.loc;
            long curTime = cur.time;

            // 이미 더 짧은 시간에 도달한 경우 건너뜀
            if(minTime[curLoc] < curTime) continue;

            // 현재 있는 곳에서 갈 수 있는 루트들과 몇분에 갈 수 있는지 확인
            for(int i=0; i<list[curLoc].size(); i++) {
                Info next = list[curLoc].get(i);
                int nextLoc = next.loc; // 어디로
                long nextTime; // 몇분에

                // 현재 시간이 횡단보도 파란불이 켜지는 시간 이전일 경우
                if (curTime <= next.time) {
                    nextTime = next.time + 1;
                } else {
                    // 현재 시간이 파란불 켜지는 시간을 지난 경우
                    // 현재 시간을 기준으로 다음 파란불 시간에 도달하기 위해 대기하는 시간 계산
                    nextTime = ((long) Math.ceil(((double)cur.time - next.time)/M)) * M + next.time + 1;
                }

                if (nextTime < minTime[nextLoc]) {
                    minTime[nextLoc] = nextTime;
                    pq.add(new Info(nextLoc, nextTime));
                }

            }


        }


    }

}

/*
    ex)
    현재 시간(cur.time)이 7분이고,
    다음 교차로에서 파란불이 켜지는 시간(next.time)이 3분,
    횡단보도의 주기(M)가 5분일 때.

    cur.time - next.time = 7 - 3 = 4 (현재 시간에서 다음 파란불 시간까지의 차이)

    4 / 5 = 0.8 (주기 M에 따른 시간 비율)

    Math.ceil(0.8) = 1 (올림하여 다음 파란불 시간까지의 남은 주기 계산)

    1 * 5 = 5 (주기 M을 곱하여 다음 파란불이 켜지는 시간까지의 남은 시간 계산)

    5 + 3 + 1 = 9 (실제 파란불이 켜지는 시간에 도달한 후 1분 후의 시간)

    => nextTime은 9, 9분 후에 해당 교차로에 도달할 수 있다

* */