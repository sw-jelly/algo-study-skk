package PrefixSum.p2632_피자판매;

import java.io.*;
import java.util.*;

// 누적합
public class p2632_SL {

    static int M, N, S;
    static int[] A, B;
    static int[] prefixSumA, prefixSumB;
    static int[] arrA, arrB;
    static int result = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        S = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        A = new int[2*M];
        B = new int[2*N];
        prefixSumA = new int[2*M + 1];
        prefixSumB = new int[2*N + 1];

        for (int i = 0; i < M; i++) {
            A[i] = Integer.parseInt(br.readLine());
            A[i + M] = A[i];
        }
        for (int i = 0; i < N; i++) {
            B[i] = Integer.parseInt(br.readLine());
            B[i + N] = B[i];
        }

        for (int i = 1; i <= 2*M; i++) {
            prefixSumA[i] = prefixSumA[i - 1] + A[i-1];
        }
        for (int i = 1; i <= 2*N; i++) {
            prefixSumB[i] = prefixSumB[i - 1] + B[i-1];
        }

        arrA = new int[S + 1]; // 각 피자로 만들 수 있는 크기 합
        arrB = new int[S + 1];
        makeArr(arrA, prefixSumA, M);
        makeArr(arrB, prefixSumB, N);

        result = arrA[S] + arrB[S];
        for(int i=1; i<S; i++) {
            result += arrA[i] * arrB[S-i];
        }

        System.out.println(result);

    }

    private static void makeArr(int[] arr, int[] ps, int size) {

        for(int i=1; i<size; i++) { // 조각 개수
            for(int s=1; s<=size; s++) { // 시작점
                int sum = ps[s+i-1] - ps[s-1];
                if(sum > S) continue;
                arr[sum]++;
            }
        }

        // 피자를 한번도 안자르는 경우
        if(ps[size] <= S) {
            arr[ps[size]]++;
        }
    }

}
