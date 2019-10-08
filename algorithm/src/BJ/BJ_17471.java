package BJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// BJ_17471: 게리맨더링
public class BJ_17471 {
	static int N;
	static int mat[][];
	static int pop[];
	static boolean visited[];
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine().trim());
		mat = new int[N][N];
		pop = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine().trim(), " ");
		for(int i=0; i<N; i++) {
			pop[i] = Integer.parseInt(st.nextToken());
		}
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine().trim(), " ");
			int M = Integer.parseInt(st.nextToken());
			for(int m=0; m<M; m++) {
				int j = Integer.parseInt(st.nextToken()) - 1;
				mat[i][j] = 1;
				mat[j][i] = 1;
			}
		}
		int ans = Integer.MAX_VALUE;
		boolean isOk = false;
		for(int sub=1; sub< (1 << N) - 1; sub++) {
			int sumA = 0;
			int sumB = 0;
			ArrayList<Integer> A = new ArrayList<Integer>();
			ArrayList<Integer> B = new ArrayList<Integer>();
			for(int k =0; k<N; k++) {
				if((sub & 1 << k) != 0) {
					sumA += pop[k];
					A.add(k);
				}
				else {
					sumB += pop[k];
					B.add(k);
				}
			}
			int substit = Math.abs(sumA - sumB);
			if(substit >= ans) continue;
			
			bfs(A);
			if(!isConnected()) {
				continue;
			}
			
			bfs(B);
			if(!isConnected()) {
				continue;
			}
			isOk = true;
			ans = Integer.min(ans, substit);
		}
		
		if(!isOk) {
			ans = -1;
		}
		System.out.println(ans);
	}
	
	private static void bfs(ArrayList<Integer> list) {
		Queue<Integer> q = new LinkedList<Integer>();
		visited = new boolean[list.size()];
		visited[0] = true;
		q.add(list.get(0));
		while(!q.isEmpty()) {
			int cur = q.poll();
			for(int k = 0; k< list.size(); k++) {
				if(!visited[k] && mat[cur][list.get(k)] == 1) {
					visited[k] = true;
					q.add(list.get(k));
				}
			}
		}
	}
	
	private static boolean isConnected() {
		for(int i=0; i<visited.length; i++) {
			if(!visited[i]) {
				return false;
			}
		}
		return true;
	}

}
