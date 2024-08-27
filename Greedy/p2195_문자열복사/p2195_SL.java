package Greedy.p2195_문자열복사;

import java.io.*;
import java.util.*;

public class p2195_SL {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s = br.readLine();
        String target = br.readLine();

        int idx = 0;
        int cnt = 0;

        for(int i=0; i<target.length(); i++) {
            // 문자열이 s 안에 존재 X면, indexOf는 -1 반환.
            // 존재하면
            if(s.indexOf(target.substring(idx, i+1)) != -1) continue;
            cnt++;
            idx = i;
        }

        // a a a b b b a b b b a a a
        // aa  abb   ba  bb  baa   a
        // 00  222   55  77  999   12 <- index

        System.out.println(cnt + 1);

    }

}
