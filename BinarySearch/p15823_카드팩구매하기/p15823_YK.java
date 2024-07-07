package BinarySearch.p15823_카드팩구매하기;

import java.io.*;
import java.util.*;

public class p15823_YK {

    static int N, M;
    static int[] cards;
    static Map<Integer, Integer> cMap;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        cards = new int[N];
        cMap = new HashMap<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; ++i) {
            cards[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(find());
        br.close();
    }

    private static int find() {
        int left = 1, right = N / M;
        int result = 0;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (check(mid)) {
                result = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return result;
    }

    private static boolean check(int k) {
        int packs = 0;
        cMap.clear();
        addCards(-1, -1, 0, k - 1);

        for (int left = 0, right = k - 1; left <= N - k; ++left) {
            if (cMap.size() == k) {
                ++packs;
                if (right + k < N) {
                    addCards(left, right, right + 1, right + k);
                }
                left = right;
                right = left + k;
            } else {
                ++right;
                if (right >= N) break;
                int cnt = cMap.remove(cards[left]);
                if (cnt > 1) {
                    cMap.put(cards[left], cnt - 1);
                }
                cMap.put(cards[right], cMap.getOrDefault(cards[right], 0) + 1);
            }

            if (packs == M) return true;
        }

        return false;
    }

    private static void addCards(int s1, int e1, int s2, int e2) {
        for (int i = s1; i >= 0 && i <= e1; ++i) {
            int cnt = cMap.remove(cards[i]);
            if (cnt > 1) {
                cMap.put(cards[i], cnt - 1);
            }
        }

        for (int i = s2; i <= e2; ++i) {
            cMap.put(cards[i], cMap.getOrDefault(cards[i], 0) + 1);
        }
    }
}
