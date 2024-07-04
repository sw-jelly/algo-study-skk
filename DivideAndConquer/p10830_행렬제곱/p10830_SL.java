package DivideAndConquer.p10830_행렬제곱;

import java.io.*;
import java.util.*;

// 분할정복
public class p10830_SL {

    static int N;  // 행렬 크기
    static long B; // 제곱수
    static int[][] matrix;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        B = Long.parseLong(st.nextToken());
        matrix = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 행렬 제곱
        int[][] answer = pow(B);

        // 출력
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(answer[i][j] % 1000).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);

    }

    public static int[][] pow(long b) {

        if (b == 1) {
            return matrix;
        }

        int[][] half = pow(b / 2);
        int[][] result = new int[N][N];

        // 행렬 제곱 계산
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    result[i][j] += half[i][k] * half[k][j];
                }
                result[i][j] %= 1000;
            }
        }

        // 홀수일 경우
        if (b % 2 == 1) {

            int[][] temp = new int[N][N];

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    for (int k = 0; k < N; k++) {
                        temp[i][j] += result[i][k] * matrix[k][j];
                    }
                    temp[i][j] %= 1000;
                }
            }

            return temp;
        }

        return result;
    }


}
