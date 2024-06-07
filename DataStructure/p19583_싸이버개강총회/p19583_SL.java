package DataStructure.p19583_싸이버개강총회;

import java.io.*;
import java.util.*;

public class p19583_SL {

    public static void main(String[] args) throws Exception {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 
        Set<String> before = new HashSet<>();
        Set<String> after = new HashSet<>();
        
        String line = br.readLine();
        String[] time = line.split(" ");
        
        while(true) {
            line = br.readLine();
            if(line == null) {
                break;
            }
            String[] info = line.split(" ");
            if(info[0].compareTo(time[0]) <= 0) {
                before.add(info[1]);
            } else if(info[0].compareTo(time[1]) >= 0 && info[0].compareTo(time[2]) <= 0) {
                after.add(info[1]);
            }
        }

        int answer = 0;
        for (String name : before) {
            if(after.contains(name)) {
                answer++;
            }
        }

        System.out.println(answer);
    }
}
