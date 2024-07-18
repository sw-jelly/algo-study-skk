package Greedy.p1339_단어수학;

import java.io.*;
import java.util.*;

public class p1339_SL {

    static int N;
    static int[] alphabet = new int[26]; // 알파벳에 대응하는 숫자

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        for(int i=0; i<N; i++) {
            String line = br.readLine();
            for(int j=0; j<line.length(); j++) {
                char c = line.charAt(j);
                alphabet[c-'A'] += (int) Math.pow(10, line.length()-1-j);
            }
        }

        Arrays.sort(alphabet); // 오름차순

        int num = 9;
        int turn = 25;
        int ans = 0;
        while(alphabet[turn] != 0) {
            ans += alphabet[turn] * num;
            turn--;
            num--;
        }

        System.out.println(ans);

    }
}
