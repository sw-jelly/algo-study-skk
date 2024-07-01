package p18809_Gaaaaaaaaaarden;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class p18809_SL {

    static class Info {
        int x, y, time, color;
        public Info(int x, int y) {
            this.x = x;
            this.y = y;
        }
        public Info(int x, int y, int time, int color) {
            this.x = x;
            this.y = y;
            this.time = time;
            this.color = color;
        }
    }

    static int N, M, G, R;
    static int[][] map;
    static int[] dx = {-1, 1, 0 ,0};
    static int[] dy = {0, 0, -1, 1};
    static int max = 0;
    static int[] selectedLand;
    static int[] selectedGreen;
    static ArrayList<Info> possibleLand;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new java.io.InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        G = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        selectedLand = new int[G + R];
        selectedGreen = new int[G];
        possibleLand = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) {
                    possibleLand.add(new Info(i, j));
                }
            }
        }

        selectLand(0, 0);
        System.out.println(max);
    }

    private static void selectLand(int cnt, int start) {
        if (cnt == G + R) {
            selectGreen(0, 0);
            return;
        }

        for (int i = start; i < possibleLand.size(); i++) {
            selectedLand[cnt] = i;
            selectLand(cnt + 1, i + 1);
        }
    }

    private static void selectGreen(int cnt, int start) {
        if (cnt == G) {
            go();
            return;
        }

        for (int i = start; i < G + R; i++) {
            selectedGreen[cnt] = i;
            selectGreen(cnt + 1, i + 1);
        }
    }

    // bfs
    private static void go() {
        int[][] greenTime = new int[N][M];
        int[][] redTime = new int[N][M];
        Queue<Info> q = new LinkedList<>();

        for (int i = 0; i < G + R; i++) {
            boolean check = false;

            for (int j = 0; j < G; j++) {
                if (selectedGreen[j] == i) {
                    check = true;
                    break;
                }
            }
            if (check) {
                greenTime[possibleLand.get(selectedLand[i]).x][possibleLand.get(selectedLand[i]).y] = 1;
                q.add(new Info(possibleLand.get(selectedLand[i]).x, possibleLand.get(selectedLand[i]).y, 1, 3));
            } else {
                redTime[possibleLand.get(selectedLand[i]).x][possibleLand.get(selectedLand[i]).y] = 1;
                q.add(new Info(possibleLand.get(selectedLand[i]).x, possibleLand.get(selectedLand[i]).y, 1, 4));
            }
        }

        int cnt = 0;
        while (!q.isEmpty()) {
            Info cur = q.poll();

            // 꽃이 피었을 때
            if(greenTime[cur.x][cur.y] == redTime[cur.x][cur.y]) {
                continue;
            }

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                if (map[nx][ny] == 0) continue;

                if (cur.color == 3) { // green
                    if (greenTime[nx][ny] > 0) continue;

                    // 이미 빨간색을 뿌렸음
                    if (redTime[nx][ny] > 0 && redTime[nx][ny] <= cur.time) continue;

                    greenTime[nx][ny] = cur.time + 1;

                    // 꽃이 피는 경우
                    if(redTime[nx][ny] == cur.time + 1) {
                        cnt++;
                        continue;
                    }

                    q.add(new Info(nx, ny, cur.time + 1, cur.color));

                } else { // red
                    if (redTime[nx][ny] > 0) continue;

                    // 이미 초록색을 뿌렸음
                    if (greenTime[nx][ny] > 0 && greenTime[nx][ny] <= cur.time) continue;

                    redTime[nx][ny] = cur.time + 1;

                    // 꽃이 피는 경우
                    if(greenTime[nx][ny] == cur.time + 1) {
                        cnt++;
                        continue;
                    }

                    q.add(new Info(nx, ny, cur.time + 1, 4));
                }
            }
        }

        max = Math.max(max, cnt);
    }
}
