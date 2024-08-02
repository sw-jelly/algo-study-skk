package TopologySort.p20119_클레어와물약;

import java.io.*;
import java.util.*;

public class p20119_SL {

    static int N, M; // N종류 물약, M개 레시피

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        ArrayList<Integer>[] list = new ArrayList[N+1];
        int[] target = new int[M];
        int[] inputCount = new int[M];
        Boolean[] visited = new Boolean[N+1];

        for(int i=0; i<=N; i++) {
            visited[i] = false;
            list[i] = new ArrayList<>();
        }

        // inputCount        target
        // 3 3 2 2 2        2 7 5 3 4

        // list[1] 0 1 : 1로 만들 수 있는 target의 index
        // list[2]
        // list[3] 1 2
        // list[4] 2 3
        // list[5] 0 3 4
        // list[6] 1 4
        // list[7] 0

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()); // 재료 개수
            inputCount[i] = a;
            for(int j=0; j<a; j++) {
                int b = Integer.parseInt(st.nextToken()); // 재료 숫자
                list[b].add(i); // b번 물약은 index가 i인 target을 만들 수 있음.
            }
            target[i] = Integer.parseInt(st.nextToken()); // 만들 수 있는 숫자
        }

        st = new StringTokenizer(br.readLine());
        int L = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        Queue<Integer> q = new LinkedList<>();
        for(int i=0; i<L; i++) {
            int what = Integer.parseInt(st.nextToken()); // 만들 숫자
            q.add(what);
            visited[what] = true;
        }

        while(!q.isEmpty()) {
            int tmp = q.poll();

            for(int i=0; i<list[tmp].size(); i++) {
                int next = list[tmp].get(i);

                inputCount[next]--;
                if(inputCount[next] == 0 && (!visited[target[next]])) {
                    visited[target[next]] = true; // 만들 수 있음
                    q.add(target[next]);
                }
            }

        }

        int cnt = 0;
        StringBuilder sb = new StringBuilder();
        for(int i=1; i<=N; i++) {
            if(visited[i]) {
                cnt++;
                sb.append(i).append(" ");
            }
        }
        System.out.println(cnt);
        System.out.println(sb);

    }
}

