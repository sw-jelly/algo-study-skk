package p20166_문자열지옥에빠진호석;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class p20166_SL {

	static int N,M,K;
	static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};
	static char[][] map;
	static String[] words;
	static HashMap<String, Integer> hm;
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		words = new String[K];
		hm = new HashMap<>();
		
		for(int i=0; i<N; i++) {
			String line = br.readLine();
			for(int j=0; j<M; j++) {
				map[i][j] = line.charAt(j);
			}
		}
		
		for(int i=0; i<K; i++) {
			String line2 = br.readLine();
			words[i] = line2;
			hm.put(words[i], 0);
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				dfs(i, j, 1, String.valueOf(map[i][j]));
			}
		}
		
		StringBuilder answer = new StringBuilder();
		for(String key : words) {
			answer.append(hm.get(key) + "\n");
		}
		System.out.println(answer);

	}

	private static void dfs(int x, int y, int cnt, String s) {

		if(hm.containsKey(s)) {
			hm.put(s, hm.get(s) + 1);
		}
		
		if(cnt > 5) {
			return;
		}
		
		for(int d=0; d<8; d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];
			
			if(nx < 0) {
				nx = N - 1;
			} else if (nx > N-1) {
				nx = 0;
			}
			
			if(ny < 0) {
				ny = M - 1;
			} else if (ny > M-1) {
				ny = 0;
			}
			
			dfs(nx, ny, cnt+1, s + map[nx][ny]);
		}
		
		
	}	
	
	
	
	

}
