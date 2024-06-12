package DataStructure.p19583_싸이버개강총회;

import java.util.*;
import java.io.*;

public class p3019_SA {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int C, P;
    static int[] height;
    static List<int[]>[] blocks;


    public static void main(String[] args) throws Exception
    {
        input();
        solve();
    }

    static void solve(){
        int cnt = 0;
        for(int start=0; start<C; ++start){
            for(int[] block : blocks[P]){
                int len = block.length;
                int minHeight = 100;
                boolean in = true;
                for(int c=0; c<len; ++c){
                    int base = start + c;
                    if(base > C-1){
                        in = false;
                        break;
                    }
                    minHeight = Math.min(minHeight, height[base]);
                }

                if(!in){
                    continue;
                }

                int checked = 0;
                for(int c=0; c<len; ++c){
                    int base = start + c;
                    if(height[base]+1 == block[c] + minHeight){
                        ++checked;
                        continue;
                    }
                    break;
                }

                if(checked == len){
                    // System.out.println(start);
                    // System.out.println(Arrays.toString(block));
                    // System.out.println("-------------");
                    ++cnt;
                }

            }
        }
        System.out.println(cnt);
    }

    static void input() throws Exception{
        StringTokenizer st = new StringTokenizer(br.readLine());
        C = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());

        height = new int[C];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<C; ++i){
            height[i] = Integer.parseInt(st.nextToken());
        }

        blocks = new List[8];
        for(int i=0; i<8; ++i){
            blocks[i] = new ArrayList<>();
        }

        blocks[1].add(new int[]{1});
        blocks[1].add(new int[]{1,1,1,1});

        blocks[2].add(new int[]{1,1});

        blocks[3].add(new int[]{1,1,2});
        blocks[3].add(new int[]{2,1});

        blocks[4].add(new int[]{2,1,1});
        blocks[4].add(new int[]{1,2});

        blocks[5].add(new int[]{1,1,1});
        blocks[5].add(new int[]{1,2});
        blocks[5].add(new int[]{2,1});
        blocks[5].add(new int[]{2,1,2});

        blocks[6].add(new int[]{1,1,1});
        blocks[6].add(new int[]{3,1});
        blocks[6].add(new int[]{1,2,2});
        blocks[6].add(new int[]{1,1});

        blocks[7].add(new int[]{1,1,1});
        blocks[7].add(new int[]{1,1});
        blocks[7].add(new int[]{2,2,1});
        blocks[7].add(new int[]{1,3});
    }
}
