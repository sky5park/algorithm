package BJ;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
// BJ_17142: 연구소 - 19년도 상반기 연구소 문제(비트연산)
public class BJ_17142_1 {
	
	static class Point {
		int r, c, time;
		public Point(int r, int c, int time) {
			this.r = r;
			this.c = c;
			this.time = time;
		}
	}
	static int N, M;
	static int mat[][];
	static int dr[] = {-1, 1, 0, 0};
	static int dc[] = {0, 0, -1, 1};
	static ArrayList<Point> virus = new ArrayList<Point>();
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		mat = new int[N][N];
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				mat[i][j] = sc.nextInt();
				if(mat[i][j] == 2) {
					virus.add(new Point(i, j ,0));
				}
			}
		}
		
		System.out.println(solve());
	}
	
	private static int solve() {
		int ret = Integer.MAX_VALUE;
		
		for(int sub = 0; sub < 1 << virus.size(); sub++) {
			if(countBits(sub) == M) {
				boolean[][] visited = new boolean[N][N];
				int time = 0;
				Queue<Point> q = new LinkedList<Point>();
				for(int i =0; i<virus.size(); i++) {
					if((sub & 1 << i) != 0) {
						q.add(virus.get(i));
						visited[virus.get(i).r][virus.get(i).c] = true;
					}
				}
				while(!q.isEmpty()) {
					Point cur = q.poll();
					if(mat[cur.r][cur.c] == 0) {
						time = Integer.max(time, cur.time);
					}
					
					for(int k=0; k<4; k++) {
						int nR = cur.r + dr[k];
						int nC = cur.c + dc[k];
						if(nR < 0 || nR >= N || nC < 0 || nC >= N) continue;
						if(visited[nR][nC] || mat[nR][nC] == 1) continue;
						q.add(new Point(nR, nC, cur.time + 1));
						visited[nR][nC] = true;
					}
				}
				
				boolean flag = true;
				outer:
				for(int i=0 ;i<N; i++) {
					for(int j=0; j<N; j++) {
						if(mat[i][j] == 0 && !visited[i][j]) {
							flag = false;
							break outer;
						}
					}
				}
				
				if(flag) {
					ret = Integer.min(ret, time);
				}
			}
		}
		
		if(ret == Integer.MAX_VALUE) {
			ret = -1;
		}
		return ret;
	}
	
	private static int countBits(int n) {
		int cnt = 0;
		while(n > 0) {
			if((n & 1) == 1) cnt++;
			n = n >> 1;
		}
		return cnt;
	}
}
