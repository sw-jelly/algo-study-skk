package DataStructure.p3019_테트리스;

import java.util.*;
import java.io.*;

public class p3019_SL {

    static int[] map;
    static int C,P;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        C = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken()); // 블록 번호

        map = new int[C];
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < C; i++) {
            map[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(solve());

    }

    private static int solve() {

        int cnt = 0;

        // 블록 7가지
        // 회전 4가지
        // C-너비+1 만큼

        if(P==1){ // 1자 블록
            cnt += C;
            for(int i=0; i<=C-4; i++) {
                cnt += is_level(i, 4);
            }
        }else if(P==2){ // 정사각형
            for(int i=0; i<=C-2; i++) {
                cnt += is_level(i, 2);
            }
        }else if(P==3){ // 오른쪽 지그재그
            for(int i=0; i<=C-3; i++) {
                if(map[i] == map[i+1] && map[i] == map[i+2]-1) {
                    cnt++;
                }
            }
            for(int i=0; i<=C-2; i++) {
                if(map[i] == map[i+1]+1) {
                    cnt++;
                }
            }
        }else if(P==4){ // 왼쪽 지그재그
            for(int i=0; i<=C-3; i++) {
                if(map[i+1] == map[i+2] && map[i]-1 == map[i+1]) {
                    cnt++;
                }
            }
            for(int i=0; i<=C-2; i++) {
                if(map[i] == map[i+1]-1) {
                    cnt++;
                }
            }
        }else if(P==5){ // ㅗ
            // ㅗ
            for(int i=0; i<=C-3; i++) {
                cnt += is_level(i, 3);
            }
            // ㅏ
            for(int i=0; i<=C-2; i++) {
                if(map[i] == map[i+1]-1) {
                    cnt++;
                }
            }
            // ㅜ
            for(int i=0; i<=C-3; i++) {
                if(map[i] == map[i+2] && map[i+1] == map[i]-1) {
                    cnt++;
                }
            }
            // ㅓ
            for(int i=0; i<=C-2; i++) {
                if(map[i] == map[i+1]+1) {
                    cnt++;
                }
            }
        }else if(P==6){ // ㄴ 좌우대칭
            for(int i=0; i<=C-3; ++i) {
                cnt += is_level(i, 3);
            }
            for(int i=0; i<=C-2; ++i) {
                cnt += is_level(i, 2);
            }
            for(int i=0; i<=C-3; ++i) {
                if(map[i] == map[i+1]-1 && map[i+1] == map[i+2]) {
                    cnt++;
                }
            }
            for(int i=0; i<=C-2; ++i) {
                if(map[i]-2 == map[i+1]) {
                    cnt++;
                }
            }
        }else { // ㄴ
            for(int i=0; i<=C-3; ++i) {
                cnt += is_level(i, 3);
            }
            for(int i=0; i<=C-2; ++i) {
                cnt += is_level(i, 2);
            }
            for(int i=0; i<=C-3; ++i) {
                if(map[i] == map[i+1] && map[i+1] == map[i+2]+1) {
                    cnt++;
                }
            }
            for(int i=0; i<=C-2; ++i) {
                if(map[i]+2 == map[i+1]) {
                    cnt++;
                }
            }
        }

        return cnt;
    }

    // i~i+c-1이 높이가 모두 같은지 체크
    private static int is_level(int a, int b) {
        int h = map[a]; // a번째 높이

        for (int i = a + 1; i < a + b; i++) {
            if (map[i] != h)
                return 0;
        }

        return 1;
    }
}
