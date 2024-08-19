package UnionFind.p10775_공항;

import java.util.*;
import java.io.*;

// 공항, 유니온파인드
// https://eastplanet.tistory.com/212

public class p10775_SL {

    static int find(int a) {
        if(parent[a] == a) return a;
        return parent[a] = find(parent[a]);
    }

    static boolean union(int a, int b) {
        a = find(a);
        b = find(b);
        if(a == b) {
            return false;
        }
        parent[b] = a;
        return true;
    }

    static int G, P;
    static int[] parent;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 공항에 G개의 게이트,
        // P개의 비행기가 순서대로 도착, 게이트 숫자 이하로 들어가기 가능
        G = Integer.parseInt(br.readLine());
        P = Integer.parseInt(br.readLine());

        parent = new int[G+1];
        for(int i=1; i<=G; i++) {
            parent[i] = i;
        }

        int answer = 0;
        for(int i=0; i<P; i++) {
            int g = Integer.parseInt(br.readLine());
            int emptyGate = find(g);

            if(emptyGate == 0) break;

            answer++;

            union(emptyGate-1, emptyGate);
        }

        System.out.println(answer);

    }
}
