package PrefixSum.p21758_꿀따기;

import java.io.*;
import java.util.*;

public class p21758_YK {

    static int N;
    static int[] honeys, leftSum, rightSum;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        honeys = new int[N];
        leftSum = new int[N];
        rightSum = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0, max = 0; i < N; ++i) {
            honeys[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, honeys[i]);
        }

        leftSum[0] = honeys[0];
        rightSum[N - 1] = honeys[N - 1];
        for (int i = 1; i < N; ++i) {
            leftSum[i] += leftSum[i - 1] + honeys[i];
            rightSum[N - 1 - i] += rightSum[N - i] + honeys[N - 1 - i];
        }

        // 1. 둘이 한쪽 끝에서 다른 쪽 끝으로 가는 경우
        // 한 벌과 벌통은 양끝에 있는 게 이득 - 다른 한 벌의 최적 시작 위치 찾기
        int result = Math.max(doBeeBeeHive(), doHiveBeeBee());

        // 2. 벌통이 벌들 사이에 있는 경우
        // 무조건 양쪽 끝에서 시작하는게 이득 - 최적의 벌통 위치 찾기
        result = Math.max(result, doBeeHiveBee());

        System.out.println(result);
        br.close();
    }

    private static int doBeeHiveBee() {
        int result = 0;
        for (int i = 1, startHoney = honeys[0] + honeys[N - 1]; i < N - 1; ++i) {
            result = Math.max(result, leftSum[i] + rightSum[i] - startHoney);
        }
        return result;
    }

    private static int doBeeBeeHive() {
        int beeA = leftSum[N - 1] - honeys[0];

        int result = 0;
        for (int i = 1, tmpA, tmpB; i < N - 1; ++i) {
            tmpA = beeA - honeys[i];
            tmpB = leftSum[N - 1] - leftSum[i];
            result = Math.max(tmpA + tmpB, result);
        }

        return result;
    }

    private static int doHiveBeeBee() {
        int beeA = rightSum[0] - honeys[N - 1];

        int result = 0;
        for (int i = N - 2, tmpA, tmpB; i > 0; --i) {
            tmpA = beeA - honeys[i];
            tmpB = rightSum[0] - rightSum[i];
            result = Math.max(tmpA + tmpB, result);
        }

        return result;
    }
}
