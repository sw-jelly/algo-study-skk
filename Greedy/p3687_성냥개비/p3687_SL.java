package Greedy.p3687_성냥개비;

import java.util.*;
import java.io.*;

public class p3687_SL {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());

            long[] minDp = new long[101];

            //성냥 한개를 표현하는데 사용하는 개수는 2~7개 사이
            //2개 : 1, 3개 : 7, 4개 : 4, 5개 : 2,3,5, 6개 : 6,9,0, 7개 : 8

            Arrays.fill(minDp, Long.MAX_VALUE);
            minDp[2] = 1;
            minDp[3] = 7;
            minDp[4] = 4;
            minDp[5] = 2;
            minDp[6] = 6;
            minDp[7] = 8;
            minDp[8] = 10;

            String[] add = {"", "", "1", "7", "4", "2", "0", "8"};

            for (int j = 9; j <= 100; j++) {
                for (int k = 2; k <= 7; k++) {
                    String line = "" + minDp[j - k] + add[k]; // j-k 개랑, k개로 모든 경우의 수 만들기
                    minDp[j] = Math.min(Long.parseLong(line), minDp[j]);
                }
            }

            StringBuilder max = new StringBuilder();
            long a = n / 2;
            long b = n % 2;
            if (b == 1) {
                max.append("7");
            } else {
                max.append("1");
            }

            for (int j = 1; j < a; j++) {
                max.append("1");
            }
            System.out.println(minDp[n] + " " + max);

        }
    }

}
