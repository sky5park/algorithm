package BJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_13460 {
	static class Point {
		int rR, rC, bR, bC, cnt;
		public Point() {
			this.cnt = 0;
		}
		public Point(int rR, int rC, int bR, int bC, int cnt) {
			this.rR = rR;
			this.rC = rC;
			this.bR = bR;
			this.bC = bC;
			this.cnt = cnt;
		}
	}
	
	static int N, M, ans;
	static char mat[][];
	static int dr[] = {-1, 1, 0, 0};
	static int dc[] = {0, 0, -1, 1};
	static Point start = new Point();
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		mat = new char[N][M];
		for(int i=0; i<N; i++) {
			char[] temp = br.readLine().trim().toCharArray();
			for(int j=0; j<M; j++) {
				mat[i][j] = temp[j];
				if(mat[i][j] == 'R') {
					start.rR = i;
					start.rC = j;
				}
				else if(mat[i][j] == 'B') {
					start.bR = i;
					start.bC = j;
				}
			}
		}
		ans = bfs();
		System.out.println(ans);
		
	}
	
	private static int bfs() {
		int ret = -1;
		boolean visited[][][][] = new boolean[N][M][N][M];
		Queue<Point> q = new LinkedList<Point>();
		q.add(start);
		visited[start.rR][start.rC][start.bR][start.bC] = true;
		while(!q.isEmpty()) {
			Point cur = q.poll();
			if(cur.cnt > 10) break;
			if(mat[cur.rR][cur.rC]== 'O' && mat[cur.bR][cur.bC] != 'O') {
				ret = cur.cnt;
				break;
			}
			
			for(int k=0; k<4; k++) {
				int n_rR = cur.rR;
				int n_rC = cur.rC;
				int n_bR = cur.bR;
				int n_bC = cur.bC;
				
				while(true) {
					if(mat[n_rR][n_rC] != '#' && mat[n_rR][n_rC] != 'O') {
						n_rR += dr[k];
						n_rC += dc[k];
					}
					else {
						if(mat[n_rR][n_rC] == '#') {
							n_rR -= dr[k];
							n_rC -= dc[k];
						}
						break;
					}
				}
				
				while(true) {
					if(mat[n_bR][n_bC] != '#' && mat[n_bR][n_bC] != 'O') {
						n_bR += dr[k];
						n_bC += dc[k];
					}
					else {
						if(mat[n_bR][n_bC] == '#') {
							n_bR -= dr[k];
							n_bC -= dc[k];
						}
						break;
					}
				}
				
				if(n_rR == n_bR && n_rC == n_bC) {
					if(mat[n_rR][n_rC] != 'O') {
						int red_dist = Math.abs(n_rR - cur.rR) + Math.abs(n_rC - cur.rC);
						int blue_dist = Math.abs(n_bR - cur.bR) + Math.abs(n_bC - cur.bC);
						if(red_dist > blue_dist) {
							n_rR -= dr[k];
							n_rC -= dc[k];
						}
						else {
							n_bR -= dr[k];
							n_bC -= dc[k];
						}
							
					}
				}
				
				if(!visited[n_rR][n_rC][n_bR][n_bC]) {
					visited[n_rR][n_rC][n_bR][n_bC] = true;
					q.add(new Point(n_rR, n_rC, n_bR, n_bC, cur.cnt + 1));
				}
				
			}
			
		}
		
		return ret;
	}

}
