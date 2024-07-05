package BinarySearch.p1450_냅색문제;

import java.io.*;
import java.util.*;

// 이분탐색, 중간에서 만나기
public class p1450_SL {

    static int N, C; // N개 물건, C 무게
    static int[] weight; // 무게
    static long result = 0;
    static ArrayList<Long> leftList = new ArrayList<>();
    static ArrayList<Long> rightList = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        weight = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            weight[i] = Integer.parseInt(st.nextToken());
        }

        // 무게를 반으로 나눠서 각각의 경우의 수를 구한다.
        go(0, N / 2, 0, leftList);
        go(N / 2, N, 0, rightList);

        // 정렬
        Collections.sort(leftList);

        // 이분탐색
        for (long right : rightList) {
            if(right > C) {
                continue;
            }
            int leftIndex = 0;
            int rightIndex = leftList.size();

            while (leftIndex < rightIndex) {
                int mid = (leftIndex + rightIndex) / 2;
                if (leftList.get(mid) + right <= C) {
                    leftIndex = mid + 1;
                } else {
                    rightIndex = mid;
                }
            }

            result += rightIndex;
        }

        System.out.println(result);

    }

    // 부분집합 무게 합을 리스트에 저장
    private static void go(int start, int end, long sum, ArrayList<Long> list) {
        if (sum > C) {
            return;
        }
        if (start >= end) {
            list.add(sum);
            return;
        }
        go(start + 1, end, sum, list);
        go(start + 1, end, sum + weight[start], list);
    }

}
