package Implematation.p31937_로그프레소마에스트로;

import java.util.*;
import java.io.*;

public class p31937_SL {

    static class Log implements Comparable<Log> {
        int t, a, b;

        public Log(int t, int a, int b) {
            this.t = t;
            this.a = a;
            this.b = b;
        }

        @Override
        public int compareTo(Log o) {
            return this.t - o.t;
        }
    }

    static int N, M, K;
    static boolean[] checked;
    static ArrayList<Log> logs = new ArrayList<>();
    static List<Integer> infected = new ArrayList<>();

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 컴퓨터의 개수
        M = Integer.parseInt(st.nextToken()); // 파일 전송 로그 개수
        K = Integer.parseInt(st.nextToken()); // 감염된 컴퓨터 개수

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<K; i++) {
            infected.add(Integer.parseInt(st.nextToken()));
        }

        if(K == 0) {
            System.out.println(infected.get(0));
            return;
        }

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            logs.add(new Log(t, a, b));
        }

        Collections.sort(logs);

        checked = new boolean[N + 1];
        for (int i = 0; i < infected.size(); i++) {
            checked[infected.get(i)] = true;
        }


        for(int i=0; i<infected.size(); i++) {

            int infectedComputer = infected.get(i);
            boolean[] visited = new boolean[N + 1];
            visited[infectedComputer] = true;
            int cnt = 1;

            for(int j=0; j<logs.size(); j++) {
                Log log = logs.get(j);
                if(visited[log.a] && !checked[log.b]) {
                    break;
                }
                if(checked[log.a] && checked[log.b] && visited[log.a] && !visited[log.b]) {
                    visited[log.b] = true;
                    cnt++;
                }
            }

            if(cnt == K) {
                System.out.println(infectedComputer);
                return;
            }
        }

    }

}
