package DivideAndConquer.p10830_행렬제곱;

import java.io.*;
import java.util.*;

public class p10830_YK {

    static int N, MOD = 1000;
    static long B;
    static int[][] matrix;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        B = Long.parseLong(st.nextToken());
        matrix = new int[N][N];

        for (int i = 0; i < N; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; ++j) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] result = calculate(B);
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                sb.append(result[i][j] ).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
        br.close();
    }

    public static int[][] calculate(long exp) {
        if (exp == 1) return matrix;
        if (exp == 2) return square(matrix, matrix);

        int[][] m = calculate(exp / 2);
        if (exp % 2 == 1) return square(m, square(m, matrix));
        else return square(m, m);
    }

    private static int[][] square(int[][] m1, int[][] m2) {
        int[][] result = new int[N][N];

        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                for (int k = 0; k < N; ++k) {
                    result[i][j] = (result[i][j] + m1[i][k] * m2[k][j]) % MOD;
                }
            }
        }

        return result;
    }
}
