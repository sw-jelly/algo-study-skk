package DynamicProgramming.p2515_전시장;

import java.util.*;
import java.io.*;

public class p2515_SL {

    static int N, S;
    static int[] pictures;
    static int[] price;  // i 높이에서 얻을 수 있는 최대 가격
    static int min, max; // 가장 작은 높이, 큰 높이
    static final int MAX = 20000000;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        pictures = new int[MAX + 1];
        price = new int[MAX + 1];
        min = MAX;
        max = 0;

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int h = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if (h < min) {
                min = h;
            }
            if (h > max) {
                max = h;
            }
            if (pictures[h] < c) { // h높이가 여러개일때 제일 큰 거로만 넣기
                pictures[h] = c;
            }
        }

        price[max] = pictures[max];
        for (int h = max; h >= min; h--) {
            price[h] = Math.max(price[h + 1], price[h + S] + pictures[h]);
        }

        System.out.println(price[min]);

    }


}
/*
    pictures[8] = 230
    pictures[10] = 100
    pictures[15] = 80
    pictures[17] = 200
    pictures[20] = 75
    pictures[26] = 80
* */