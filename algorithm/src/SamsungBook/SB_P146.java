package SamsungBook;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
// »ï¼º Ã¥ È­»êÆø¹ß 2 ¹®Á¦
public class SB_P146 {
	static class Point {
		int r, c;
		
		public Point() {
			
		}
		
		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	static int N, M;
	static int mat[][];
	static int ans;
	static int dr[] = {1, -1, 0, 0};
	static int dc[] = {0, 0, -1, 1};
	static ArrayList<Point> mList = new ArrayList<Point>();
	static ArrayList<Point> allow = new ArrayList<Point>();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		mat = new int[N][N];
		
		for(int i =0; i<N;i++) {
			for(int j=0; j<N; j++) {
				mat[i][j] = sc.nextInt();
				if(mat[i][j] == 0) {
					allow.add(new Point(i, j));
				}
				else if(mat[i][j] == 2) {
					mList.add(new Point(i, j));
				}
			}
		}
		
		ans = 0;
		for(int i =0; i<allow.size() -1; i++) {
			mat[allow.get(i).r][allow.get(i).c] = 1;
			dfs(i, 1);
			mat[allow.get(i).r][allow.get(i).c] = 0;
		}
		
		System.out.println(ans);
	}
	
	static private void dfs(int now, int cnt) {
		if(cnt == M) {
			bfs();
		}
		else {
			for(int i =now + 1; i<allow.size(); i++) {
				int nR = allow.get(i).r;
				int nC = allow.get(i).c;
				mat[nR][nC] = 1;
				dfs(i, cnt + 1);
				mat[nR][nC] = 0;
			}
		}
	}
	
	static private void bfs() {
		Queue<Point> q = new LinkedList<Point>();
		boolean visited[][] = new boolean[N][N];
		int cmat[][] = new int[N][N];
		for(int i =0; i<N; i++) {
			for(int j =0; j<N; j++) {
				cmat[i][j] = mat[i][j];
			}
		}
		
		for(Point m: mList) {
			q.add(m);
			visited[m.r][m.c] = true;
		}
		while(!q.isEmpty()) {
			Point cur = q.poll();
			for(int k=0; k<4; k++) {
				int nR = cur.r + dr[k];
				int nC = cur.c + dc[k];
				if(nR < 0 || nR >= N || nC < 0 || nC >= N) continue;
				if(cmat[nR][nC] != 0 || visited[nR][nC]) continue;
				q.add(new Point(nR, nC));
				visited[nR][nC] = true;
			}
		}
		
		int cnt = 0;
		for(int i =0; i<N; i++) {
			for(int j =0; j<N; j++) {
				if(mat[i][j] == 0 && !visited[i][j]) cnt++;
			}
		}
		
		ans = Integer.max(ans, cnt);
	}
}

