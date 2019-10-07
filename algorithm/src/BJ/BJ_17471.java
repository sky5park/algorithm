package BJ;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BJ_17471 {
	static int N;
	static int mat[][];
	static int pop[];
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		mat = new int[N][N];
		pop = new int[N];
		for(int i=0; i<N; i++) {
			pop[i] = sc.nextInt();
		}
		for(int i=0; i<N; i++) {
			int M = sc.nextInt();
			for(int m=0; m<M; m++) {
				int j = sc.nextInt() - 1;
				mat[i][j] = 1;
				mat[j][i] = 1;
			}
		}
		int ans = Integer.MAX_VALUE;
		boolean isOk = false;
		outer:
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
			boolean visited[] = new boolean[A.size()];
			Queue<Integer> q = new LinkedList<Integer>();
			q.add(A.get(0));
			visited[0] = true;
			while(!q.isEmpty()) {
				int cur = q.poll();
				for(int k = 0; k< A.size(); k++) {
					if(!visited[k] && mat[cur][A.get(k)] == 1) {
						visited[k] = true;
						q.add(A.get(k));
						
					}
				}
			}
			for(int i=0; i<visited.length; i++) {
				if(!visited[i]) {
					continue outer;
				}
			}
			
			q = new LinkedList<Integer>();
			visited = new boolean[B.size()];
			q.add(B.get(0));
			visited[0] = true;
			
			while(!q.isEmpty()) {
				int cur = q.poll();
				for(int k = 0; k< B.size(); k++) {
					if(!visited[k] && mat[cur][B.get(k)] == 1) {
						visited[k] = true;
						q.add(B.get(k));
						
					}
				}
			}
			for(int i=0; i<visited.length; i++) {
				if(!visited[i]) {
					continue outer;
				}
			}
			
			isOk = true;
			ans = Integer.min(ans, substit);
		}
		
		if(!isOk) {
			ans = -1;
		}
		
		System.out.println(ans);
		
	}

}
