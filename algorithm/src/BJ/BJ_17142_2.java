package BJ;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// BJ_17142: 연구소2 - 19년도 상반기 연구소 문제(일반 풀이)
public class BJ_17142_2 {
	static class Point {
		int r, c, time;
		public Point(Point p) {
			this.r = p.r;
			this.c = p.c;
			this.time = p.time;
		}
		public Point(int r, int c, int time) {
			this.r = r;
			this.c = c;
			this.time = time;
		}
	}
	static int N, M, ans;
	static int mat[][];
	static int dr[] = {-1, 1, 0, 0};
	static int dc[] = {0, 0, -1, 1};
	static ArrayList<Point> allow = new ArrayList<Point>();
	static ArrayList<Point> virus;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		mat = new int[N][N];
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				mat[i][j] = sc.nextInt();
				if(mat[i][j] == 2) {
					allow.add(new Point(i, j, 0));
				}
			}
		}
		ans = Integer.MAX_VALUE;
		
		for(int i =0; i< allow.size() -1; i++) {
			virus = new ArrayList<Point>();
			virus.add(allow.get(i));
			dfs(i, 1);
		}
		if(ans == Integer.MAX_VALUE) {
			ans = -1;
		}
		System.out.println(ans);
			
	}
	
	private static void dfs(int now, int cnt) {
		if(cnt == M) {
			bfs();
		}
		else {
			int size = allow.size();
			for(int i= now + 1; i<size; i++) {
				virus.add(allow.get(i));
				dfs(i, cnt + 1);
				virus.remove(virus.size()-1);
			}
		}
		
	}
	
	private static void bfs() {
		Queue<Point> q = new LinkedList<Point>();
		q.addAll(virus);
		boolean visited[][] = new boolean[N][N];
		int time = 0;
		while(!q.isEmpty()) {
			Point cur = q.poll();
			if(mat[cur.r][cur.c] == 0) {
				time = Integer.max(time, cur.time);
			}
			for(int k=0; k<4; k++) {
				int nR = cur.r + dr[k];
				int nC = cur.c + dc[k];
				if(nR < 0 || nR >= N || nC < 0 || nC >= N) continue;
				if(mat[nR][nC] == 1 || visited[nR][nC]) continue;
				visited[nR][nC] = true;
				q.add(new Point(nR, nC, cur.time + 1));
			}
		}
		
		
		boolean flag = true;
		outer:
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(mat[i][j] == 0 && !visited[i][j]) {
					flag = false;
					break outer;
				}
					
			}
		}
		if(flag) {
			ans = Integer.min(ans, time);
		}
	}
}

