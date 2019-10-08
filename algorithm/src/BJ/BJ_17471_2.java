package BJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_17471_2 {
	
	static int[] person;
	static int[][] way;
	static int[] area1;
	static int[] area2;
	static int answer;
	static int N;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine().trim());
		person = new int[N];
		way = new int[N][N];
		answer = -1;
		StringTokenizer st = new StringTokenizer(br.readLine().trim()," ");
		for(int i=0;i<N;i++) {
			person[i] = Integer.parseInt(st.nextToken());
			
		}
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine().trim()," ");
			int cnt = Integer.parseInt(st.nextToken());
			for(int j=0;j<cnt;j++) {
				int val = Integer.parseInt(st.nextToken());
				way[i][val-1] = 1;
			}
		}
		
		area1 = new int[N];
		area2 = new int[N];
		area1[0] = 0;
		splitAndCalcDiff(1,0,1);
		
		System.out.println(answer);
	}
	public static void splitAndCalcDiff(int top1, int top2, int idx) {
		if(answer == 0) {
			return;
		} else if(idx>=N) {
			if(top1 == 0 || top2==0) {
				return;
			} else {
				if(!isConnection(1, top1) || !isConnection(2, top2)) {
					return;
				}
				int total1 = 0;
				int total2 = 0;
				for(int i=0;i<top1;i++) {
					total1 += person[area1[i]];
				}
				for(int i=0;i<top2;i++) {
					total2 += person[area2[i]];
				}
				
				if(answer == -1 || Math.abs(total1-total2)<answer) {
					answer = Math.abs(total1-total2);
				}
				
			}
		} else {
			area1[top1] = idx;
			splitAndCalcDiff(top1+1, top2, idx+1);
			area2[top2] = idx;
			splitAndCalcDiff(top1, top2+1, idx+1);
		}
	}
	
	public static boolean isConnection(int arrNum, int arrSize) {
		int[] arr = (arrNum==1)? area1 : area2;
		int homeNode = arr[0];
		
		Queue<Integer> queue = new LinkedList<>(); 
		boolean[] visited = new boolean[N];
		for(int i=1;i<arrSize;i++) {
			// initialize values
			queue.clear();
			for(int j=0;j<N;j++) {
				visited[j] = false;
			}
			// set values
			queue.add(arr[i]);
			visited[arr[i]] = true;
			boolean isConnect = false;
			
			while(!queue.isEmpty()) {
				int now = queue.poll();
				for(int j=0;j<arrSize;j++) {
					if(way[now][arr[j]]==1 && !visited[arr[j]]) {
						if(arr[j]==homeNode) {
							isConnect = true;
							break;
						} else {
							queue.add(arr[j]);
							visited[arr[j]] = true;
						}
					}
				}
			}
			if(!isConnect) {
				return false;
			}
		}
		return true;
	}
}

