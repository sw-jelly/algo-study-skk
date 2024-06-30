package TwoPointer.p6503_망가진키보드;

import java.util.*;
import java.io.*;

public class p6503_SA  {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws Exception {

        while(true){
            int m = Integer.parseInt(br.readLine());
            if(m == 0){
                break;
            }

            String str = br.readLine();
            solve(m, str);
        }

        System.out.print(sb);
    }

    public static void solve(int m, String str){
        int len = str.length();

        int[] checked = new int[128];
        int cnt = 0;
        int lo = -1;
        int hi = -1;
        int ans = 1;
        while(lo <= hi && hi < len-1){
            // 아직 m개의 글자가 등장하지 않았거나
            // m개의 글자인 경우, 다음 글자가 이전에 나온 글자와 같은 경우
            if (cnt < m || (cnt == m && checked[str.charAt(hi+1)] > 0)){
                ++hi;
                if(checked[str.charAt(hi)]++ == 0){
                    ++cnt;
                }
            } else {
                ++lo;
                if(--checked[str.charAt(lo)] == 0){
                    --cnt;
                }
            }

            ans = Math.max(hi-lo, ans);
        }
        sb.append(ans).append("\n");
    }

}