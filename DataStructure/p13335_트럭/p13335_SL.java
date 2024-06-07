package DataStructure.p13335_트럭;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class p13335_SL {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		
		Queue<Integer> truck = new LinkedList<>();
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			truck.add(Integer.parseInt(st.nextToken()));
		}
		
		int time = 0;
		int ww = 0;
		Queue<Integer> bridge = new LinkedList<>();
		for(int i=0; i<W; i++) {
			bridge.add(0);
		}
		
		while(!bridge.isEmpty()) {
			time++;
			ww -= bridge.poll();
			
			if(!truck.isEmpty()) {
				if(truck.peek() + ww <= L) {
					ww += truck.peek();
					bridge.add(truck.poll());
				} else {
					bridge.add(0);
				}
			}
		}
		
		System.out.println(time);

	}

}
