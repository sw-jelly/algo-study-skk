package BinarySearch.p1450_냅색문제;

import java.io.*;
import java.util.*;

public class p1450_YK {

    static int N, C;
    static long[] items;
    static ArrayList<Long> groups;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int N2 = N >> 1;
        C = Integer.parseInt(st.nextToken());
        items = new long[N];
        groups = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; ++i) {
            items[i] = Integer.parseInt(st.nextToken());
        }

        List<Long> firstHalf = getSubsets(0, N2 - 1);
        List<Long> secondHalf = getSubsets(N2, N - 1);

        Collections.sort(firstHalf);
        Collections.sort(secondHalf);

        long result = 0;
        for (long k : firstHalf) {
            result += upperbound(secondHalf, (int) (C - k)) + 1;
        }
        System.out.println(result);
        br.close();
    }

    private static List<Long> getSubsets(int left, int right) {
        List<Long> result = new ArrayList<>();

        for (int mask = 0, cnt = right - left + 1, size = 1 << cnt; mask < size; ++mask) {
            long sum = 0;
            for (int i = 0; i < cnt; ++i) {
                if ((mask & (1 << i)) == 0) continue;
                sum += items[left + i];
            }
            if (sum <= C) result.add(sum);
        }

        return result;
    }

    private static int upperbound(List<Long> list, int num) {
        int left = 0, right = list.size() - 1;

        while (left <= right) {
            int mid = (left + right) >> 1;
            long sum = list.get(mid);

            if (sum <= num) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return left - 1;
    }
}
