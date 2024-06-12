package Bitmasking.p5175_문제없는문제;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class p5175_SL {

    static int M, N;
    static List<Integer> resultList;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int tc = Integer.parseInt(st.nextToken());

        for(int t=1; t<=tc; t++) {

            st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken()); // 알고리즘 개수
            N = Integer.parseInt(st.nextToken()); // 문제 총 개수
            int[] arr = new int[N]; // 문제들의 알고리즘 비트마스크

            for(int i=0; i<N; i++) {
                st = new StringTokenizer(br.readLine());
                while (st.hasMoreTokens()) {
                    int alg = Integer.parseInt(st.nextToken());
                    arr[i] |= (1 << (alg-1)); //?
                }
            }

            resultList = new ArrayList<>();
            findMinResult(arr);

            System.out.print("Data Set "+ t + ": ");
            for(int i : resultList) {
                System.out.print((char)(i + 'A')+" ");
            }
            System.out.println();
            if(t < tc) {
                System.out.println();
            }
        }
    }

    public static void findMinResult(int[] arr) {
        int total = 1<<N;
        int minSize = N+1;

        for(int subset=1; subset<total; subset++) {
            int covered = 0;
            List<Integer> curList = new ArrayList<>();
            for(int i=0; i<N; i++) {
                if((subset & (1<<i)) != 0) {
                    covered |= arr[i];
                    curList.add(i);
                }
            }
            if(covered == (1<<M)-1) {
                if(curList.size() < minSize ||
                    (curList.size()==minSize && isSmaller(curList))) {
                    minSize = curList.size();
                    resultList = new ArrayList<>(curList);
                }
            }
        }
    }

    public static boolean isSmaller(List<Integer> curList) {

        for(int i=0; i<curList.size(); i++) {
            if(curList.get(i) < resultList.get(i)) {
                return true;
            } else if(curList.get(i) > resultList.get(i)) {
                return false;
            }
        }

        return false;
    }

}
