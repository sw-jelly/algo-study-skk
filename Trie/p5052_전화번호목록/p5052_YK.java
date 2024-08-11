package Trie.p5052_전화번호목록;

import java.io.*;
import java.util.*;

public class p5052_YK {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        boolean flag;
        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            String[] phonebook = new String[N];
            flag = false;
            for (int i = 0; i < N; ++i) {
                phonebook[i] = br.readLine();
            }

            Arrays.sort(phonebook);
            for (int i = 0; i < N - 1; ++i) {
                if (isPrefix(phonebook[i], phonebook[i + 1])) {
                    flag = true;
                    break;
                }
            }

            if (flag) sb.append("NO");
            else sb.append("YES");
            sb.append("\n");
        }

        System.out.println(sb);
        br.close();
    }

    private static boolean isPrefix(String s1, String s2) {
        if (s1.length() >= s2.length()) return false;
        for (int i = 0, size = s1.length(); i < size; ++i) {
            if (s1.charAt(i) != s2.charAt(i)) return false;
        }
        return true;
    }
}
