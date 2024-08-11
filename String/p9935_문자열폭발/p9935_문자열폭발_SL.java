package String.p9935_문자열폭발;

import java.util.*;
import java.io.*;

public class p9935_문자열폭발_SL {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        String boom = br.readLine();
        int boomSize = boom.length();

        Stack<Character> st = new Stack<>();

        for(int i=0; i<line.length(); i++) {
            st.push(line.charAt(i));

            // 문자열 길이 같아지면 확인
            if(st.size() >= boomSize) {
                boolean flag = true;

                for(int j=0; j<boomSize; j++) {
                    if(st.get(st.size()-boomSize+j) != boom.charAt(j)) {
                        flag = false;
                        break;
                    }
                }

                if(flag) { // 문자열 같아서 없앨 수 있으면
                    for(int j=0; j<boomSize; j++) {
                        st.pop();
                    }
                }
            }
        }

        if(st.isEmpty()) {
            System.out.println("FRULA");
        } else {
            StringBuilder sb = new StringBuilder();
            for(Character c : st) {
                sb.append(c);
            }
            System.out.println(sb);
        }


    }

}
