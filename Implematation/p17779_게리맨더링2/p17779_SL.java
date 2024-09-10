package Implematation.p17779_게리맨더링2;

import java.io.*;
import java.util.*;

public class p17779_SL {

    static int N;
    static int total = 0;
    static int[][] people;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        people = new int[N][N];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                people[i][j] = Integer.parseInt(st.nextToken());
                total += people[i][j];          // 5구역 구할 때, (전체 인구수 - 나머지 구역의 인구수)
            }
        }

        // 기준점 : i는 x, j는 y
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if((i==0 && j==0) || (i==0 && j==N-1) || (i==N-1 && j==0) || (i==N-1 && j==N-1)) continue;

                for(int d1=1; d1<N; d1++) {
                    for(int d2=1; d2<N; d2++) {

                        if(i + d1 + d2 >= N) continue;
                        if(j - d1 < 0 || j + d2 >= N) continue;
                        solve(i, j, d1, d2);
                    }
                }
            }
        }

        System.out.println(min);

    }

    static void solve(int x, int y, int d1, int d2) {
        boolean[][] border = new boolean[N][N];
        int[] peopleSum = new int[5];

        // 왼쪽아래로 그리기
        for(int i=0; i<=d1; i++) {
            border[x+i][y-i] = true;
            border[x+d2+i][y+d2-i] = true;
        }

        // 오른쪽아래로 그리기
        for (int i=0; i<=d2; i++) {
            border[x+i][y+i] = true;
            border[x+d1+i][y-d1+i] = true;
        }

        // 1 구역
        for (int i=0; i<x+d1; i++) {
            for (int j=0; j<=y; j++) {
                if (border[i][j]) break;
                peopleSum[0] += people[i][j];
            }
        }

        // 2 구역
        for (int i=0; i<=x+d2; i++) {
            for (int j=N-1; j>y; j--) {
                if (border[i][j]) break;
                peopleSum[1] += people[i][j];
            }
        }

        // 3 구역
        for (int i=x+d1; i<N; i++) {
            for (int j=0; j<y-d1+d2; j++) {
                if (border[i][j]) break;
                peopleSum[2] += people[i][j];
            }
        }

        // 4 구역
        for (int i=x+d2+1; i<N; i++) {
            for (int j=N-1; j>=y-d1+d2; j--) {
                if (border[i][j]) break;
                peopleSum[3] += people[i][j];
            }
        }

        // 5 구역
        peopleSum[4] = total;
        for (int i=0; i<4; i++) {
            peopleSum[4] -= peopleSum[i];
        }

        Arrays.sort(peopleSum);

        min = Math.min(min, peopleSum[4] - peopleSum[0]);

    }

}
