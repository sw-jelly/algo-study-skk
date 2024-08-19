package BinarySearch.p3020_개똥벌레;

import java.util.*;
import java.io.*;

public class p3020_SL {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());

        int[] down = new int[N/2];
        int[] up = new int[N/2];
        for(int i=0; i<N/2; i++) {
            int a = Integer.parseInt(br.readLine());
            int b = Integer.parseInt(br.readLine());
            down[i] = a;
            up[i] = b;
        }

        Arrays.sort(down);
        Arrays.sort(up);

        int min = N;
        int cnt = 0;

        // 6 7
        // 1 5 3 3 5 1
        // 1 3 5 => 1 3 5
        // 5 3 1 => 1 3 5

        for(int i=1; i<=H; i++) {
            int both = go(0, N/2, i, down) + go(0, N/2, H-i+1, up);

            if(min == both) {
                cnt++;
                continue;
            }
            if(min > both) {
                min = both;
                cnt = 1;
            }
        }

        System.out.println(min +" "+ cnt);

    }

    static int go(int left, int right, int h, int[] arr) {

        while(left < right) {
            int mid = (left + right) / 2;

            if(arr[mid] >= h) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return arr.length - right; // 부딪히는 장애물 개수
    }

}
