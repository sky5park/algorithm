package BJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_2146 {
	static class Point {
		int r, c, dist;
		public Point(int r, int c) {
			this.r = r;
			this.c = c;
			this.dist = 0;
		}
		public Point(int r, int c, int dist) {
			this.r = r;
			this.c = c;
			this.dist = dist;
		}
	}
	static int N, unionNum;
	static int mat[][], union[][];
	static int dr[] = {-1, 1, 0, 0};
	static int dc[] = {0, 0, -1, 1};
	static ArrayList<Point> land = new ArrayList<Point>();
	static ArrayList<ArrayList<Point>> nodes = new ArrayList<ArrayList<Point>>();
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine().trim());
		
		mat = new int[N][N];
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine().trim(), " ");
			for(int j=0; j<N; j++) {
				mat[i][j] = Integer.parseInt(st.nextToken());
				if(mat[i][j] == 1) {
					land.add(new Point(i, j));
				}
			}
		}
		
		unionNum = 0;
		union = new int[N][N];
		nodes.add(null);
		
		for(int i =0; i<land.size(); i++) {
			Point l = land.get(i);
			if(union[l.r][l.c]  == 0) {
				Queue<Point> q = new LinkedList<Point>();
				q.add(l);
				ArrayList<Point> uList = new ArrayList<Point>();
				unionNum++;
				union[l.r][l.c] = unionNum;
				while(!q.isEmpty()) {
					Point cur = q.poll();
					uList.add(cur);
					for(int k=0; k<4; k++) {
						int nR = cur.r + dr[k];
						int nC = cur.c + dc[k];
						if(nR < 0 || nR >= N || nC < 0 || nC >= N || mat[nR][nC] == 0 || union[nR][nC] != 0) continue;
						q.add(new Point(nR, nC));
						union[nR][nC] = unionNum;
					}
				}
				nodes.add(uList);
			}
		}
		
		int min = Integer.MAX_VALUE;
		for(int i=1; i<unionNum; i++) {
			ArrayList<Point> uList = nodes.get(i);
			for(Point p: uList) {
				Queue<Point> q = new LinkedList<Point>();
				boolean visited[][] = new boolean[N][N];
				q.add(p);
				visited[p.r][p.c] = true;
				while(!q.isEmpty()) {
					Point cur = q.poll();
					for(int k=0; k<4; k++) {
						int nR = cur.r + dr[k];
						int nC = cur.c + dc[k];
						if(nR < 0 || nR >= N || nC < 0 || nC >= N || visited[nR][nC] || union[nR][nC] == i) continue;
						if(mat[nR][nC] == 0) {
							if(cur.dist + 1 < min) { // 가지 치기
								q.add(new Point(nR, nC, cur.dist + 1));
								visited[nR][nC] = true;
							}
						}
						else {
							min = Integer.min(min, cur.dist);
						}
					}
				}
			}
		}
		
		System.out.println(min);
	}

}
