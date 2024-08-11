package String.p9935_문자열폭발;

import java.io.*;
import java.util.*;

public class p9935_YK {

    static char[] tmpArr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        char[] input = br.readLine().toCharArray();
        Stack<Character> s = new Stack<>();
        char[] bomb = br.readLine().toCharArray();
        int M = bomb.length;
        tmpArr = new char[M];

        for (char c : input) {
            s.push(c);
            if (s.peek() == bomb[M - 1]) {
                explodeIfPossible(s, bomb, M);
            }
        }

        if (s.isEmpty()) sb.append("FRULA");
        Stack<Character> tmp = new Stack<>();
        while (!s.isEmpty()) {
            tmp.push(s.pop());
        }
        while (!tmp.isEmpty()) {
            sb.append(tmp.pop());
        }
        System.out.println(sb);
        br.close();
    }

    private static void explodeIfPossible(Stack<Character> s, char[] bomb, int m) {
        if (s.size() < m) return;

        for (int i = m - 1; i >= 0; --i) {
            tmpArr[i] = s.pop();
        }

        boolean canExplode = true;
        for (int i = 0; i < m; ++i) {
            if (tmpArr[i] != bomb[i]) {
                canExplode = false;
                break;
            }
        }

        if (!canExplode) {
            for (char c : tmpArr) {
                s.push(c);
            }
        }
    }
}
