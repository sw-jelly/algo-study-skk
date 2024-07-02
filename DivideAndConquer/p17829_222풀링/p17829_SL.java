package DivideAndConquer.p17829_222풀링;

import java.util.*;
import java.io.*;

public class p17829_SL {

    static int N;
    static int[][] map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        map = new int[N][N];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(divide(0, 0, N));
        br.close();

    }

    public static int divide(int x, int y, int size) {

        if(size == 1) {
            return map[x][y];
        }

        int[] arr = new int[4];
        arr[0] = divide(x, y, size/2);
        arr[1] = divide(x, y+size/2, size/2);
        arr[2] = divide(x+size/2, y, size/2);
        arr[3] = divide(x+size/2, y+size/2, size/2);

        Arrays.sort(arr);

        return arr[2];
    }
}
