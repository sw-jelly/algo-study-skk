import java.util.*;
import java.io.*;

public class p10800_SA {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static long[] out;
    static long[] answer;

    static class Ball{
        int idx;
        int color;
        int size;

        Ball(int i, int c, int s){
            this.idx = i;
            this.color = c;
            this.size = s;
        }
    }

    static List<Ball> balls = new ArrayList<>();


    public static void main(String[] args) throws Exception{
        input();
        solve();
    }

    static void solve(){

        int bi = 0;
        long sum = 0;
        balls.sort((b1, b2)->(b1.size - b2.size));

        for(Ball curr : balls){
            // 현재 공보다 작은 사이즈의 공을 꺼냄

            // sum) score 누적합
            // out) 꺼낸 공의 정보
            // out[colorIdx] = colorIdx에 해당하는 score
            while(balls.get(bi).size < curr.size) {
                Ball prev = balls.get(bi);

                sum += prev.size;
                out[prev.color] += prev.size;
                ++bi;
            }

            answer[curr.idx] = sum - out[curr.color];
        }

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<N; ++i){
            sb.append(answer[i]).append("\n");
        }
        System.out.print(sb);
    }

    static void input() throws Exception{
        N = Integer.parseInt(br.readLine());
        out = new long[N+1];
        answer = new long[N];

        for(int i=0; i<N; ++i){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int c = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            balls.add(new Ball(i, c, s));
        }
    }
}
