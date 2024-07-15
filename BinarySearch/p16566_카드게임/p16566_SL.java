package BinarySearch.p16566_카드게임;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class p16566_SL {

    static int N, M, K;
    static int[] cards; // 카드 번호
    static int[] parents;

    static int find(int a) {
        if(parents[a] == a) return a;
        return parents[a] = find(parents[a]);
    }

    static boolean union(int a, int b) {
        int pa = find(a);
        int pb = find(b);
        if(pa == pb) return false;
        parents[pa] = pb;
        return true;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken()); // 카드 개수
        M = Integer.parseInt(st.nextToken()); // 카드 종류
        K = Integer.parseInt(st.nextToken()); // 철수가 내는 카드 개수
        cards = new int[M];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            cards[i] = Integer.parseInt(st.nextToken());
        }

        // 0 1 2 3 4 5 6 7
        parents = new int[M + 1];
        for(int i = 0; i <= M; i++) {
            parents[i] = i;
        }

        // 2 3 4 5 7 8 9
        Arrays.sort(cards);

        // 4 1 1 3 8
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            int num = Integer.parseInt(st.nextToken()); // 4
            int idx = solve(num); // 3 : 4보다 큰 첫 번째 카드(5)의 위치
            idx = find(idx); // 3
            union(idx, idx + 1); // parents[3] = 4
            sb.append(cards[idx]).append("\n"); // cards[3] = 5
        }

        // 5 2 3 4 9
        System.out.println(sb);
    }

    private static int solve(int target) {
        int left = 0;
        int right = M - 1;
        int mid;

        while (left <= right) {
            mid = (left + right) / 2;

            if (cards[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}
