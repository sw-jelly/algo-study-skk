package FloydWarshall.p11404_플로이드;

import java.io.*;
import java.util.*;

public class p11404_YK {

    static int N, M, INF = Integer.MAX_VALUE >> 2;
    static int[][] routes;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        routes = new int[N][N];
        for (int i = 0; i < N; ++i) {
            Arrays.fill(routes[i], INF);
        }

        for (int i = 0, from, to, weight; i < M; ++i) {
            st = new StringTokenizer(br.readLine());
            from = Integer.parseInt(st.nextToken()) - 1;
            to = Integer.parseInt(st.nextToken()) - 1;
            weight = Integer.parseInt(st.nextToken());
            routes[from][to] = Math.min(routes[from][to], weight);
        }

        // 경출도
        for (int k = 0; k < N; ++k) {
            for (int i = 0; i < N; ++i) {
                for (int j = 0; j < N; ++j) {
                    if (i == j) continue;
                    routes[i][j] = Math.min(routes[i][j], routes[i][k] + routes[k][j]);
                }
            }
        }

        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                sb.append(routes[i][j] == INF ? 0 : routes[i][j]).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
        br.close();
    }
}
