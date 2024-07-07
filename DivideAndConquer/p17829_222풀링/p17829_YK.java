package DivideAndConquer.p17829_222풀링;

import java.io.*;
import java.util.*;

public class p17829_YK {

    static int N;
    static int[][] matrix;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        matrix = new int[N][N];

        for (int i = 0; i < N; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; ++j) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(findSecondLargest(0, 0, N));
        br.close();
    }

    private static int findSecondLargest(int x, int y, int len) {
        if (len == 1) return matrix[x][y];

        int[] nums = new int[4];
        int mid = len >> 1;

        nums[0] = findSecondLargest(x, y, mid);
        nums[1] = findSecondLargest(x, y + mid, mid);
        nums[2] = findSecondLargest(x + mid, y, mid);
        nums[3] = findSecondLargest(x + mid, y + mid, mid);

        return getSecond(nums);
    }

    private static int getSecond(int[] nums) {
        Arrays.sort(nums);
        return nums[2];
    }
}
