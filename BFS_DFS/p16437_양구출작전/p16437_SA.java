package p16437_양구출작전;

import java.util.*;
import java.io.*;

public class p16437_SA {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static Queue<Info> q = new LinkedList<>();
    static int N;

    static int[] wolves;
    static int[] graph;
    static class Info{
        int no;
        int cnt;
        Info(int n, int c){
            this.no = n;
            this.cnt = c;
        }
    }

    public static void main(String[] args) throws Exception {
        input();
        solve();
    }

    static void solve(){
        long cnt = 0;

        while(!q.isEmpty()){
            Info curr = q.poll();

            if(curr.no == 1){
                cnt += curr.cnt;
                continue;
            }

            int next = graph[curr.no];
            if(curr.cnt <= wolves[next]){
                wolves[next] -= curr.cnt;
                continue;
            }

            int newCnt = curr.cnt - wolves[next];
            wolves[next] = 0;
            if(next !=1){
                graph[curr.no] = graph[next];
            }
            q.add(new Info(next, newCnt));
        }


        System.out.println(cnt);
    }

    static void input() throws Exception{
        N = Integer.parseInt(br.readLine());
        graph = new int[N+1];
        wolves = new int[N+1];
        for(int i=1; i<N; ++i){
            StringTokenizer st = new StringTokenizer(br.readLine());

            String sw = st.nextToken();
            int cnt = Integer.parseInt(st.nextToken());
            int next = Integer.parseInt(st.nextToken());

            if(sw.equals("S")){
                Info newInfo = new Info(i+1, cnt);
                q.add(newInfo);
            } else{
                wolves[i+1] = cnt;
            }

            graph[i+1] = next;
        }
    }
}
