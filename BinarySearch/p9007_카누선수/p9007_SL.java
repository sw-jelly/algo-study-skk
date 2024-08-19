package BinarySearch.p9007_카누선수;

import java.util.*;
import java.io.*;

// 카누선수, 이분탐색.... ㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠ
public class p9007_SL {

    static int k, n;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(st.nextToken());
        long weight;
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            k = Integer.parseInt(st.nextToken()); // 합해서 돼야 할 숫자
            n = Integer.parseInt(st.nextToken());

            int[][] group = new int[4][n];

            for (int i = 0; i < 4 ; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    group[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int[][] calcGroup = new int[2][n*n];
            int index = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    calcGroup[0][index] = group[0][i] + group[1][j];
                    calcGroup[1][index++] = group[2][i] + group[3][j];
                }
            }

            Arrays.sort(calcGroup[0]);
            Arrays.sort(calcGroup[1]);

            sb.append(k - find(calcGroup[0], calcGroup[1])).append('\n');

        }

        System.out.println(sb);
    }

    // 목표 값에 가장 가까운 합 찾기
    public static int find(int[] first, int[] second) {

        int ans = 0;
        int min = Integer.MAX_VALUE;

        for (int i=0; i<first.length; i++) {
            int tmp = first[i];
            int result = binarySearch(second, k - tmp); // 최소 차이
            int abs = Math.abs(result);

            if (min > abs) {
                ans = result;
                min = abs;
            } else if (min == abs && ans < 0) {
                ans = result;
            }
        }

        return ans;
    }

    public static int binarySearch(int[] arr, int num) {

        int result = 0;
        int min = Integer.MAX_VALUE;
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = (left + right) >> 1;

            int calc = num - arr[mid];
            int abs = Math.abs(calc);

            if (min > abs) {
                min = abs;
                result = calc;
            } else if (min == abs && result < 0) {
                result = calc;
            }

            if (calc > 0) {
                left = mid + 1;
            } else if (calc < 0) {
                right = mid - 1;
            } else {
                return 0;
            }
        }

        return result;
    }

}
