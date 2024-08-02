package FloydWarshall.p1956_운동;

import java.io.*;
import java.util.*;

public class p1956_SL {

    static int V; // V개의 마을
    static int E; // E개의 도로
    static int[][] dist;
    static final int INF = 2_000_000_000;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 사이클을 이루는 도로의 길이의 합이 최소.
        // 두 마을을 왕복하는 것도 사이클.
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        dist = new int[V][V];

        for(int i=0; i<V; i++) {
            for(int j=0; j<V; j++) {
                if(i == j) {
                    dist[i][j] = 0;
                } else {
                    dist[i][j] = INF;
                }
            }
        }

        for(int i=0; i<E; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken())-1;
            int to = Integer.parseInt(st.nextToken())-1;
            int weight = Integer.parseInt(st.nextToken());

            dist[from][to] = weight;
        }

        // 플로이드워셜
        for(int k=0; k<V; k++) {
            for(int i=0; i<V; i++) {
                for(int j=0; j<V; j++) {
                    if (dist[i][k] != INF && dist[k][j] != INF) {
                        dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                    }
                }
            }
        }

        int answer = INF;
        for(int i=0; i<V; i++) {
            for(int j=0; j<V; j++) {
                if(i != j && dist[i][j] != INF && dist[j][i] != INF) {
                    answer = Math.min(answer, dist[i][j] + dist[j][i]);
                }
            }
        }

        if(answer == INF) {
            System.out.println(-1);
        } else {
            System.out.println(answer);
        }
    }
}
