package BackTracking.p18428_감시피하기;

import java.io.*;
import java.util.*;

public class p18428_YK {
    static int[][] map;
    static int N;
    static final int S = 1, T = 2, O = 3;
    static final String YES = "YES", NO = "NO";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        List<int[]> teachers = new ArrayList<>();

        for (int i = 0; i < N; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; ++j) {
                char ch = st.nextToken().charAt(0);
                if (ch == 'X') continue;
                else if (ch == 'S') map[i][j] = S;
                else {
                    map[i][j] = T;
                    teachers.add(new int[] {i, j});
                }
            }
        }

        for (int i = 0; i < N * N; ++i) {
            for (int j = i + 1; j < N * N; ++j) {
                for (int k = j + 1; k < N * N; ++k) {
                    boolean flag = true;
                    if (i == j || j == k || k == i) continue;
                    if(!putObstacles(i, j, k)) continue;

                    for (int[] teacher : teachers) {
                        if (!doTeacher(teacher[0], teacher[1])) {
                            flag = false;
                            break;
                        }
                    }

                    if (flag) {
                        System.out.println(YES);
                        return;
                    }
                    removeObstacles(i, j, k);
                }
            }
        }

        System.out.println(NO);
    }

    private static boolean putObstacles(int i, int j, int k) {
        int x1 = i / N, y1 = i % N;
        int x2 = j / N, y2 = j % N;
        int x3 = k / N, y3 = k % N;

        if (map[x1][y1] == T || map[x1][y1] == S) return false;
        if (map[x2][y2] == T || map[x2][y2] == S) return false;
        if (map[x3][y3] == T || map[x3][y3] == S) return false;

        map[x1][y1] = O;
        map[x2][y2] = O;
        map[x3][y3] = O;
        return true;
    }

    private static void removeObstacles(int i, int j, int k) {
        int x1 = i / N, y1 = i % N;
        int x2 = j / N, y2 = j % N;
        int x3 = k / N, y3 = k % N;

        map[x1][y1] = 0;
        map[x2][y2] = 0;
        map[x3][y3] = 0;
    }

    private static boolean doTeacher(int x, int y) {
        // 상
        for (int i = x - 1; i >= 0; --i) {
            if (map[i][y] == S) return false;
            if (map[i][y] == T || map[i][y] == O) break;
        }

        // 하
        for (int i = x + 1; i < N; ++i) {
            if (map[i][y] == S) return false;
            if (map[i][y] == T || map[i][y] == O) break;
        }

        // 좌
        for (int j = y - 1; j >= 0; --j) {
            if (map[x][j] == S) return false;
            if (map[x][j] == T || map[x][j] == O) break;
        }

        // 우
        for (int j = y + 1; j < N; ++j) {
            if (map[x][j] == S) return false;
            if (map[x][j] == T || map[x][j] == O) break;
        }

        return true;
    }
}
