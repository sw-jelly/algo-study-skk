package Bruteforce.p17136_색종이붙이기;

import java.io.*;
import java.util.*;

public class p17136_SL {

    static int[][] map = new int[10][10];
    static int[] papers = {0, 5, 5, 5, 5, 5};
    static int result = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for(int i = 0; i < 10; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 10; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        go(0, 0, 0);

        if(result == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(result);
        }

    }

    public static void go(int x, int y, int cnt) {

        if(x >= 9 && y > 9) {
            result = Math.min(result, cnt);
            return;
        }

        // 이미 구한 값보다 크면 안되니까
        if(cnt >= result) {
            return;
        }

        // y가 10이 넘어가면 다음 줄로 넘어가야 함
        if(y > 9) {
            go(x + 1, 0, cnt);
            return;
        }

        if(map[x][y] == 1) {
            // 5부터 1까지 색종이를 붙일 수 있는지 확인
            for(int i = 5; i >= 1; i--) {
                if(papers[i] > 0 && check(x, y, i)) {
                    attach(x, y, i, 0);             // 색종이 붙이기
                    papers[i]--;                          // 색종이 사용
                    go(x, y+1, cnt+1);            // 다음으로 넘가기
                    papers[i]++;                          // 색종이 사용 취소
                    attach(x, y, i, 1);             // 색종이 떼기
                }
            }
        } else {   // 0인 경우, 오른쪽으로 이동
            go(x, y + 1, cnt);
        }
    }

    // 색종이 붙이기
    public static void attach(int x, int y, int size, int value) {
        for(int i = x; i < x + size; i++) {
            for(int j = y; j < y + size; j++) {
                map[i][j] = value;
            }
        }
    }

    // 사이즈별로 색종이 붙일 수 있는지 확인
    public static boolean check(int x, int y, int size) {

        for(int i = x; i < x + size; i++) {
            for(int j = y; j < y + size; j++) {
                if(i<0 || i>=10 || j<0 || j>=10) {
                    return false;
                }
                if(map[i][j] != 1) {
                    return false;
                }
            }
        }
        return true;
    }


}
