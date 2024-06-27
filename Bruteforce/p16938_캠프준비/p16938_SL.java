package Bruteforce.p16938_캠프준비;

import java.io.*;
import java.util.*;

public class p16938_SL {

    static int N, L, R, X;
    static int[] arr;
    static int result;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 2문제 이상 선택
        // L <= 합 <= R
        // 어려운 문제 - 쉬운 문제 >= X
        // 모든 방법의 수 출력

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 2개부터 N개까지 선택
        result = 0;
        for(int i=0; i<(1<<N); i++) {

            if(Integer.bitCount(i) < 2) continue;

            int sum = 0;
            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;

            for(int j=0; j<N; j++) {
                if((i & (1<<j)) != 0) {
                    sum += arr[j];
                    min = Math.min(min, arr[j]);
                    max = Math.max(max, arr[j]);
                }
            }

            if(sum >= L && sum <= R && max - min >= X) {
                result++;
            }
        }

        System.out.println(result);
    }
}
