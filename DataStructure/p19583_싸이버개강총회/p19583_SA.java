package DataStructure.p19583_싸이버개강총회;

import java.util.*;
import java.io.*;

public class p19583_SA {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static String S, E, Q;
    static int answer;
    public static void main(String[] args) throws Exception {
        // 입장-퇴장이 확인된 학회원은?
        // 시작 전, 채팅기록확인
        // 종료 후, 스트리밍을 끝낼 때까지 확인
        StringTokenizer st = new StringTokenizer(br.readLine());
        S = st.nextToken();
        E = st.nextToken();
        Q = st.nextToken();

        solve();

        System.out.println(answer);
    }

    static void solve() throws Exception{
        Set<String> nameSet = new HashSet<>();
        String line;
        while((line = br.readLine())!=null){
            StringTokenizer st = new StringTokenizer(line);

            String time = st.nextToken();
            String nickname = st.nextToken();

            // 개강총회 입회전
            if(time.compareTo(S)<=0){
                nameSet.add(nickname);
                continue;
            }

            if(time.compareTo(E)>=0 && time.compareTo(Q)<=0){
                if(nameSet.contains(nickname)){
                    nameSet.remove(nickname);
                    ++answer;
                }
            }
        }

    }
}