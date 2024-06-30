package Implematation.p16235_나무재테크;

import java.io.*;
import java.util.*;

public class p16235_YK {

    static int N, M, K;
    static Queue<Integer>[][] map;
    static int[][] energyMap, A;
    static Queue<Integer> tmp = new ArrayDeque<>();
    static Queue<Point> breeders = new ArrayDeque<>();
    static int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new Queue[N][N];
        energyMap = new int[N][N];
        A = new int[N][N];
        for (int i = 0; i < N; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; ++j) {
                map[i][j] = new PriorityQueue<>();
                energyMap[i][j] = 5;
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0, x, y, age; i < M; ++i) {
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken()) - 1;
            y = Integer.parseInt(st.nextToken()) - 1;
            age = Integer.parseInt(st.nextToken());
            map[x][y].add(age);
        }

        while (K-- > 0) {
            springSummer();
            autumn();
            winter();
        }

        int result = 0;
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                result += map[i][j].size();
            }
        }
        System.out.println(result);
        br.close();
    }

    private static void springSummer() {
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                int energy = 0;

                // 어린 순서대로 양분 먹기
                while (!map[i][j].isEmpty()) {
                    int tree = map[i][j].poll();
                    // 나이만큼 못먹으면 사망
                    if (energyMap[i][j] - tree < 0) {
                        energy += tree / 2;
                        continue;
                    }
                    // 나이만큼 먹었으면 1살 먹고 tmp에 넣기
                    energyMap[i][j] -= tree;
                    tmp.add(tree + 1);
                }
                // 살아남은 나무들 다시 맵에 넣기
                while (!tmp.isEmpty()) {
                    int tree = tmp.poll();
                    map[i][j].add(tree);
                    if (tree % 5 == 0) breeders.add(new Point(i, j));
                }
                // 여름 : 죽은 나무의 양분 더하기
                energyMap[i][j] += energy;
            }
        }
    }

    private static void autumn() {
        while (!breeders.isEmpty()) {
            Point tree = breeders.poll();
            for (int i = 0, nx, ny; i < 8; ++i) {
                nx = tree.x + dx[i];
                ny = tree.y + dy[i];
                if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
                map[nx][ny].add(1);
            }
        }
    }

    private static void winter() {
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                energyMap[i][j] += A[i][j];
            }
        }
    }
}
