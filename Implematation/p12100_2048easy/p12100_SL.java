package Implematation.p12100_2048easy;

import java.io.*;
import java.util.*;

// 최대 5번 이동해서 만들 수 있는 가장 큰 블록의 값 구하기
public class p12100_SL {

    static class Info {
        int x, y;
        public Info(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int N;
    static int[][] map;
    static int max = 0;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static boolean[][] check; // 블록이 합쳐졌는지 확인하는 배열

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

        go(0);
        System.out.println(max);

    }

    private static void go(int cnt) {

        if(cnt == 5) {
            for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++) {
                    max = Math.max(max, map[i][j]);
                }
            }
            return;
        }

        // 원래 맵 복사해두기
        int[][] originMap = new int[N][N];
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                originMap[i][j] = map[i][j];
            }
        }

        for(int d=0; d<4; d++) {
            move(map, d);
            go(cnt+1);

            // 다시 map에 담아주기
            for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++) {
                    map[i][j] = originMap[i][j];
                }
            }
        }

    }

    // 방향대로 블록 이동시키기
    private static void move(int[][] map, int d) {
        // 블록이 합쳐졌는지
        check = new boolean[N][N];

        if(d == 0) { // 상
            for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++) {
                    if(map[i][j] != 0) {
                        int num = map[i][j];
                        Info next = go(i, j, d, map);
                        map[i][j] = 0;  // 기존 위치 없애기
                        map[next.x][next.y] += num; // 새로운 위치에 블록 값 추가
                    }
                }
            }
        } else if(d == 1) { // 하
            for(int i=N-1; i>=0; i--) {
                for(int j=0; j<N; j++) {
                    if(map[i][j] != 0) {
                        int num = map[i][j];
                        Info next = go(i, j, d, map);
                        map[i][j] = 0;
                        map[next.x][next.y] += num;
                    }
                }
            }
        } else if(d == 2) { // 좌
            for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++) {
                    if(map[i][j] != 0) {
                        int num = map[i][j];
                        Info next = go(i, j, d, map);
                        map[i][j] = 0;
                        map[next.x][next.y] += num;
                    }
                }
            }
        } else { // 우
            for(int i=0; i<N; i++) {
                for(int j=N-1; j>=0; j--) {
                    if(map[i][j] != 0) {
                        int num = map[i][j];
                        Info next = go(i, j, d, map);
                        map[i][j] = 0;
                        map[next.x][next.y] += num;
                    }
                }
            }
        }

    }

    // 블록이 움직일 수 있는 가장 먼 위치 찾기
    private static Info go(int i, int j, int d, int[][] map) {
        Info cur;
        int x = i;
        int y = j;

        while(true) {
            int nx = x + dx[d];
            int ny = y + dy[d];

            // 범위 밖, 0이 아니고 내 값과 다를 때
            if(nx < 0 || ny < 0 || nx >= N || ny >= N
                || (map[nx][ny] != 0 && map[i][j] != map[nx][ny])) {
                cur = new Info(x, y);
                break;
            }

            // 같은 경우
            if(map[i][j] == map[nx][ny]) {
                if(check[nx][ny]) {
                    cur = new Info(x, y);
                    break;
                } else {
                    check[nx][ny] = true;
                }
            }
            x = nx;
            y = ny;
        }

        return cur;
    }

}
