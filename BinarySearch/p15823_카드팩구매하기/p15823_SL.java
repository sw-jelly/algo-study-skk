package BinarySearch.p15823_카드팩구매하기;

import java.io.*;
import java.util.*;

// 이분탐색
public class p15823_SL {

    static int N, M;
    static int[] cards;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 각 카드 팩 구성 카드의 수는 일치해야함
        // 한 카드 팩 안에 같은 종류의 카드 안됨
        // 카드 중복 사용 안됨

        cards = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            cards[i] = Integer.parseInt(st.nextToken());
        }

        int low = 1;      // 최소 카드 수
        int high = N / M; // 최대 카드 수
        int max = 0;

        while(low <= high) {
            int mid = (low + high) / 2;

            if (isPossible(mid)) {
                max = Math.max(max, mid);
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        System.out.println(max);

    }

    private static boolean isPossible(int K) {

        Map<Integer, Integer> m = new HashMap<>();

        int left = 0;  // 구간 맨 왼쪽
        int right = 0; // 구간 맨 오른쪽
        int cnt = 0;   // 구간 내 고유한 카드 수
        int total = M;

        while(right < N) { // 구간 증가시키기

            m.put(cards[right], m.getOrDefault(cards[right], 0) + 1);
            if(m.get(cards[right]) == 1) {
                cnt++;
            }
            right++;

            // 구간의 길이가 K를 초과하면, left 포인터를 오른쪽으로 이동시켜 크기 조절
            while(right - left > K) {
                m.put(cards[left], m.get(cards[left]) - 1);
                if(m.get(cards[left]) == 0) {
                    cnt--;
                }
                left++;
            }

            // 구간의 길이가 K이고, 고유한 카드 수가 K일 때
            if(cnt == K && (right - left) == K) {
                total--;
                if(total == 0) {
                    return true;
                }
                // 구간을 다음으로 이동시키기 위해 초기화
                m.clear();
                cnt = 0;
                right = left;
            }

        }

        return false;
    }
}
