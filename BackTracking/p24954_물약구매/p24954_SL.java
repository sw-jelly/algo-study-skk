package BackTracking.p24954_물약구매;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class p24954_SL {

    static class Info {
        int to, discount;
        public Info(int to, int discount) {
            this.to = to;
            this.discount = discount;
        }
    }

    static int N;
    static int[] arr;
    static ArrayList<Info>[] discountList;
    static int min = 2_000_000_000;

    static int[] data;
    static int[] num;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        discountList = new ArrayList[N];
        for(int i=0; i<N; i++) {
            discountList[i] = new ArrayList<>();
        }

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int cnt = Integer.parseInt(st.nextToken());

            for(int j=0; j<cnt; j++) {
                st = new StringTokenizer(br.readLine());
                int to = Integer.parseInt(st.nextToken())-1;
                int discount = Integer.parseInt(st.nextToken());
                discountList[i].add(new Info(to, discount));
            }
        }

        // N개중에 N개 뽑기 permutation
        data = new int[N];
        num = new int[N];
        visited = new boolean[N];
        for(int i=0; i<N; i++) {
            data[i] = i;
        }
        per(0);
        System.out.println(min);

    }

    private static void solve(int[] perArr) {
        int sum = 0;

        int[] priceArr = new int[N];
        for(int i=0; i<N; i++) {
            priceArr[i] = arr[i];
        }

        for(int i=0; i<N; i++) {
            int idx = perArr[i]; // idx 순서
            int price = priceArr[idx]; // 바뀐 가격

            sum += price;
//            System.out.println(i + " 가격 : " + price);

            for(int j=0; j<discountList[idx].size(); j++) { // 가격 바꾸기
                Info info = discountList[idx].get(j);
                int nextPrice = priceArr[info.to] - info.discount;
                if(nextPrice <= 0) {
                    nextPrice = 1;
                }
                priceArr[info.to] = nextPrice;
            }

        }
        min = Math.min(min, sum);
    }

    private static void per(int cnt) {

        if(cnt == N) {
//            System.out.println(Arrays.toString(num));
            solve(num);
            return;
        }

        for(int i=0; i<N; i++) {
            if(!visited[i]) {
                visited[i] = true;
                num[cnt] = data[i];
                per(cnt+1);
                visited[i] = false;
            }
        }

    }

}
