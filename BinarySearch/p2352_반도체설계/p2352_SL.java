package BinarySearch.p2352_반도체설계;

import java.util.*;
import java.io.*;

// 최장 증가 부분 수열
// https://steady-coding.tistory.com/48 -... 다시보기
public class p2352_SL {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int[] pins = new int[N]; // [4, 2, 6, 3, 1, 5]

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            pins[i] = Integer.parseInt(st.nextToken());
        }

        // [4, 2, 6, 3, 1, 5] => LIS : [1, 3, 5] ( => 2,3,5)
        ArrayList<Integer> lis = new ArrayList<>();

        for (int i=0; i<N; i++) {
            int pin = pins[i];

            if (lis.isEmpty() || lis.get(lis.size() - 1) < pin) {  // LIS 배열의 마지막 원소보다 크면 추가
                lis.add(pin);
            } else {
                // LIS 배열에서 현재 원소가 들어갈 위치를 찾음 (이분 탐색 이용)
                int where = Collections.binarySearch(lis, pin);
                if (where < 0) {
                    where = -(where + 1);  // 삽입 위치 계산
                }
                lis.set(where, pin);  // 기존 원소를 교체하여 LIS 유지
            }
        }

        System.out.println(lis.size());  // 최장 증가 부분 수열의 길이 출력

    }
}
