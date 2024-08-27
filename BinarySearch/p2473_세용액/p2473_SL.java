package BinarySearch.p2473_세용액;

import java.io.*;
import java.util.*;

public class p2473_SL {

    static int[] input;
    static int N;
    static long closestSum = Long.MAX_VALUE;
    static int[] result = new int[3];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        input = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            input[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(input);

        // -97 -6 -1 6 98
        // 첫번째 수
        for(int i = 0; i < N - 2; i++) {
            int left = i + 1; // 두번째
            int right = N - 1; // 세번째

            while(left < right) {
                long sum = (long)input[i] + input[left] + input[right];

                if(Math.abs(sum) < Math.abs(closestSum)) {
                    closestSum = sum;
                    result[0] = input[i];
                    result[1] = input[left];
                    result[2] = input[right];
                }

                if(sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }

        System.out.println(result[0] + " " + result[1] + " " + result[2]);

    }
}
