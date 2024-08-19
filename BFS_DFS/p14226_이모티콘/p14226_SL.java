package p14226_이모티콘;

import java.util.*;
import java.io.*;

public class p14226_SL {

    static class Info implements Comparable<Info> {
        int display, clip, time;

        public Info(int display, int clip, int time) {
            this.display = display;
            this.clip = clip;
            this.time = time;
        }

        @Override
        public int compareTo(Info o) {
            return Integer.compare(this.time, o.time);
        }
    }

    static int S;
    static boolean[][] visited; // display, clip

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        S = Integer.parseInt(br.readLine());
        visited = new boolean[2001][2001];

        // 6
        // 화면:    1->2->4->6
        // 클립보드: 1->2

        PriorityQueue<Info> pq = new PriorityQueue<>();
        pq.add(new Info(1, 0, 0));
        visited[1][0] = true;

        while(!pq.isEmpty()) {
            Info cur = pq.poll();

            if(cur.display == S) {
                System.out.println(cur.time);
                return;
            }

            // 클립보드에 복사
            if(!visited[cur.display][cur.display]) {
                visited[cur.display][cur.display] = true;
                pq.add(new Info(cur.display, cur.display, cur.time+1));
            }

            // 클립보드에서 화면으로 붙여넣기
            if(cur.clip >= 1
                    && cur.display + cur.clip <= 2000
                        && !visited[cur.display + cur.clip][cur.clip]) {
                visited[cur.display + cur.clip][cur.clip] = true;
                pq.add(new Info(cur.display+cur.clip, cur.clip, cur.time+1));
            }

            // 이모티콘 하나 삭제
            if(cur.display > 1 && !visited[cur.display - 1][cur.clip]) {
                visited[cur.display - 1][cur.clip] = true;
                pq.add(new Info(cur.display-1, cur.clip, cur.time+1));
            }
        }
    }
}
