package DataStructure.p3019_테트리스;

import java.io.*;
import java.util.*;

public class p3019_YK {

    static int C, P, result;
    static boolean[][] map;
    static int[] heights;

    static int[] rotates = {2, 1, 2, 2, 4, 4, 4};
    static class Block {
        int[] x, y;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        C = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken()) - 1;
        map = new boolean[105][C];
        heights = new int[C];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < C; ++i) {
            heights[i] = Integer.parseInt(st.nextToken());
            for (int j = 0; j < heights[i]; ++j) {
                map[j][i] = true;
            }
        }

        Block[] blocks = makeBlocks();
        Block block = blocks[P];

        for (int i = 0; i < C; ++i) {
            for (int j = 0; j < rotates[P]; ++j) {
                if (putBlock(block, i)) ++result;
                rotateBlock90(block);
            }
        }

        System.out.println(result);
        br.close();
    }

    private static boolean putBlock(Block block, int y) {
        int[] bx = new int[4];
        int[] by = new int[4];

        for (int i = 0; i < 4; ++i) {
            bx[i] = heights[y] + block.x[i];
            by[i] = y + block.y[i];
        }

        for (int i = 0; i < 4; ++i) {
            // 범위 밖
            if (bx[i] < 0 || by[i] < 0 || by[i] >= C) return false;
            // 이미 블록 있음
            if (map[bx[i]][by[i]]) return false;
        }

        // 블록과 블록 또는 블록과 바닥 사이에 채워지지 않은 칸이 없는지
        for (int i = 0; i < 4; ++i) {
            map[bx[i]][by[i]] = true;
        }

        boolean flag = true;
        for (int i = 0; i < 4; ++i) {
            if (bx[i] - 1 < 0) continue;
            if (!map[bx[i] - 1][by[i]]) {
                flag = false;
                break;
            }
        }

        for (int i = 0; i < 4; ++i) {
            map[bx[i]][by[i]] = false;
        }

        return flag;
    }

    private static void rotateBlock90(Block block) {
        for (int i = 0; i < 4; ++i) {
            int tmpX = block.x[i];
            block.x[i] = -block.y[i];
            block.y[i] = tmpX;
        }

        // 회전 후 가장 작은 x, y 값을 찾아서 (0,0) 위치로 조정
        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;
        for (int i = 0; i < 4; ++i) {
            if (block.x[i] < minX || (block.x[i] == minX && block.y[i] < minY)) {
                minX = block.x[i];
                minY = block.y[i];
            }
        }
        for (int i = 0; i < 4; ++i) {
            block.x[i] -= minX;
            block.y[i] -= minY;
        }
    }


    private static Block[] makeBlocks() {
        Block[] blocks = new Block[7];

        for (int i = 0; i < 7; ++i) {
            blocks[i] = new Block();
        }

        // 기준 왼쪽 위
        blocks[0].x = new int[] {0, 1, 2, 3};
        blocks[0].y = new int[] {0, 0, 0, 0};

        blocks[1].x = new int[] {0, 0, 1, 1};
        blocks[1].y = new int[] {0, 1, 0, 1};

        blocks[2].x = new int[] {0, 0, 1, 1};
        blocks[2].y = new int[] {0, 1, 1, 2};

        blocks[3].x = new int[] {0, 0, -1, -1};
        blocks[3].y = new int[] {0, 1, 1, 2};

        blocks[4].x = new int[] {0, 0, -1, 0};
        blocks[4].y = new int[] {0, 1, 1, 2};

        blocks[5].x = new int[] {0, -1, 0, 0};
        blocks[5].y = new int[] {0, 0, 1, 2};

        blocks[6].x = new int[] {0, 0, 0, -1};
        blocks[6].y = new int[] {0, 1, 2, 2};

        return blocks;
    }
}
