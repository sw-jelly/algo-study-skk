package FloydWarshall.p11404_플로이드;

import java.util.*;
import java.io.*;

public class p11404_SL {

    static int n,m; // n: 도시 개수, m: 버스 개수
    static int[][] map;
    static final int INF = 2_000_000_000;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(br.readLine());
        map = new int[n][n];

        for (int i = 0; i < n; i++) {
            Arrays.fill(map[i], INF);
            map[i][i] = 0; // 자기 자신으로 가는 거리는 0
        }

        for(int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken())-1;
            int to = Integer.parseInt(st.nextToken())-1;
            int weight = Integer.parseInt(st.nextToken());
            map[from][to] = Math.min(map[from][to], weight);
        }

        for(int k=0; k<n; k++) {
            for(int a=0; a<n; a++) {
                for(int b=0; b<n; b++) {
                    if (map[a][k] != INF && map[k][b] != INF) {
                        map[a][b] = Math.min(map[a][b], map[a][k] + map[k][b]);
                    }
                }
            }
        }

        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                if(map[i][j] == INF) {
                    System.out.print(0 + " ");
                } else {
                    System.out.print(map[i][j]+" ");
                }
            }
            System.out.println();
        }

    }
}
