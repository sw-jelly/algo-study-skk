package Bitmasking.p5175_문제없는문제;

import java.util.*;
import java.io.*;

public class p5174_SA {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int K, M, N;
    static StringBuilder sb = new StringBuilder();

    static int[] numbers;
    static int exit;
    static int minCnt;
    static int minProblem;

    public static void main(String[] args) throws Exception{
        K = Integer.parseInt(br.readLine());
        for(int i=0; i<K; ++i){
            StringTokenizer st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken()); // 알고리즘 수
            N = Integer.parseInt(st.nextToken()); // 문제 수

            // 비트마스킹으로 알고리즘 수 저장
            numbers = new int[N];
            exit = 0;
            minCnt = 21;
            minProblem = Integer.MAX_VALUE;
            for(int j=0; j<M; ++j){
                exit |= (1<<j);
            }

            for(int j=0; j<N; ++j){
                st = new StringTokenizer(br.readLine());

                int num = 0;
                String token;
                while(st.hasMoreTokens()){
                    int curr = Integer.parseInt(st.nextToken())-1;
                    num |= (1<<curr);
                }
                numbers[j] = num;
            }
            solve(0,0,0,0);

            sb.append("Data Set ").append(i+1).append(": ");
            for(int j=0; j<N; ++j){
                if((minProblem & (1<<j))!=0){
                    sb.append((char)(j + 'A')).append(" ");
                }
            }
            sb.append("\n\n");
        }

        System.out.println(sb);
    }

    static void solve(int curr, int visited, int checked, int cnt){

        if(curr == N || visited == exit){
            if(visited!=exit){
                return;
            }

            if(minCnt > cnt){
                minProblem = checked;
                minCnt = cnt;
            }
            return;
        }

        solve(curr+1, visited | numbers[curr], checked | (1<<curr),cnt+1);
        solve(curr+1, visited, checked, cnt);
    }
}
