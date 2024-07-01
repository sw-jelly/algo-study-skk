package TwoPointer.p6503_망가진키보드;

import java.io.*;
import java.util.*;

// 투포인터
public class p6503_SL {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 서로 다른 문자 최대 m개로 이루어진 가장 긴 부분 문자열 찾기
        while(true) {
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken()); // 서로 다른 문자 개수
            if (m == 0) break;

            String line = br.readLine();
            int[] alphabet = new int[128]; // 알파벳 개수 저장 아스키코드는 128개
            int left = 0, right = 0, max = 0; // 왼쪽, 오른쪽 포인터, 최대 길이
            int count = 0; // 서로 다른 문자 개수

            // 오른쪽 포인터 이동
            while(right < line.length()) {
                // 해당 문자가 처음 나온 경우
                if (alphabet[line.charAt(right)] == 0) {
                    count++;
                }
                // 해당 문자 개수 증가
                alphabet[line.charAt(right)]++;
                // 오른쪽 포인터 이동
                right++;

                // 서로 다른 문자가 m개보다 크면 왼쪽 포인터 이동
                while(count > m) {
                    // 해당 문자 개수 감소
                    alphabet[line.charAt(left)]--;
                    // 해당 문자가 0개가 되면 count 감소
                    if (alphabet[line.charAt(left)] == 0) {
                        count--;
                    }
                    // 왼쪽 포인터 이동
                    left++;
                }

                // 최대 길이 갱신
                max = Math.max(max, right - left);
            }

            System.out.println(max);
        }


    }

}
