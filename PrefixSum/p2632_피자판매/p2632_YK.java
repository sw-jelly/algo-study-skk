package PrefixSum.p2632_피자판매;

import java.io.*;
import java.util.*;

public class p2632_YK {

    static int N, result;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        int[] pizzaA = new int[A];
        int[] pizzaB = new int[B];
        for (int i = 0; i < A; ++i) {
            pizzaA[i] = Integer.parseInt(br.readLine());
        }
        for (int i = 0; i < B; ++i) {
            pizzaB[i] = Integer.parseInt(br.readLine());
        }

        int[] sumA = getSums(pizzaA, A);
        int[] sumB = getSums(pizzaB, B);

        for (int i = 1; i < N; ++i) {
            result += sumA[i] * sumB[N - i];
        }

        System.out.println(result);
        br.close();
    }

    private static int[] getSums(int[] pizza, int k) {
        int[] list = new int[N];

        for (int i = 0, sum; i < k; ++i) {
            sum = 0;
            for (int j = 0; j <= i; ++j) {
                sum += pizza[j];
            }

            if (i == k - 1) {
                if (sum == N) {
                    ++result;
                } else if (sum < N) {
                    ++list[sum];
                }
                break;
            }

            for (int left = 0, right = i; left < k; ++left) {
                if (sum == N) {
                    ++result;
                } else if (sum < N) {
                    ++list[sum];
                }

                sum -= pizza[left];
                right = right == k - 1 ? 0 : right + 1;
                sum += pizza[right];
            }
        }

        return list;
    }
}
