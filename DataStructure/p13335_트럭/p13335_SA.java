package DataStructure.p13335_트럭;

import java.util.*;
import java.io.*;

public class p13335_SA {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int n, w, L;
	static int[] trucks;
	static int answer;

	public static void main(String[] args) throws Exception{
		// n개의 트럭이 건너가려고 함
		// 트럭 순서는 바꿀 수 없음
		// 다리 위에는 w대의 트럭만 올라갈 수 있음
		// 다리 길이: w
		// 다리 위에 올라가있는 트럭 최대 무게 합: L
		input();
		solve();
		System.out.println(answer);
	}

	static class Status{
		int idx;
		int pos;

		Status(int idx, int pos){
			this.idx = idx;
			this.pos = pos;
		}
	}

	static void solve(){
		Queue<Integer> waiting = new LinkedList<>();
		Queue<Status> q = new LinkedList<>();

		for(int i=0; i<n; ++i){
			waiting.add(i);
		}

		int sec = 0;
		int weight = 0;
		int cnt = 0;
		while(!waiting.isEmpty() || !q.isEmpty()){

			// 다리에 들어왔을 때
			int qsize = q.size();
			for(int i=0; i<qsize; ++i){
				Status curr = q.poll();

				if(curr.pos > w-1){
					weight -= trucks[curr.idx];
					--cnt;
					continue;
				}

				++curr.pos;
				q.add(curr);
			}

			++sec;
			// 다리에 들어가는 코드

			if(waiting.isEmpty()){
				//System.out.printf("[%d] %d %d%n", sec, cnt, weight);
				continue;
			}

			int candidate = waiting.peek();
			if(cnt < w && weight + trucks[candidate] < L + 1){
				++cnt;
				weight += trucks[candidate];
				waiting.poll();
				q.add(new Status(candidate,1));
			}
			//System.out.printf("[%d] %d %d%n", sec, cnt, weight);
		}
		answer = sec;
	}

	static void input() throws Exception{
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		w = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		trucks = new int[n];
		for(int i=0; i<n; ++i){
			trucks[i] = Integer.parseInt(st.nextToken());
		}
	}
}