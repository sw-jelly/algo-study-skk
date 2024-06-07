package p14442_벽부수고이동하기2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class p14442_SL {

	static class Info {
		int x, y, dist, cnt;
		public Info(int x, int y, int dist, int cnt) {
			this.x = x;
			this.y = y;
			this.dist = dist;
			this.cnt = cnt;
		}
	}
	
	static int N,M,K;
	static int[][] map;
	static int result = -1;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static boolean[][][] visited;
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		
		for(int i=0; i<N; i++) {
			String line = br.readLine();
			for(int j=0; j<M; j++) {
				map[i][j] = line.charAt(j) - '0';
			}
		}
		
		visited = new boolean[N][M][K+1];
		bfs();
		System.out.println(result);
		
	}
	
	public static void bfs() {
		
		Queue<Info> q = new LinkedList<>();
		q.add(new Info(0, 0, 1, 0));
		visited[0][0][0] = true;
		
		while(!q.isEmpty()) {
			Info cur = q.poll();
			int x = cur.x;
			int y = cur.y;
			
			if(x == N-1 && y == M-1) {
				result = cur.dist;
				return;
			}
			
			for(int i=0; i<4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if(nx < 0 || N <= nx || ny < 0 || M <= ny) continue;
				
				if(map[nx][ny] == 0 && !visited[nx][ny][cur.cnt]) { // 벽 없을 때
					visited[nx][ny][cur.cnt] = true;
					q.add(new Info(nx, ny, cur.dist+1, cur.cnt));
				} else { // 벽 있을 때
					if(cur.cnt < K && !visited[nx][ny][cur.cnt+1]) {
						visited[nx][ny][cur.cnt+1] = true;
						q.add(new Info(nx, ny, cur.dist+1, cur.cnt+1));
					}
				}
				
			}
			
		}
		
		
	}
	

}
