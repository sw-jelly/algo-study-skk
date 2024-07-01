import java.util.*;
import java.io.*;

public class p1713_SA {

    static class Status{
        int no;
        int recomm;
        int time;

        Status(int no, int recomm, int time){
            this.no = no;
            this.recomm = recomm;
            this.time = time;
        }

        @Override
        public boolean equals(Object obj){
            Status curr = (Status) obj;
            return (curr.no == this.no);
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.no);
        }

    }

    static int N, M;
    static int[] recomms;

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws Exception{
        input();
        solve();
    }

    static void solve(){

        PriorityQueue<Status> pq = new PriorityQueue<>(
                (s1, s2)->{
                    if(s1.recomm == s2.recomm){
                        return s1.time - s2.time;
                    }
                    return s1.recomm - s2.recomm;
                }
        );

        Status[] list = new Status[101];

        int time = 0;
        for(int recomm : recomms){
            // 현재 사진이 게시된 학생이 추천을 받은 경우 -> 추천 수 증가
            ++time;
            if(list[recomm] != null){
                pq.remove(list[recomm]);
                ++list[recomm].recomm;
                pq.add(list[recomm]);
                continue;
            }

            Status newStatus = new Status(recomm, 1, time);
            // 비어있는 사진틀이 있는 경우
            if(pq.size() < N){
                list[recomm] = newStatus;
                pq.add(newStatus);
                continue;
            }

            // 비어있는 사진틀이 없는 경우
            // 조건에 맞춰서 삭제
            Status removed = pq.poll();
            list[removed.no] = null;

            list[recomm] = newStatus;
            pq.add(newStatus);
        }

        List<Status> answers = new ArrayList<>();
        while(!pq.isEmpty()){
            answers.add(pq.poll());
        }

        Collections.sort(answers, (s1,s2)->(s1.no-s2.no));
        StringBuilder sb = new StringBuilder();
        for(Status s : answers){
            sb.append(s.no).append(" ");
        }
        System.out.println(sb);
    }

    static void input() throws Exception{
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        recomms = new int[M];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<M; ++i){
            recomms[i] = Integer.parseInt(st.nextToken());
        }
    }
}
