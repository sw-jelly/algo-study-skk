package PrefixSum.p21758_꿀따기;

import java.util.*;
import java.io.*;

public class p21758_SL {

    static int N;
    static int[] arr;
    static int max = 0;
    static int[] prefixSum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        arr = new int[N+1];
        prefixSum = new int[N+1];

        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            prefixSum[i] = prefixSum[i-1] + arr[i];
        }

        // 0 9 9  4  1  4  9  9
        // 0 9 18 22 23 27 36 45
        leftHive();
        middleHive();
        rightHive();

        System.out.println(max);

    }

    // 벌집은 맨 왼쪽, 벌1은 맨 오른쪽
    // 벌2가 중간 어딘가
    // 1) 벌집-9 벌1-9 / 벌2-(모름)
    // -> 맨왼(0)부터 맨오(N-2) - (벌2 * 2)
    private static void leftHive() {
        int sum = 0;
        // 가능한 벌2의 위치
        for(int i=2; i<=N-1; i++) {
            sum = (prefixSum[N] - arr[N] - arr[i]) + prefixSum[i - 1];
            max = Math.max(max, sum);
        }
    }

    // 벌1은 맨 왼쪽, 벌2는 맨 오른쪽
    // 벌집은 중간 어딘가
    // 2) 벌1-9 벌2-9 / 벌집-(모름)
    // -> 맨왼(1)부터 맨오(N-2) + 벌집
    private static void middleHive() {
        int sum = 0;
        // 가능한 벌집의 위치
        for (int i = 2; i <= N - 1; i++) {
            sum = prefixSum[i] - arr[1] + (prefixSum[N - 1] - prefixSum[i - 1]);
            max = Math.max(max, sum);
        }
    }

    // 벌1은 맨 왼쪽, 벌집은 맨 오른쪽
    // 벌2가 중간 어딘가
    // 3) 벌1-9 벌집-9 / 벌2-(모름)
    // -> 맨왼(1)부터 맨오(N-1) - 벌2
    //    + 벌2부터 맨오(N-1)
    private static void rightHive() {
        int sum = 0;
        // 가능한 벌2의 위치
        for (int i = 2; i <= N - 1; i++) {
            sum = (prefixSum[N] - arr[1] - arr[i]) + (prefixSum[N] - prefixSum[i]);
            max = Math.max(max, sum);
        }
    }
}
