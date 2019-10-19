package BJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_17144_Re {
	static class Point {
		int r, c;
		public Point (int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	static class Dust {
		int r, c, dust;
		public Dust(int r, int c, int dust) {
			this.r = r;
			this.c = c;
			this.dust = dust;
		}
	}
	static int R, C, T;
	static int mat[][], cmat[][];
	static int dr[] = {-1, 1, 0, 0};
	static int dc[] = {0, 0, -1, 1};
	static int dir1[] = {3, 0, 2, 1};
	static int dir2[] = {3, 1, 2, 0};
	static ArrayList<Point> m = new ArrayList<Point>();
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim(), " ");
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		mat = new int[R][C];
		for(int i=0; i<R; i++) {
			st = new StringTokenizer(br.readLine().trim(), " ");
			for(int j=0; j<C; j++) {
				mat[i][j] = Integer.parseInt(st.nextToken());
				if(mat[i][j] == -1) {
					m.add(new Point(i, j));
				}
			}
		}
		
		while(T-- > 0) {
			// 확산
			Queue<Dust> q = new LinkedList<Dust>();
			for(int i=0; i<R; i++) {
				for(int j=0; j<C; j++) {
					if(mat[i][j] > 4) {
						q.add(new Dust(i, j, mat[i][j]));
					}
				}
			}
			
			while(!q.isEmpty()) {
				Dust cur = q.poll();
				int divide = cur.dust / 5;
				int cnt = 0;
				for(int k=0; k<4; k++) {
					int nR = cur.r + dr[k];
					int nC = cur.c + dc[k];
					if(nR <0 || nR >= R || nC < 0 || nC >= C || mat[nR][nC] == -1) continue;
					cnt++;
					mat[nR][nC] += divide;
				}
				mat[cur.r][cur.c] -= divide * cnt;
			}
			
			// 복사
			cmat = new int[R][C];
			for(int i=0; i<R; i++) {
				for(int j=0; j<C; j++) {
					cmat[i][j] = mat[i][j];
				}
			}
			
			// 공기청정기 순환
			circulate(m.get(0), dir1);
			circulate(m.get(1), dir2);
			
		}
		
		int sum = 0;
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				if(mat[i][j] != -1) {
					sum += mat[i][j];
				}
			}
		}
		System.out.println(sum);
	}
	
	private static void circulate(Point p, int dir[]) {
		int r = p.r;
		int c = 1;
		mat[r][c] = 0;
		
		for(int k=0; k<4; k++) {
			while(true) {
				int nR = r + dr[dir[k]];
				int nC = c + dc[dir[k]];
				if(nR < 0 || nR >= R || nC < 0 || nC >= C || (nR == p.r && nC == p.c)) break;
				mat[nR][nC] = cmat[r][c];
				r = nR;
				c = nC;
			}
		}
	}

}
