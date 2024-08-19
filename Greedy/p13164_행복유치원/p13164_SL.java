package Greedy.p13164_행복유치원;

import java.io.*;
import java.util.*;

public class p13164_SL {

    static int N, K;
    static int[] arr;
    static ArrayList<Integer> list = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        if(K == 1) {
            int tmp = arr[N-1] - arr[0];
            System.out.println(tmp);
            return;
        }

        for(int i=0; i<N-1; i++) {
            int cha = arr[i+1] - arr[i];
            list.add(cha);
        }

        Collections.sort(list);

        // 2 2 1 4
        // 1 2 2 4
        // 5명을 3개의 조니까.. 앞에 2개(N-K 개) 합치면 됨.
        int answer = 0;
        for(int i=0; i<N-K; i++) {
            answer += list.get(i);
        }

        System.out.println(answer);


    }

}
