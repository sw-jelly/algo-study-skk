package p20166_문자열지옥에빠진호석;

import java.util.*;
import java.io.*;

public class p20166_SA {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N, M, K;
	static char[][] map;
	static Map<String, Integer> counter = new HashMap<>();

	static int[] dr = {-1, -1, -1, 0, 0, 1, 1, 1};
	static int[] dc = {0, -1, 1, 1, -1, 0, -1, 1};
	public static void main(String[] args) throws Exception{
		input();
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<N; ++i){
			for(int j=0; j<M; ++j){
				sb.append(map[i][j]);
				go(i, j, sb);
				sb.deleteCharAt(0);
			}
		}
		output();
	}

	static void output() throws Exception{
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<K; ++i){
			String query = br.readLine();

			if(!counter.containsKey(query)){
				sb.append(0).append("\n");
				continue;
			}

			sb.append(counter.get(query)).append("\n");
		}
		System.out.print(sb);
	}

	static void go(int r, int c, StringBuilder sb){
		if(sb.length() > 5){
			return;
		}

		String str = sb.toString();
		if(counter.containsKey(str)){
			int cnt = counter.get(str);
			//System.out.printf("%s %d%n", str, cnt);
			counter.put(str, cnt+1);
		} else{
			//System.out.printf("%s %d%n", str, 1);
			counter.put(str, 1);
		}

		for(int d=0; d<8; ++d){
			int nr = (r + dr[d]+N)%N;
			int nc = (c + dc[d] + M)%M;

			sb.append(map[nr][nc]);
			go(nr, nc, sb);
			sb.deleteCharAt(sb.length() - 1);
		}
	}


	static void input() throws Exception{
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new char[N][M];
		for(int i=0; i<N; ++i){
			String str = br.readLine();
			for(int j=0; j<M; ++j){
				map[i][j] = str.charAt(j);
			}
		}
	}
}