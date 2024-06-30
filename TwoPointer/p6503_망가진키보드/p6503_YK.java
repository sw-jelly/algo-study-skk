package TwoPointer.p6503_망가진키보드;

import java.io.*;
import java.util.*;

public class p6503_YK {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while (true) {
            int M = Integer.parseInt(br.readLine());
            if (M == 0) break;
            char[] sentence = br.readLine().toCharArray();
            int N = sentence.length;
            Map<Character, Integer> charMap = new HashMap<>();

            int result = 1;
            int left = 0;
            charMap.put(sentence[left], 1);
            for (int right = 1; right < N; ++right) {
                charMap.put(sentence[right], charMap.getOrDefault(sentence[right], 0) + 1);
                while (left < right && charMap.size() > M) {
                    int cnt = charMap.get(sentence[left]);
                    if (cnt == 1) {
                        charMap.remove(sentence[left]);
                    } else {
                        charMap.put(sentence[left], cnt - 1);
                    }
                    ++left;
                }
                result = Math.max(result, right - left + 1);
            }

            sb.append(result).append("\n");
        }

        System.out.println(sb);
        br.close();
    }
}
