package Implematation.p16235_나무재테크;

import java.util.*;
import java.io.*;

public class p16235_SL {

    static class Tree implements Comparable<Tree>{
        int x, y, age;

        public Tree(int x, int y, int age) {
            this.x = x;
            this.y = y;
            this.age = age;
        }

        @Override
        public int compareTo(Tree o) {
            return this.age - o.age;
        }
    }

    static int N, M, K;
    static int[][] map; // x, y, 양분
    static int[][] amount; // x, y, 추가 양분
    static ArrayList<Tree>[][] trees;
    static ArrayList<Tree> dead = new ArrayList<>();
    static int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        amount = new int[N][N];
        trees = new ArrayList[N][N];

        // 초기 양분, 추가 양분 입력
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                map[i][j] = 5;
                amount[i][j] = Integer.parseInt(st.nextToken());
                trees[i][j] = new ArrayList<>();
            }
        }

        // 나무 정보 입력
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int age = Integer.parseInt(st.nextToken());
            trees[x][y].add(new Tree(x, y, age));
        }

        // K년 동안 반복
        for(int i=0; i<K; i++) {
            springAndSummer();
            fall();
            winter();
        }

        System.out.println(countTrees());

    }

    // 봄 : 나무가 나이만큼 양분먹음, 나이 1 증가
    // 부족해서 양분 못 먹으면 즉시 죽음
    // 여름 : 죽은 나무가 양분으로 변함 => 죽은 나무마다 나이/2 추가
    private static void springAndSummer() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (trees[i][j].isEmpty()) continue;

                Collections.sort(trees[i][j]);

                List<Tree> alive = new ArrayList<>();
                for (Tree tree : trees[i][j]) {
                    if (map[tree.x][tree.y] >= tree.age) {
                        map[tree.x][tree.y] -= tree.age;
                        tree.age++;
                        alive.add(tree);
                    } else {
                        dead.add(tree);
                    }
                }
                trees[i][j].clear();
                trees[i][j].addAll(alive);
            }
        }

        // 여름: 죽은 나무를 양분으로 변환
        for (Tree tree : dead) {
            map[tree.x][tree.y] += tree.age / 2;
        }
        dead.clear();
    }

    // 가을 : 나이가 5의 배수인 나무 번식, 인접한 8개 칸에 나이 1 나무 생성
    private static void fall() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k=0; k<trees[i][j].size(); k++) {
                    Tree tree = trees[i][j].get(k);
                    if (tree.age % 5 == 0) {
                        for (int d = 0; d < 8; d++) {
                            int nx = tree.x + dx[d];
                            int ny = tree.y + dy[d];
                            if (nx >= 0 && ny >= 0 && nx < N && ny < N) {
                                trees[nx][ny].add(new Tree(nx, ny, 1));
                            }
                        }
                    }
                }
            }
        }
    }

    // 겨울 : 각 칸에 양분 추가
    private static void winter() {
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                map[i][j] += amount[i][j];
            }
        }
    }

    // 나무의 총 개수 세기
    private static int countTrees() {
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                count += trees[i][j].size();
            }
        }
        return count;
    }

}
