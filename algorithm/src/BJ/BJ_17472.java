package BJ;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// BJ_17472: 다리 만들기2
public class BJ_17472 {
	static class Point {
		int r, c;
		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	static int N, M, bridgeSize;
	static int mat[][];
	static int union[][];
	static int dr[] = {-1, 1, 0, 0};
	static int dc[] = {0, 0, -1, 1};
	static Queue<Point> land = new LinkedList<Point>();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		mat = new int[N][M];
		for(int i=0; i<N; i++) {
			for(int j =0; j<M; j++) {
				mat[i][j] = sc.nextInt();
				if(mat[i][j] == 1) {
					land.add(new Point(i, j));
				}
			}
		}
		
		union = new int[N][M];
		
		
		int num = 1;
		ArrayList<ArrayList<Point>> boundary = new ArrayList<ArrayList<Point>>();
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(union[i][j] != 0) continue;
				Queue<Point> q = new LinkedList<Point>();
				boolean visited[][] = new boolean[N][M];
				q.add(new Point(i, j));
				visited[i][j] = true;
				ArrayList<Point> sub = new ArrayList<Point>();
				while(!q.isEmpty()) {
					Point cur = q.poll();
					union[cur.r][cur.c] = num;
					for(int k=0; k<4; k++) {
						int nR = cur.r + dr[k];
						int nC = cur.c + dc[k];
						if(nR < 0 || nR >= N || nC < 0 || nC >= M || mat[nR][nC] == 0 || visited[nR][nC]) {
							sub.add(cur);
							continue;
						}
						visited[nR][nC] = true;
						q.add(new Point(nR, nC));
					}
				}
				boundary.add(sub);
				num++;
			}
		}
		
		for(int i=0; i<boundary.size(); i++) {
			Queue<Point> q = new LinkedList<Point>();
			boolean visited[][] = new boolean[N][M];
			for(Point p: boundary.get(i)) {
				q.add(p);
				visited[p.r][p.c] = true;
			}
			int size = 0;
			while(!q.isEmpty()) {
				Point cur = q.poll();
				
				for(int k=0; k<4; k++) {
					int len = 0;
					int r = cur.r;
					int c = cur.c;
					while(true) {
						int nR = r + dr[k];
						int nC = c + dc[k];
						if(nR < 0 || nR >= M || nC < 0 || nC > M || mat[nR][nC] == 1 || union[r][c] == union[nR][nC]) {
							break;
						}
						len++;
						r = nR;
						c = nC;
					}
					if(len >= 2) {
						
					}
				}
			}
		}
		
		
	}

}


