package DivideAndConquer.p2447_별찍기10;

import java.io.*;

// 분할정복
public class p2447_SL {

    static int N;
    static char[][] map;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        map = new char[N][N];

        divide(0, 0, N, false);

        for (int i = 0; i < N; i++) {
            sb.append(map[i]).append("\n");
        }

        System.out.println(sb.toString());
    }

    private static void divide(int x, int y, int size, boolean isBlank) {

        if (isBlank) {
            for (int i = x; i < x + size; i++) {
                for (int j = y; j < y + size; j++) {
                    map[i][j] = ' ';
                }
            }
            return;
        }

        if (size == 1) {
            map[x][y] = '*';
            return;
        }

        int newSize = size / 3;
        int count = 0;
        for (int i = x; i < x + size; i += newSize) {
            for (int j = y; j < y + size; j += newSize) {
                count++;
                if (count == 5) {
                    divide(i, j, newSize, true);
                } else {
                    divide(i, j, newSize, false);
                }
            }
        }

    }

}
