package TwoPointer.p20366_같이눈사람만들래;

import java.util.*;
import java.io.*;

public class p20366_SL {

    static int N;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 두 눈사람의 키의 차이가 작도록.
        N = Integer.parseInt(st.nextToken());
        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        int min = Integer.MAX_VALUE;

        for(int i=0; i<N-1; i++) {
            for(int j=i+1; j<N; j++) {
                int snowman1 = arr[i] + arr[j]; // 눈사람1의 키 고정

                int start = 0; // 눈사람2 쌍 구하기
                int end = N-1;

                while (start < end && min != 0) {

                    // 눈덩이들 겹치지 않기
                    if(start == i || start == j) {
                        start++;
                        continue;
                    }
                    if(end == i || end == j) {
                        end--;
                        continue;
                    }

                    int snowman2 = arr[start] + arr[end];
                    min = Math.min(min, Math.abs(snowman2 - snowman1));

                    // 더 작은 차이를 찾기 위해 이동하기
                    if(snowman1 > snowman2) {
                        start++;
                    } else {
                        end--;
                    }

                    if(min == 0) {
                        break;
                    }

                } // while
            }
        }

        System.out.println(min);

    }
}
