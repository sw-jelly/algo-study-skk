package p16437_양구출작전;

import java.io.*;
import java.util.*;

public class p16437_SL {

    static char[] cArr;
    static long[] nArr;
    static ArrayList<Integer>[] list;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 섬 개수 N, 늑대 W, 양 S
        int N = Integer.parseInt(st.nextToken());

        nArr = new long[N+1];
        cArr = new char[N+1];
        list = new ArrayList[N+1];
        for(int i=0; i<=N; i++) {
            list[i] = new ArrayList<>();
        }

        for(int i=2; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            char type = st.nextToken().charAt(0);
            long num = Long.parseLong(st.nextToken());
            int parent = Integer.parseInt(st.nextToken());
            cArr[i] = type;
            nArr[i] = num;
            list[parent].add(i);
        }

        System.out.println(go(1));

    }

    public static long go(int idx) {
        long sum = 0;
        for(int i=0; i<list[idx].size(); i++) {
            int next = list[idx].get(i);
            sum += go(next);
        }

        if(cArr[idx] == 'S') {
            return sum + nArr[idx];
        } else {
            return Math.max(0, sum - nArr[idx]);
        }
    }

}
