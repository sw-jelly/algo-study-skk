package BackTracking.p15684_사다리조작;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p15684_SL {

    static int N, M, H;
    static int[][] map;
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 세로선 개수
        M = Integer.parseInt(st.nextToken()); // 가로선 개수
        H = Integer.parseInt(st.nextToken()); // 가로선 놓을 수 있는 위치 개수
        map = new int[H + 1][N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            map[a][b] = 1;
            map[a][b + 1] = -1;
        }

        go(1, 1, 0);

        if (answer == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(answer);
        }

    }

    private static void go(int x, int y, int cnt) {
        if (cnt >= answer) {
            return;
        }

        if (check()) {
            answer = Math.min(answer, cnt);
            return;
        }

        if (cnt == 3) {
            return;
        }

        for (int i = x; i <= H; i++) {
            for (int j = 1; j < N; j++) {
                if (map[i][j] == 0 && map[i][j + 1] == 0) {
                    map[i][j] = 1;
                    map[i][j + 1] = -1;
                    go(i, j, cnt + 1);
                    map[i][j] = 0;
                    map[i][j + 1] = 0;
                }
            }
        }
    }

    private static boolean check() {
        for (int i = 1; i <= N; i++) {
            int col = i;
            for (int row = 1; row <= H; row++) {
                if (map[row][col] == 1) {
                    col++;
                } else if (map[row][col] == -1) {
                    col--;
                }
            }
            if (col != i) {
                return false;
            }
        }
        return true;
    }
}
